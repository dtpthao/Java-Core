<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="AllResult" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div>
		<form action="controller" method="post">
			<table>
				<input type="hidden" name="command" value="listResult" />
				<tr>
				<td>Subject</td>
				<td><select name="categoryId">
						<option></option>
						<c:forEach var="bean" items="${categories}">
							<option value="${bean.id}">${bean.name}</option>
						</c:forEach>
				</select></td>
				<td>Sort by: name</td>
				<td><select name="sortByName">
						<option value="0">${sortByName}</option>
						<option value="1">A->Z</option>
						<option value="2">Z-A</option>
				</select></td>
				<td>level <select name="sortByLevel">
						<option value="0">${sortByLevel}</option>
						<option value="1">Easiest</option>
						<option value="2">Hardest</option>
				</select></td>
				<td>questions<select name="sortByQuestions">
						<option value="0">${sortByQuestions}</option>
						<option value="1">Min</option>
						<option value="2">Max</option>
				</select></td>
				<td><input type="submit" value="search"></td>
			</tr>
				</br>
			</table>

			<table>
				<c:choose>
					<c:when test="${fn:length(utbeans) == 0}"> You have not done any test yet. </c:when>

					<c:otherwise>
						<table border='1'>
							<tr>
								<td>№</td>
								<td>Name</td>
								<td>Date</td>
								<td>Successed</td>
								<td>Status</td>
								<td></td>
							</tr>
							<c:forEach var="bean" items="${utbeans}">
								<tr>
									<td>${bean.id}</td>
									<td><a
										href="controller?command=viewResult&utbeanId=${bean.id}">${bean.test.name}</a>
										&nbsp;</td>
									<td>${bean.date}</td>
									<td>${bean.percent}</td>
									<td>
										<c:if test="${bean.statusId == 0}">Failed</c:if>
										<c:if test="${bean.statusId == 1}">Passed</c:if>
									</td>
									<td>
										<a href="controller?command=deleteResult&userId=${bean.userId}&utbeanId=${bean.id}">Delete</a>
										&nbsp;
									</td>
								</tr>
							</c:forEach>
							<tr>
									<td></td>
									<td></td>
									<td>Total</td>
									<td>${total}</td>
									<td></td>
									<td></td>
								</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</table>
			
		</form>
	</div>
	<div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>

</body>
</html>