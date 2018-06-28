<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<script language="JavaScript">
	window.onload = formSubmit;
	function auto_sub() {
		document.forms['test'].submit();
	}
</script>

<script language="javascript" type="text/javascript">
 	var i = ${duration * 60};
 	var j = 60;
 	function time() {
 		if (i >= 0) { 
 			document.getElementById("show").innerHTML = "Auto Submit in "
 				+ (i-(i%60))/60 + " : " + i%60 + "s.";
 			i--;
 			setTimeout("time()", 1000); 
 		} else { 
 			setTimeout("auto_sub()", 0); 
 		}
 	}
</script>

<body onload="time()">

	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	
	<div id="show"></div>
	
	<form id="test_info" action="controller" name="test">
		<input type="hidden" name="command" value="finishTest" /> 
		<input type="hidden" name="userId" value="${user.id}" /> 
		<input type="hidden" name="testId" value="${test.id}" />

		<div id="test_info">

			<h3>${test.name}</h3>

			<table>
				<tr>
					<td>UserID:</td>
					<td>${user.id}</td>
				</tr>
				<tr>
					<td>TestID :</td>
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
			<h4>List Questions</h4>
			<table>
				<tr>
					<td>№</td>
					<td>Name</td>
				</tr>
			</table>
			<c:set var="i" value="0" />
			<c:forEach var="q" items="${test.questions}">
				<c:set var="i" value="${i+1}" />
				<table>
					<td><h4>${i}</h4></td>
					<td><h4>${q.name}</h4></td>
				</table>
				<c:set var="j" value="0" />
				<c:forEach var="a" items="${q.answers}">
					<c:set var="j" value="${j+1}" />
					<input type="checkbox" name="${a.id}">${a.name}</input>
					<br />
				</c:forEach>
				<br />
			</c:forEach>
		</div>
		<div></div>
		<div>
			<input type="submit" value="Finish">
		</div>
	</form>
	<div></div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>