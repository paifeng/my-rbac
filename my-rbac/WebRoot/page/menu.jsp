<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>menu</title>
<style type="text/css">
*,html,body {
	margin: 0px;
	padding: 0px;
	background: #CBC9C9;
}

.menu-warp {
	width: 150px;
	border: 1px solid #A7A7A7;
	position: absolute;
	left: 50%;
	margin-top: 30px;
	margin-left: -75px;
}

.menu-item {
	border-bottom: 1px solid #A7A7A7;
	width: 100%;
	height: 30px;
	line-height: 30px;
	text-align: center;
	color: #000;
	font-size: 15px;
	display: block;
	text-decoration: none;
}

.menu-item:hover {
	background: #B2B2B2;
}
</style>
</head>

<body>
	<div class="menu-warp">
		<a class="menu-item"
			href="${ pageContext.request.contextPath }/page/usermanage.jsp"
			target="main">用户管理</a> <a class="menu-item"
			href="${ pageContext.request.contextPath }/page/rolemanage.jsp"
			target="main">角色管理</a> <a class="menu-item"
			href="${ pageContext.request.contextPath }/page/privilegemanage.jsp"
			target="main">权限管理</a> <a class="menu-item"
			href="${ pageContext.request.contextPath }/page/emailmanage.jsp"
			target="main">电子邮件</a>
	</div>
</body>
</html>
