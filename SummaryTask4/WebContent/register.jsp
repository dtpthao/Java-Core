<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="Register" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	
	<table id="main-container">

		<div style="background: #E0E0E0; height: 55px; padding: 5px;">
			<div style="float: left">
				<h1>Student Test</h1>
			</div>
		</div>
		<h3>Provide all the fields for registration.</h3>
		<tr>
			<td class="content center">
				<p style="color: red;">${errorString}</p>
				<form id="login_form" action="controller" method="post">
					<input type="hidden" name="command" value="register" />

					<table>
						<tr>
							<td><strong>First Name</strong>:</td>
							<td><input type="text" name="firstName"></td>
						<tr>
							<td><strong>Last Name</strong>:</td>
							<td><input type="text" name="lastName"></td>
						</tr>
						<tr>
							<td><strong>Login</strong>:</td>
							<td><input type="text" name="login"></td>
						</tr>
						<tr>
							<td><strong>Password</strong>:</td>
							<td><input type="password" name="Pwd"></td>
						</tr>
						<tr>
							<td><strong>Confirm Password</strong>:</td>
							<td><input type="password" name="confPwd"></td>
						</tr>
					</table>
					<input type="submit" value="Register">
				</form>

			</td>
		</tr>
		<tr>
			If you are registered user, please
			<a href="login.jsp">login</a>.
		</tr>
	</table>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>