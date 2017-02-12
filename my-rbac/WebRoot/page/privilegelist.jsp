<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table cellspacing="0" width="100%" id="userlist-html"
	class="table-list-con" bordercolor="#FFF" border="1">
	<tr>
		<th>编号</th>
		<th>权限名称</th>
		<th>控制URL</th>
		<th>控制动作</th>
		<th>未登录访问</th>
		<th>权限描述</th>
		<th>修改</th>
		<th>删除</th>
	</tr>
	<c:set var="num" value="1" scope="page"></c:set>
	<c:forEach begin="0" var="item" items="${ requestScope.privileges }">
		<tr>
			<td style="background: #C4D1DB; text-align: center;">${
				pageScope.num }</td>
			<td style="background: #FFFFFF; text-align: center;">${
				item.privilegename }</td>
			<td style="background: #C4D1DB; text-align: center;">${
				item.privilegeUrl }</td>
			<td style="background: #FFFFFF; text-align: center;">${
				item.requestaction }</td>
			<td style="background: #FFFFFF; text-align: center;">
				<c:if test="${ item.nologin eq 1 }">是</c:if>
				<c:if test="${ item.nologin != 1 }">否</c:if>
			</td>
			<td style="background: #C4D1DB; text-align: center;">${
				item.description }</td>
			<td style="background: #FFFFFF; text-align: center;"><a
				href="javascript:void(0);" class="a-update"
				onclick="commitRole('updateprivilegeUI','${ item.id }')">修改</a>
			</td>
			<td style="background: #C4D1DB; text-align: center;"><a
				href="javascript:void(0);" class="a-delete"
				onclick="commitRole('deleteprivilege','${ item.id }')">删除</a>
			</td>
		</tr>
		<c:set var="num" value="${ pageScope.num + 1 }" scope="page"></c:set>
	</c:forEach>
</table>
