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
    	for (var u in userList) {
    		var eachUser = userList[u];
    		alert("id: " + eachUser.id + ",name: " + eachUser.nickname);
    	}
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
        chatAction.join("macrotea");
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
