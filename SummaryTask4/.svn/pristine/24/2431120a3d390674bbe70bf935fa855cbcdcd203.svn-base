<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<head>
<c:set var="title" value="New Test" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<table id="main-container">
		<tr>
			<td class="content center">
				<p style="color: red;">${errorString}</p>
				<form action="controller" method="post">
					<div>
						<input type="hidden" name="command" value="saveTest" />
					</div>

					<div>
						<table>
							<tr>
								<td><h4>Text</h4></td>
								<td><h4><input name="text" /></h4></td>
								<br />
							</tr>
							<tr>
								<td>Category</td>
								<td><select name="categoryId">
										<c:forEach var="bean" items="${categories}">
											<option value="${bean.id}">${bean.name}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Level</td>
								<td><select name="levelId">
										<c:forEach var="bean" items="${levels}">
											<option value="${bean.id}">${bean.name}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Duration</td>
								<td><input type="number" name="duration" value='1' min="1"></td>
								<td>minutes</td>
							</tr>
						</table>
					</div>
					<div></div>
					<div>
						<h4><input type="submit" value="Save" /></h4>
					</div>
					<div>
						Input File : <input name="file">  (*.xml)
					</div>
				</form>
	</table>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>