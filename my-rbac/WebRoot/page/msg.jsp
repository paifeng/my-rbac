<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>msg</title>
<style type="text/css">
*,html,body {
	margin: 0px;
	padding: 0px;
}

.msg-notice,.msg-error {
	width: 200px;
	background: #ccc;
	padding: 30px;
	border-radius: 10px;
	text-align: center;
	position: absolute;
	left: 50%;
	margin-left: -100px;
}

.msg-error {
	background: #DB2F21;
}
</style>
<script type="text/javascript">
	function moveAnimation(obj) {
		var nowVal = window.parseInt(obj.style.top.substring(0,
				obj.style.top.length - 1), 10);
		obj.style.top = (nowVal + 1) + "%";

		if (nowVal <= 50) {
			setTimeout(function() {
				moveAnimation(obj);
			}, 1);
		}
	}
	window.onload = function() {
		var idMsgNotice = document.getElementById("id-msg-notice");
		var idMsgError = document.getElementById("id-msg-error");

		if (idMsgNotice) {
			moveAnimation(idMsgNotice);
		} else if (idMsgError) {
			moveAnimation(idMsgError);
		}
	};
</script>
</head>

<body>
	<c:if test="${ not empty requestScope['msg-notice'] }">
		<div style="top:-20%" class="msg-notice" id="id-msg-notice">${
			requestScope['msg-notice'] }</div>
	</c:if>
	<c:if test="${ not empty requestScope['msg-error'] }">
		<div style="top:10%" class="msg-error" id="id-msg-error">${
			requestScope['msg-error'] }</div>
	</c:if>
</body>
</html>
