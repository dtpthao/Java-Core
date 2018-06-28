<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<td class="content">
		<form id="test_info" action="controller">
			<input type="hidden" name="command" value="detailTest1" />
			<div id="test_info">
				<h3>
					<a href="controller?command=detailTest1&testId=${test.id}">${test.name}</a>
				</h3>
				<table>
					<tr>
						<td>ID :</td>
						<td>${test.id}</td>
					</tr>
					<tr>
						<td>Category :</td>
						<td>${category.name}</td>
					</tr>
					<tr>
						<td>Level :</td>
						<td>${level.name}</td>
					</tr>
					<tr>
						<td>Questions:</td>
						<td>${test.questions.size()}</td>
					</tr>
					<tr>
						<td>Duration :</td>
						<td>${test.duration}</td>
					</tr>
				</table>
			</div>
			
			<div>
			<c:choose>
				<c:when test="${fn:length(test.questions) == 0 }"> Test has no question </c:when>
				<c:otherwise><h4></h4>
					<h4><a href="controller?command=startTest&testId=${test.id}">Start</a></h4>
				</c:otherwise>
			</c:choose>
			</div>

		</form>
	</td>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>