<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="usermanage-adduser" id="adduser-html">
	<form id="add-form"
		action="${ pageContext.request.contextPath }/servlet/RoleServlet"
		method="post" target="main">
		<div class="add-item">
			<div class="add-item-prompt prompt-email">角色名称</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="rolename" id="rolename" class="add-in"
						value="${ requestScope.role.rolename }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">角色描述</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="description" id="description"
						class="add-in" value="${ requestScope.role.description }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="item-privilege">
			<div class="privi-title">拥有权限</div>
			<div class="choise-privi">
				<c:forEach var="privilege" items="${ requestScope.privileges }">
					<span class="ck-item"> ${ privilege.privilegename }<input
						class="privi-check-in" type="checkbox" name="privilegeid"
						value="${ privilege.id }"> </span>
				</c:forEach>
				<span class="ck-item" style="background: #006CFF;"> 全选<input
					class="privi-check-in" type="checkbox" onclick="selectAll(this)">
				</span>
			</div>
		</div>
		<div class="add-submit-warp">
			<a id="add-send" class='add-submit-button' href='javascript:void(0);'
				onclick="submitF()">${ requestScope.action }</a>
		</div>
		<input type="hidden" name="action"
			value="${ requestScope['action.name'] }"> <input
			type="hidden" name="id" value="${ requestScope.role.id }">
	</form>
</div>
</html>
