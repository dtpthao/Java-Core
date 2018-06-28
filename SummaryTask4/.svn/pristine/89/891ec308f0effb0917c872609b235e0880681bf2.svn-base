<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="login" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<div style="background: #E0E0E0; height: 55px; padding: 5px;">
			<div style="float: left">
				<h1>Student Test</h1>
			</div>
		</div>
		<tr>
			<td class="content center">
				<p style="color: red;">${errorString}</p>
				<form id="login_form" action="controller" method="post">

					<input type="hidden" name="command" value="login" />

					<fieldset>
						<legend>Login</legend>
						<input name="login" /><br />
					</fieldset>
					<br />

					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password" />
					</fieldset>
					<br /> <input type="submit" value="Login">

				</form>

			</td>
		</tr>
		<tr>
			If you are new user, please
			<a href="register.jsp">register</a>.
		</tr>
	</table>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>