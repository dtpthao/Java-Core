<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<td class="content">
		<form id="test_info" action="controller">
			<input type="hidden" name="command" value="detailTest1" />
			<div id="test_info">
				<h3>
					<a href="controller?command=viewResult&utbeanId=${utbean.id}">${test.name}</a>
				</h3>
				<table>
					<tr>
						<td>ID :</td>
						<td>${utbean.id}</td>
					</tr>
					<tr>
						<td>UserID :</td>
						<td>${utbean.userId}</td>
					</tr>
					<tr>
						<td>Category :</td>
						<td>${category}</td>
					</tr>
					<tr>
						<td>Level :</td>
						<td>${level}</td>
					</tr>
					<tr>
						<td>Date:</td>
						<td>${utbean.date}</td>
					</tr>
					<tr>
						<td>Result:</td>
						<td>${utbean.result}/${utbean.test.numberQuestions}</td>
					</tr>
					<tr>
						<td>Status :</td>
						<td>
							<c:if test="${utbean.statusId == 0}">Failed</c:if>
							<c:if test="${utbean.statusId == 1}">Passed</c:if>
						</td>
					</tr>
				</table>
			</div>

			<div>
				<a href="controller?command=detailTest1&testId=${utbean.test.id}">&lt Go to test</a>
			</div>
		
		</form>
		<div></div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</td>
</body>
</html>