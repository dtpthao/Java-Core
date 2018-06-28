<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="All test" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<form action="controller" method="post">
	<input type="hidden" name="command" value="listTestToEdit" />
		
		<table>
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
						<option value="A-Z">A->Z</option>
						<option value="Z-A">Z-A</option>
				</select></td>
				<td>level <select name="sortByLevel">
						<option value="0">${sortByLevel}</option>
						<option value="Easiest">Easiest</option>
						<option value="Hardest">Hardest</option>
				</select></td>
				<td>questions<select name="sortByQuestions">
						<option value="0">${sortByQuestions}</option>
						<option value="Min">Min</option>
						<option value="Max">Max</option>
				</select></td>
				<td><input type="submit" value="search"></td>
			</tr>
			</br>
		</table>
		<table>
			<a href="controller?command=addTest">Add Test</a>
		</table>
		<table>
			<c:choose>
				<c:when test="${fn:length(tests) == 0}"> No such tests </c:when>
				<c:otherwise>
					<table border='1'>
						<tr>
							<td>№</td>
							<td>Name</td>
							<td>Category</td>
							<td>Level</td>
							<td>Questions</td>
							<td>Edit</td>
							<td>Delete</td>
						</tr>
						<c:forEach var="bean" items="${tests}">
						<input type="hidden" name="testId" value="${bean.id}"/>
							<tr>
								<td>${bean.id}</td>
								<td><a
									href="controller?command=detailTest&testId=${bean.id}">${bean.name}</a></td>
								<td>${bean.getCategoryName(categories)}</td>
								<td>${bean.getLevelName(levels)}</td>
								<td>${bean.numberQuestions}</td>
								<td><a href="controller?command=editTest&testId=${bean.id}">Edit</a></td>
								<td><a
									href="controller?command=deleteTest&testId=${bean.id}">Delete</a></td>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</table>
		
	</form>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>