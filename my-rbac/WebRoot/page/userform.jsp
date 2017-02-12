<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="usermanage-adduser" id="adduser-html">
	<form id="add-form"
		action="${ pageContext.request.contextPath }/servlet/UserManageServlet"
		method="post" target="main">
		<div class="add-item">
			<div class="add-item-prompt prompt-email">账户(邮箱)</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="email" id="email" class="add-in"
						value="${ requestScope.user.email }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">昵称</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="nickname" id="nickname" class="add-in"
						value="${ requestScope.user.nickname }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">密码</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="password" id="password" class="add-in"
						value="">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">确认密码</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="confirm" id="confirm" class="add-in"
						value="">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="item-privilege">
			<div class="privi-title">所属角色</div>
			<div class="choise-privi">
				<c:forEach var="role" items="${ requestScope.roles }">
					<span class="ck-item"> ${ role.rolename }<input
						class="privi-check-in" type="checkbox" name="roleid"
						value="${ role.id }"> </span>
				</c:forEach>
			</div>
		</div>
		<div class="add-submit-warp">
			<a id="add-send" class='add-submit-button' href='javascript:void(0);'
				onclick="submitF()">${ requestScope.action }</a>
		</div>
		<input type="hidden" name="action"
			value="${ requestScope['action.name'] }"> <input
			type="hidden" name="id" value="${ requestScope.user.id }">
	</form>
</div>
</html>
