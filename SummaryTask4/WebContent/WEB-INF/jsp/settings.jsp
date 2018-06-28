<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<head>
<c:set var="title" value="settings" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>

	<table id="main-container">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<form action="changeLocale.jsp" method="post">
			<fmt:message key="settings_jsp.label.set_locale" />
			: <select name="locale">
				<c:forEach items="${applicationScope.locales}" var="locale">
					<c:set var="selected"
						value="${locale.key == currentLocale ? 'selected' : '' }" />
					<option value="${locale.key}" ${selected}>${locale.value}</option>
				</c:forEach>
			</select> <input type="submit"
				value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">

		</form>
<%-- 		<a href="index.jsp"><fmt:message --%>
<%-- 				key="settings_jsp.link.back_to_main_page"></fmt:message></a> --%>

		<form id="settings_form" action="controller" method="post">

			<table>
				<tr>
					<input type="hidden" name="command" value="updateSettings" />
					<input type="hidden" name="userId" value="${user.id}" />
					<input type="hidden" name="corPwd" value="${user.password}" />
				</tr>

<!-- 				<tr> -->
<!-- 					<td>Language</td> -->
<!-- 					<td><select name="locale"> -->
<!-- 							<option value="ru">Russian</option> -->
<!-- 							<option value="en">English</option> -->
<!-- 					</select></td> -->
<!-- 				</tr> -->

				<tr>
					<td>First name</td>
					<td><input name="firstName" value="${user.firstName}"></td>
				</tr>

				<tr>
					<td>Last name</td>
					<td><input name="lastName" value="${user.lastName}"></td>
				</tr>

				<tr>
					<td>Current Password</td>
					<td><input type="password" name="curPwd"></td>
					<td style="color: red;">${errorString}</td>
				</tr>

				<tr>
					<td>New Password</td>
					<td><input type="password" name="newPwd"></td>
				</tr>

				<tr>
					<td>Confirm Password</td>
					<td><input type="password" name="confPwd"></td>
				</tr>

				<tr>
					<td><input type="submit" value="Update"></td>
				</tr>

				<br />
			</table>

		</form>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</table>

</body>
</html>