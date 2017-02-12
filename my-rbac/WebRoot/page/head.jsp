<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>head</title>
<style type="text/css">
*,html,body {
	margin: 0px;
	padding: 0px;
	background: #036FFC;
}

.logo-warp {
	height: 100px;
	width: 100px;
	float: left;
	position: relative;
}

.logo-img {
	height: 60px;
	width: 60px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-top: -30px;
	margin-left: -30px;
	border-radius: 30px;
	border: 2px solid #0F4B9B;
}

.title {
	height: 100px;
	line-height: 100px;
	font-size: 30px;
	color: #FFFFFF;
	font-family: Arial, "Microsoft YaHei", "WenQuanYi Micro Hei",
		"Open Sans", "Hiragino Sans GB", Verdana, sans-serif;
	float: left;
	text-decoration: none;
}

.head-tool-bar {
	height: 30px;
	position: absolute;
	right: 30px;
	bottom: 0px;
}

.bar-item {
	width: 50px;
	height: 30px;
	float: left;
	background: #5076AD;
	line-height: 30px;
	text-align: center;
	color: #E3E0E0;
	text-decoration: none;
}

.bar-item:hover {
	background: #3E5F8E;
}

.bar-not-first {
	border-left: 1px solid #3E5F8E;
}

.login-msg {
	position: absolute;
	top: 10px;
	right: 30px;
	color: #FFFFFF;
	font-weight: bolder;
	font-size: 13px;
}
</style>
<script type="text/javascript">
	function refreshHead() {
		window.location = "./head.jsp";
	}
</script>
</head>

<body>
	<div class="logo-warp">
		<a href="../" target="_top"><img class="logo-img" alt="葫芦娃用户权限管理系统"
			src="../img/logo.png"> </a>
	</div>
	<div class="login-msg" id="id-login-msg">
		<c:if test="${ not empty sessionScope.loginUser }">欢迎你:${
		sessionScope.loginUser.nickname }</c:if>
	</div>
	<div class="title">
		<a class="title" href="../" target="_top">葫芦娃用户权限管理系统</a>
	</div>
	<div class="head-tool-bar">
		<a class="bar-item" href="./login.jsp" target="main"> <c:if
				test="${ not empty sessionScope.loginUser }">
		切换</c:if> <c:if test="${ empty sessionScope.loginUser }">
		登录</c:if> </a>
		<c:if test="${ not empty sessionScope.loginUser }">
			<a class="bar-item bar-not-first"
				href="${ pageContext.request.contextPath }/servlet/UserServlet?action=exit"
				target="main" onclick="refreshHead()">退出</a>
		</c:if>
	</div>
</body>
</html>
