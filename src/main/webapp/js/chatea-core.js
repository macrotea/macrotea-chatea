(function ($) {

    var
        Constant = {
            login : "登 录",
            logining : "登录检查中...",
            nicknameNotEmpty : "请输入昵称~",
            connectFail : "登录连接失败！请您与管理员联系！",
            nicknameExists : "昵称已存在，请重新输入！",
            timeFormat : "H:mm:ss",
            siteMonitor : "小站客服",
            error : "数据通信异常",
            loginBan: false
        },

        WHO= "",

        Util = {
            getNowTime: function () {
                return moment().format(Constant.timeFormat);
            },
            isOnlyYou: function (userList) {
                if(!userList){
                    return true;
                }
                return userList.length==1?true:false;
            }
        };


    var $content = $("#content");
    var $userList = $("#userList");
    var $msgTxtInput = $("#msgTxtInput");
    var $nicknameTxtInput = $("#nicknameTxtInput");
    var $loginBtn = $("#loginBtn");

    //------------------------------
    // Message
    //------------------------------

    var Message = {
        userListHeaderLi: "<li class='nav-header'>在线用户（{total}）</li>",
        userListSiteMonitorLi: "<li><a href='#'>" + Constant.siteMonitor + "</a></li>",
        userListEachLi: "<li><a href='#'>{nickname}</a></li>",
        msgTpl: "<p><span class='badge badge-{badgeClazz}'>{name}&nbsp;&nbsp;&nbsp;{time}</span><div class='alert alert-{alertClazz}'>{msg}</div></p>",
        statTpl: "每一分钟服务器推送：截止登陆用户总数 - {loginTotal} ，截止消息发送总数 - {msgTotal}",
        siteMonitorSays: function (data) {
            return nano(this.msgTpl, {
                badgeClazz: "important",
                alertClazz: "error",
                name: Constant.siteMonitor,
                time: Util.getNowTime(),
                msg: data
            });
        },
        userSays: function (name, data) {
            var colorLevel = "info";
            if(name == WHO){
                colorLevel = "success";
            }

            return nano(this.msgTpl, {
                badgeClazz: colorLevel,
                alertClazz: colorLevel,
                name: name,
                time: Util.getNowTime(),
                msg: data
            });
        },
        whoJoin: function (username) {
            return "【" + username + "】 悄悄地加入聊天~";
        },
        whoLeave: function (username) {
            return "【" + username + "】 悄悄地溜走了~";
        },
        whenOnlyYou: function () {
            return "当前聊天室只有您一个在线用户，您可以新开一个标签页自娱自乐~ (-_-)";
        },
        whenShowStat: function (chatStat) {
            return nano(this.statTpl, chatStat);
        }

    };

    //------------------------------
    // View
    //------------------------------

    var View = {

        //-- content

        contentAppend: function (html) {
            $content.append(html);
        },
        contentClean: function () {
            $content.empty();
        },
        contentScrollToBottom:function(){
            $content.animate({ scrollTop: $content[0].scrollHeight}, 1000);
        },

        //-- user list

        userListClean: function () {
            $userList.empty();
        },

        userListAppend: function (html) {
            $userList.append(html);
        },

        userListRefresh: function (userList) {
            this.userListPrepare(userList.length);

            _.each(userList, function (val, key) {
                View.userListAppend(nano(Message.userListEachLi, val))
            });
        },

        userListPrepare: function (userListLen) {
            this.userListClean();

            var userStat = {};
            userStat.total = userListLen + 1;

            //>>在线用户
            var userListHeaderLiHtml = nano(Message.userListHeaderLi, userStat);

            //>>小站客服
            var userListSiteMonitorLiHTML = Message.userListSiteMonitorLi;

            $userList.append(userListHeaderLiHtml);
            $userList.append(userListSiteMonitorLiHTML);
        },

        //-- form
        msgTxtInputClean :function(){
            $msgTxtInput.val('');
        },

        //-- login

        showTipWhenLoginError:function(tip){
            $("#loginTip").text(tip);
            $("#loginTipBox").show().delay(1500).slideUp(500);
        },

        loginBtnDisabled:function(){
            Constant.loginBan = true;
            $loginBtn.text(Constant.logining).addClass("disabled");
        },

        loginBtnReset:function(){
            Constant.loginBan = false;
            $loginBtn.text(Constant.login).removeClass("disabled");
        },

        logined:function(){
            $("#loginBox").hide();
            $("#mainBox").show();
            $msgTxtInput.focus();
        },

        backToLogin:function(){
            $("#loginBox").show();
            $("#mainBox").hide();
            $nicknameTxtInput.focus();
        },

        showCurrentNickname:function(){
            $(".nicknameHolder").text(WHO);
        },

        //-- binding

        initBindings : function(){

            var that = this;

            $("#sendBtn").click(function (e) {
                e.preventDefault();
                var msg = $msgTxtInput.val();
                if (msg && msg.length > 0) {
                    messageAction.executeDefault(Bristleback.CONNECTOR, {'nickname': WHO,'msg':msg});
                    that.msgTxtInputClean();
                    $msgTxtInput.focus();
                }
            });

            $("#cleanBtn").click(function () {
                that.contentClean();
                $msgTxtInput.focus();
            });

            $loginBtn.click(function (e) {
                e.preventDefault();

                if(Constant.loginBan) return;

                var nickname = $nicknameTxtInput.val();
                if(nickname && nickname.length >0 ){
                    View.loginBtnDisabled();
                    WHO = nickname;
                    if(Server.client.isConnected()){
                        chatAction.joinUser(Bristleback.USER_CONTEXT, WHO);
                    }else{
                        Server.client.connect();
                    }
                }else{
                    that.showTipWhenLoginError(Constant.nicknameNotEmpty);
                }
            });

            $("#nicknameTxtInput").focus();
        }
    }

    View.initBindings();

    //------------------------------
    // Websocket
    //------------------------------

    var Server = {

        client: null,

        controller: null,

        config: {
            serverUrl: chatWebsocketUrl,
            onOpen: function (event) {
                chatAction.joinUser(Bristleback.USER_CONTEXT, WHO);
            },
            onClose: function (event) {
                View.backToLogin();
                View.loginBtnReset();
                View.showTipWhenLoginError(Constant.connectFail);
            }
        },

        init: function () {
            this.client = Bristleback.newClient(this.config);

            this.controller = this.client.dataController;
            return this;
        },

        connect: function () {
            this.client.connect();
            return this;
        },

        disconnect: function () {
            this.client.disconnect();
            return this;
        }
    }

    Server.init();

    //------------------------------
    // Client Action
    //------------------------------

    Server.controller.registerClientActionClass("ChatClientAction", {

        /**
         * 用户已加入
         * @param nickname
         * @param userList
         */
        userJoined: function (nickname, userList) {
            View.userListRefresh(userList);
            View.contentAppend(Message.siteMonitorSays(Message.whoJoin(nickname)));

            if(Util.isOnlyYou(userList)){
                View.contentAppend(Message.siteMonitorSays(Message.whenOnlyYou()));
            }

            View.contentScrollToBottom();
        },

        /**
         * 消息已发送
         * @param chatUser
         * @param chatText
         */
        msgSent: function (chatUser,chatText) {
            if(!chatText) return;
            var nickname = chatUser.nickname;
            if (nickname) {
                var msg = Bristleback.utils.escapeHTML(chatText.msg);
                View.contentAppend(Message.userSays(nickname, msg));
                View.contentScrollToBottom();
            }
        },

        /**
         * 用户已离开
         * @param nickname
         * @param userList
         */
        userLeave: function (nickname,userList) {
            View.userListRefresh(userList);
            View.contentAppend(Message.siteMonitorSays(Message.whoLeave(nickname)));
            View.contentScrollToBottom();
        },

        /**
         * 统计已推送
         * @param chatStat
         */
        statPushed: function (chatStat) {
            View.contentAppend(Message.siteMonitorSays(Message.whenShowStat(chatStat)));
            View.contentScrollToBottom();
        }
    });

    //------------------------------
    // Server Action
    //------------------------------

    var chatAction = Server.controller.getActionClass("ChatAction");
    var messageAction = Server.controller.getActionClass("MessageAction");
    messageAction.defineDefaultAction();


    //--用户加入
    chatAction
        .defineAction("joinUser")
        .setResponseHandler(function(chatUser){
            View.logined();
            View.showCurrentNickname();
        })
        .exceptionHandler
        .setExceptionHandler("SerializationException", exceptionHandler)
        .setExceptionHandler("ChatUserExistsException", function(){
            View.loginBtnReset();
            View.showTipWhenLoginError(Constant.nicknameExists);
        });


    //------------------------------
    // 公用
    //------------------------------

    function exceptionHandler() {
        alert(Constant.error);
    }

})(jQuery);
