<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="usermanage-adduser" id="adduser-html">
	<form id="add-form"
		action="${ pageContext.request.contextPath }/servlet/PrivilegeServlet"
		method="post" target="main">
		<div class="add-item">
			<div class="add-item-prompt prompt-email">权限名称</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="privilegename" id="privilegename"
						class="add-in" value="${ requestScope.privilege.privilegename }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">请求URL</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="privilegeUrl" id="privilegeUrl"
						class="add-in"
						value="<c:if test="${not empty requestScope.privilege.privilegeUrl }">${ requestScope.privilege.privilegeUrl } </c:if><c:if test="${empty requestScope.privilege.privilegeUrl }">${ pageContext.request.contextPath }/</c:if>">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">请求动作(RequestAction)</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="requestaction" id="requestaction"
						class="add-in" value="${ requestScope.privilege.requestaction }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="add-item">
			<div class="add-item-prompt prompt-email">权限描述</div>
			<div class="add-item-in">
				<div class="in-warp">
					<input type="text" name="description" id="description"
						class="add-in" value="${ requestScope.privilege.description }">
				</div>
			</div>
			<div class="add-msg errMsg"></div>
		</div>
		<div class="item-privilege">
			<div class="privi-title">拥有权限</div>
			<div class="choise-privi">
				<span class="ck-item"> 未登录可以访问<input class="privi-check-in"
					type="checkbox" name="nologin" value="1"> </span>
			</div>
		</div>
		<div class="add-submit-warp">
			<a id="add-send" class='add-submit-button' href='javascript:void(0);'
				onclick="submitF()">${ requestScope.action }</a>
		</div>
		<input type="hidden" name="action"
			value="${ requestScope['action.name'] }"> <input
			type="hidden" name="id" value="${ requestScope.privilege.id }">
	</form>
</div>
</html>
