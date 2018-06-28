<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR</title>
</head>
<body>

	<table id="main-container">
		
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		
		<tr>
			<td class="content">
				<p style="color: red;">${errorString}</p>
				<h2 class="error">
					The following error occurred
				</h2>
				
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.serlet.error.message']}"/>
				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
				
				<c:if test="${not empty code}">
					<h3>Error code: ${code}</h3>
				</c:if>
				
				<c:if test="${not empty message}">
					<h3>${message}</h3>
				</c:if>
				
				<c:if test="${not empty exception}">
					<% exception.printStackTrace(new PrintWriter(out)); %>
				</c:if>
				
				<c:if test="${not empty requestScope.errorMessage}">
					<h3>${requestScope.errorMessage}</h3>
				</c:if>
			</td>
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>

</body>
</html>