(function ($) {
    var config = {
        serverUrl: chatWebsocketUrl,
        onOpen: function (event) {
        	$("#tip").text("连接成功");
        },
        onClose: function (event) {
        	$("#tip").text("连接失败");
        }
    };
    var client = Bristleback.newClient(config);
    var dataController = client.dataController;

    function connect() {
        client.connect();
    }

    function disconnect() {
        client.disconnect();
    }

    //------------------------------
    // Client
    //------------------------------

    dataController.registerClientActionClass("ChatClientAction", chatClientAction);
    var chatClientAction = {
		userJoined: function (nickname,userList) {
			alert("nickname: " + nickname);
		}
    };

    //------------------------------
    // Server
    //------------------------------

    var chatAction = dataController.getActionClass("ChatAction");

    //-joinUser

    chatAction.defineAction("joinUser").setResponseHandler(joinUserHandler)
        .exceptionHandler
        .setExceptionHandler("SerializationException", exceptionHandler)
        .setExceptionHandler("ChatUserExistsException", chatUserExistsExceptionHandler);

    function joinUserHandler(userList) {
    	var $userList = clearAndPrepareUesrList(userList.length);
    	for (var u in userList) {
    		var eachUser = userList[u];
    		var eachLiHtml = $userList.append(nano(Template.userListEachLi,eachUser));
    		$userList.append(eachLiHtml);
    	}
    }
    
	function clearAndPrepareUesrList(userListLen){
		var $userList = $("#userList");
		$userList.empty();
		
		var userStat = {};
		userStat.total = userListLen;
		
		var userListHeaderLiHtml = nano(Template.userListHeaderLi,userStat);
		var userListSiteMonitorLiHTML = Template.userListSiteMonitorLi;
		
		$userList.append(userListHeaderLiHtml);
		$userList.append(userListSiteMonitorLiHTML);
		
		return $userList;
	}
    
    var Template = {
		userListHeaderLi : "<li class='nav-header'>在线用户（{total}）</li>",
		userListSiteMonitorLi : "<li><a href='#'>小站客服</a></li>",
		userListEachLi : "<li><a href='#'>{nickname}</a></li>"
    }

    //-- public

    function exceptionHandler() {
        alert("exception");
    }
    
    function chatUserExistsExceptionHandler() {
        alert("chatUserExistsException");
    }

    //-- binding

    $("#joinBtn").click(function(){
        chatAction.joinUser(Bristleback.USER_CONTEXT,"macrotea");
    });

    $(window).load(function () {
        $("#content").mCustomScrollbar({
            scrollButtons: {
                enable: true
            },
            theme: "light"
        });
    });

    connect();
})(jQuery);
