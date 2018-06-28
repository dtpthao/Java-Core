<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="All user" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="listUser" />
		<table>
			<tr>
				<td>UserID:</td>
				<td><input type="number" name="userId" min='0'/></td>
				<td>Login:</td>
				<td><input type="text" name="login"/></td>
				<td>Role:</td>
				<td><select name="roleId">
						<option value=null>ALL</option>
						<option value="0">Admin</option>
						<option value="1">Student</option>
						<option value="2">Block</option>
				</select></td>
				</br>
				<td><input type="submit" value="search"></td>
			</tr>
			</br>
		</table>
	</form>
	<form>
	<input type="hidden" name="command" value="updateRole" />
		<table border='1'>
			<tr>
				<td>№</td>
				<td>ID</td>
				<td>Login</td>
				<td>Role</td>
				<td></td>
			</tr>
			<c:set var='i' value='0' />
			<c:forEach var="bean" items="${users}">
				<c:set var="i" value="${i+1}" />
				<tr>
					<td>${i}</td>
					<td>${bean.id}</td>
					<td>${bean.login}</td>
					<c:choose>
						<c:when test="${bean.id == updId}">
						
							<input type="hidden" name="updUserId" value="${bean.id}" />
							<td><select name="updRoleId">
								<option value="${bean.roleId}">${Role.getRole(bean).getName()}</option>
								<option value="0">Admin</option>
								<option value="1">Student</option>
								<option value="2">Block</option>
							</select></td>
							<td><input type="submit" value="Update"/>
							<td><a href="controller?command=listUser">Cancel</a></td>
						</c:when>
						<c:otherwise>
							<td>${Role.getRole(bean).getName()}</td>
							<td><a href="controller?command=listUser&updId=${bean.id}">Change</a></td>
						</c:otherwise>
					</c:choose>
					
					
			</c:forEach>
		</table>
	</form>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>