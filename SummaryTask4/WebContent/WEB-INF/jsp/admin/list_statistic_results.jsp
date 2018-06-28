<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="AllResult" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="adminListResults" />

		<table border='1'>
			<tr>
				<td>№</td>
				<td>UserID</td>
				<td>Login</td>
				<td>Successed %</td>
				<td>Number tests</td>
			</tr>
			<c:set var='i' value='0' />
			<c:forEach var="bean" items="${users}">
				<c:set var="i" value="${i+1}" />
				<tr>
					<td>${i}</td>
					<td>${bean.id}</td>
					<td>${bean.login}</td>
					<td>${bean.avgPercent}</td>
					<td>${bean.utbeans.size()}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>

</body>
</html>