<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'usermanage.jsp' starting page</title>
<style type="text/css">
*,html,body {
	margin: 0px;
	padding: 0px;
}
body {
	background: #FFF;
}

th {
	background: #AFAFAF;
	min-width: 50px;
}

td,tr,th,table {
	border: 1px solid #FFF;
}

th,td {
	padding: 0.5em 2em;
}

.main-usermanage-warp {
	border: 1px solid #ccc;
	width: 99%;
	height: 100%;
	margin: 6px;
	width: 99%;
	position: relative;
}

.usermanage-bar {
	height: 30px;
	position: relative;
	height: 30px;
	overflow: hidden;
}

.bar-item {
	background: #CBC9C9;
	width: 250px;
	height: 30px;
	float: left;
	text-align: center;
	line-height: 30px;
	color: #FFF;
	float: right;
	display: block;
	text-decoration: none;
}

.bar-item:hover {
	background: #A5A0A0;
}

.usermanage-con-warp {
	height: 85%;
	margin-top: 15px;
	overflow-y: auto;
}

.usermanage-adduser {
	width: 600px;
	height: auto;
	position: relative;
	margin-top: 20px;
	left: 50%;
	margin-left: -300px;
	position: relative;
}

.add-item {
	width: 100%;
	height: 50px;
	position: relative;
}

.add-item-in {
	width: 600px;
	height: 40px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -300px;
	margin-top: -20px;
	border: 1px solid #006CFF;
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px #C1C1C1;
	overflow: hidden;
}

.add-item-prompt {
	width: 680px;
	height: 40px;
	top: 50%;
	left: 50%;
	margin-left: -290px;
	margin-top: -20px;
	text-align: left;
	line-height: 40px;
	color: #CCC;
	font-size: 15px;
	position: absolute;
}

.in-warp {
	width: 600px;
	height: 40px;
}

.add-in {
	width: 600px;
	height: 40px;
	border: none;
	outline: medium;
	padding-left: 10px;
	background: none;
	font-size: 18px;
	color: #000;
	line-height: 50px;
}

.errMsg,.successMsg {
	height: 30px;
	width: 200px;
	position: absolute;
	top: 50%;
	right: -210px;
	margin-top: -15px;
	overflow: hidden;
	/* border: 1px solid #ccc; */
	line-height: 30px;
	text-align: left;
	color: #999;
	font-size: 13px;
}

.add-msg-img {
	height: 30px;
	width: 30px;
	border: none;
}

.add-submit-warp {
	width: 100%;
	height: 50px;
	/* background: #ccc; */
	overflow: hidden;
	position: relative;
}

.add-submit-button {
	width: 600px;
	height: 38px;
	top: 50%;
	left: 50%;
	position: absolute;
	margin-left: -300px;
	margin-top: -19px;
	line-height: 38px;
	color: #ffffff;
	background-color: #4a8cf7;
	font-size: 16px;
	font-weight: normal;
	font-family: Arial;
	border: 1px solid #dcdcdc;
	-webkit-border-top-left-radius: 7px;
	-moz-border-radius-topleft: 7px;
	border-top-left-radius: 7px;
	-webkit-border-top-right-radius: 7px;
	-moz-border-radius-topright: 7px;
	border-top-right-radius: 7px;
	-webkit-border-bottom-left-radius: 7px;
	-moz-border-radius-bottomleft: 7px;
	border-bottom-left-radius: 7px;
	-webkit-border-bottom-right-radius: 7px;
	-moz-border-radius-bottomright: 7px;
	border-bottom-right-radius: 7px;
	-moz-box-shadow: inset 0px 0px 0px 1px #ffffff;
	-webkit-box-shadow: inset 0px 0px 0px 1px #ffffff;
	box-shadow: inset 0px 0px 0px 1px #ffffff;
	text-align: center;
	display: inline-block;
	text-decoration: none;
	left: 50%;
	position: absolute;
	position: absolute;
}

.add-submit-button:hover {
	background-color: #0078D7;
}

.item-privilege {
	width: 100%;
	height: auto;
	border: 1px solid #006CFF;
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px #C1C1C1;
	overflow: hidden;
}

.privi-title {
	background: #006CFF;
	height: 25px;
	font-size: 15px;
	line-height: 25px;
}

.choise-privi {
	width: 100%;
	height: auto;
	overflow: hidden;
}

.privi-check-in {
	margin-left: 5px;
}

.ck-item {
	height: 20px;
	background: #ccc;
	margin: 6px;
	border-radius: 5px;
	padding: 3px;
	line-height: 20px;
	display: block;
	float: left;
}

.a-update {
	display: block;
	text-decoration: none;
	background: #E0E54B;
	border-radius: 5px;
	padding: 3px;
}

.a-delete {
	display: block;
	text-decoration: none;
	background: #FF9564;
	border-radius: 5px;
	padding: 3px;
}

.table-list-con {
	font-size: 12px;
}
</style>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/functionLibs.js"></script>
<script type="text/javascript">
	window.onload = function() {

		//显示所有的用户信息
		commitRole("listprivileges", null);
		//添加用户
		var addUser = document.getElementById("add-user");
		addUser.onclick = function() {
			commitRole("addprivilegeUI", null);
		};
	};

	//修改用户
	function commitRole(action, id) {
		OpenAjax("${ pageContext.request.contextPath }/servlet/PrivilegeServlet",
				"action=" + action + "&id=" + id, "POST", true, function(
						responseText) {

					//alert(responseText);

					//json = eval("(" + responseText + ")");
					//显示数据
					var con = document.getElementById("con");
					con.innerHTML = responseText;
				}, function(status) {
					//请求失败
					alert("请求失败: [" + status + "]");
				});
	}

	//提交表单
	function submitF() {
		document.getElementById("add-form").submit();
	}
</script>

</head>

<body>

	<div class="main-usermanage-warp">
		<div class="usermanage-bar">
			<a class="bar-item" href="javascript:void(0);" id="add-user">新建权限</a>
		</div>
		<div class="usermanage-con-warp" id="con"></div>
	</div>

</body>
</html>
