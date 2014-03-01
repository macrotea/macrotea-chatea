<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<%
  String ctxPath = request.getContextPath();
  String wsPath = "ws://" + request.getServerName() + ":" + request.getServerPort() + ctxPath;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Macrotea - 闲情小站</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="macrotea-chatlet">
    <meta name="author" content="macrotea">
    
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/jquery.mCustomScrollbar.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
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
            <a class="brand" href="#"><strong>闲情小站</strong></a>

            <div class="nav-collapse collapse">
                <p class="navbar-text pull-right">
                    当前登录： <a href="#" class="navbar-link">张三</a>
                </p>
                <ul class="nav">
                    <li class="active"><a href="#">首页</a></li>
                    <li><a href="#">关于</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container-fluid">
    <!--
    <div class="row-fluid">
        <div class="span4 offset4 well">
            <legend>欢迎登录闲情小站</legend>
            <form class="form-inline">
                <input class="input-large" type="text" id="username" name="username" placeholder="昵称" AUTOCOMPLETE="off"/>
                <button type="submit" name="submit" class="btn btn-primary">登录</button>
            </form>
        </div>
    </div>
    -->
    <div class="row-fluid">
        <div class="span3">
            <div class="well sidebar-nav">
                <ul class="nav nav-list" id="userList">
                    <li class="nav-header">在线用户（4）</li>
                    <li><a href="#">小站客服</a></li>
                    <li><a href="#">张三</a></li>
                    <li><a href="#">李四</a></li>
                    <li><a href="#">王五</a></li>
                    <li><a href="#">大哥</a></li>
                </ul>
            </div>
            <!--/.well -->
        </div>
        <!--/span-->
        <div class="span9">
            <ul class="breadcrumb">
                <li>张三，欢迎你来到闲情小站~</li>
            </ul>
            <div class="well" id="content">

                <p>
                    <span class="badge badge-important">小站客服 / 23:23:09</span>
                    <div class="alert alert-error">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-success">小站客服 / 23:23:09</span>
                    <div class="alert alert-success">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-info">小站客服 / 23:23:09</span>
                    <div class="alert alert-info">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-warning">小站客服 / 23:23:09</span>
                    <div class="alert alert-warning">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-important">小站客服 / 23:23:09</span>
                    <div class="alert alert-error">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-success">小站客服 / 23:23:09</span>
                    <div class="alert alert-success">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-info">小站客服 / 23:23:09</span>
                    <div class="alert alert-info">
                        最近更新了不少东西喔
                    </div>
                </p>
                <p>
                    <span class="badge badge-warning">小站客服 / 23:23:09</span>
                    <div class="alert alert-warning">
                        最近更新了不少东西喔
                    </div>
                </p>

            </div>
            <div class="well">
                <form class="form-inline">
                    <input type="text" class="input-xxlarge" placeholder="内容">
                    <button id="send" type="submit" class="btn btn-primary">发送</button>
                    <button id="clear" type="submit" class="btn">清空</button>
                    <button id="joinBtn" type="button" class="btn">加入用户</button>
                </form>
                <div id="tip"></div>
            </div>
        </div>
        <!--/span-->
    </div>
    <!--/row-->

    <hr>
    <footer>
        <p>© macrotea.cn 2014</p>
    </footer>
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
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/bristleback-0.3.5.js"></script>
<script src="js/chatlet.js"></script>

</body>
</html>