<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.boyzhang.com/elfunction/ff" prefix="ff"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/css/register.css" />

<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
<script src="${ pageContext.request.contextPath }/js/register.js"></script>

</head>
<body>
	<div class="main-warp">
		<form id="register" name="register"
			action="${ pageContext.servletContext.contextPath }/servlet/UserServlet"
			method="post" target="_top">
			<input type="hidden" name="action" value="login">
			<div class="reg-warp">
				<div class="reg-item">
					<div class="reg-item-prompt prompt-email">账户(邮箱)</div>
					<div class="reg-item-in">
						<div class="in-warp">
							<input type="text" name="email" id="email" class="reg-in"
								value="${ ff:urlDecoder(cookie.account.value, 'utf-8') }">
						</div>
					</div>
					<%-- <div class="reg-msg errMsg">${
						requestScope.errMsg.ACCOUNT_ERROR }</div> --%>
				</div>
				<div class="reg-item">
					<div class="prompt-password reg-item-prompt">密码(6-16位)</div>
					<div class="reg-item-in">
						<div class="in-warp">
							<input type="password" name="password" id="password"
								class="reg-in">
						</div>
					</div>
					<div class="reg-msg errMsg">${
						requestScope.errMsg.PASSWORD_ERROR }</div>
				</div>
				<div class="reg-item">
					<div class="prompt-vcode reg-item-prompt">验证码</div>
					<div class="reg-item-in">
						<div class="in-warp">
							<input type="text" name="vcode" id="vcode" class="reg-in">
						</div>
					</div>
					<div class="reg-msg errMsg">${
						requestScope.errMsg.VERICODE_ERROR }</div>
				</div>
				<div class="reg-vcode-warp">
					<div class="reg-vcode">
						<div class="vcode-img">
							<img class="verfiCode"
								src="${ pageContext.request.contextPath }/servlet/VerifiCodeServlet" />
						</div>
						<div class="vcode-refresh">
							<div class="vcode-button-warp">
								<a class="vcode-button" href="javascript:void(0);"><span>换一张</span>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="reg-check">
					<div class="reg-check-warp">
						<div class="reg-remember-account">
							<div class="inner-warp">
								<input class="remAcc" type="checkbox" checked value="checked"
									name="remAcc"> <span class="remAcc-words">记住账户</span>
							</div>
						</div>
						<div class="reg-forget-acc">
							<a href="#">忘记密码?</a>
						</div>
					</div>
				</div>
				<div class="reg-submit-warp">
					<a id="reg-send" class='reg-submit-button'
						href='javascript:void(0);'>提交</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>