<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table cellspacing="0" width="100%" id="userlist-html"
	class="table-list-con" bordercolor="#FFF" border="1">
	<tr>
		<th>编号</th>
		<th>电子邮件</th>
		<th>昵称</th>
		<th>拥有角色</th>
		<th>修改</th>
		<th>删除</th>
	</tr>
	<c:set var="num" value="1" scope="page"></c:set>
	<c:forEach begin="0" var="item" items="${ requestScope.userlist }">
		<tr>
			<td style="background: #C4D1DB; text-align: center;">${
				pageScope.num }</td>
			<td style="background: #FFFFFF; text-align: center;">${
				item.email }</td>
			<td style="background: #C4D1DB; text-align: center;">${
				item.nickname }</td>
			<td style="background: #FFFFFF; text-align: center;"><c:forEach
					begin="0" items="${ item.roles }" var="role">
									${ role.rolename }/
								</c:forEach>
			</td>
			<td style="background: #C4D1DB; text-align: center;"><a
				href="javascript:void(0);" class="a-update"
				onclick="commitUser('updateuserUI','${ item.id }')">修改</a>
			</td>
			<td style="background: #FFFFFF; text-align: center;"><a
				href="javascript:void(0);" class="a-delete"
				onclick="commitUser('deleteuser','${ item.id }')">删除</a>
			</td>
		</tr>
		<c:set var="num" value="${ pageScope.num + 1 }" scope="page"></c:set>
	</c:forEach>
</table>
