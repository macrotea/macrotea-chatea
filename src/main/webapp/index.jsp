<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<%
  String ctxPath = request.getContextPath();
  String wsPath = "ws://" + request.getServerName() + ":" + request.getServerPort() + ctxPath;

  Calendar cal = Calendar.getInstance();
  int year = cal.get(Calendar.YEAR);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Macrotea - 茶余饭后 - 基于Websocket的聊天室</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="macrotea-chatlet">
    <meta name="author" content="macrotea">

    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <![endif]-->

    <script type="text/javascript">
    	var chatWebsocketUrl = '<%=wsPath%>/chat.ws';
    </script>
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="#"><strong>茶余饭后</strong></a>

            <div class="nav-collapse collapse">
                <p class="navbar-text pull-right">
                    当前登录： <a href="#" class="navbar-link"><span class="nicknameHolder">未知用户</span></a>
                </p>
                <ul class="nav">
                    <li class="active"><a href="<%=ctxPath%>">登陆</a></li>
                    <li><a href="#aboutModal" data-toggle="modal">关于</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container-fluid">
    <% boolean isIE = request.getHeader("user-agent").indexOf("MSIE") > -1;if(isIE) { %>
    <div class="row-fluid" id="ieValidateBox">
        <div class="span4 offset4 well">
            <h3 style="color:#bd362f">由于现行IE浏览器不支持Websocket<br/>请使用Firefox或者Chrome浏览器登陆！</h3>
        </div>
    </div>
    <% } %>

    <div class="row-fluid" id="loginBox">
        <div class="span4 offset4 well">
            <legend>欢迎登录茶余饭后</legend>
            <div class="alert alert-error" id="loginTipBox" style="display:none">
                <a class="close" data-dismiss="alert" href="#">×</a><span id="loginTip">登录失败</span>
            </div>
            <form method="POST" action="" accept-charset="UTF-8">
                <input type="text" id="nicknameTxtInput" class="input-block-level" placeholder="您的昵称" AUTOCOMPLETE="off"/>
                <button id="loginBtn" type="submit" name="submit" class="btn btn-info btn-block">登 录</button>
            </form>
        </div>
    </div>

    <div class="row-fluid" id="mainBox" style="display:none">
        <div class="span3">
            <div class="well sidebar-nav">
                <ul class="nav nav-list" id="userList">
                </ul>
            </div>
            <!--/.well -->
        </div>
        <!--/span-->
        <div class="span9">
            <ul class="breadcrumb">
                <li><span class="nicknameHolder">未知用户</span>，欢迎您来到茶余饭后~</li>
            </ul>
            <div class="well" id="content">
                <p>
                    <span class="badge badge-important">小站客服&nbsp;&nbsp;&nbsp;23:23:09</span>
                </p>
                <div class="alert alert-error">
                    <span class="nicknameHolder">未知用户</span>，您好，您可以通过左侧的用户列表查看当前在线用户！同时点击页面顶部的 “关于” 了解更多信息！
                </div>

            </div>
            <div class="well">
                <form class="form-inline">
                    <input id="msgTxtInput" type="text" class="input-xxlarge" placeholder="内容">
                    <button id="sendBtn" type="submit" class="btn btn-primary">发送</button>
                    <button id="cleanBtn" type="button" class="btn">清空</button>
                </form>
                <div id="tip"></div>
            </div>
        </div>
        <!--/span-->
    </div>
    <!--/row-->

    <hr>
    <footer>
        <p>© <a href="http://115.28.220.238/" target="_blank">macrotea.cn</a> <%=year%></p>
    </footer>

    <div id="aboutModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">关于茶余饭后</h3>
        </div>
        <div class="modal-body">
            <p>茶余饭后是一个以交流心情、娱乐、休闲的在线小玩意~</p>
            <p>技术组合：Spring + <a href="http://bristleback.pl" target="_blank">bristleback</a> (基于Websocket的Java框架)</p>
            <p>联系QQ：<a href="http://sighttp.qq.com/authd?IDKEY=f87ba8e8fc35c8ccf9eb9062db31c99207166221f58afaaf" target="_blank">395423086</a> </p>
            </div>
            <div class="modal-footer">
            <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
        </div>
    </div>
</div>
<!--/.fluid-container-->

<script src="js/jquery.js"></script>
<script src="js/bootstrap-transition.js"></script>
<script src="js/bootstrap-alert.js"></script>
<script src="js/bootstrap-modal.js"></script>
<script src="js/bootstrap-dropdown.js"></script>
<script src="js/bootstrap-scrollspy.js"></script>
<script src="js/bootstrap-tab.js"></script>
<script src="js/bootstrap-tooltip.js"></script>
<script src="js/bootstrap-popover.js"></script>
<script src="js/bootstrap-button.js"></script>
<script src="js/bootstrap-collapse.js"></script>
<script src="js/bootstrap-carousel.js"></script>
<script src="js/bootstrap-typeahead.js"></script>
<script src="js/nano.js"></script>
<script src="js/underscore-min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/bristleback-0.3.5.js"></script>
<script src="js/chatea-core.js"></script>

</body>
</html>