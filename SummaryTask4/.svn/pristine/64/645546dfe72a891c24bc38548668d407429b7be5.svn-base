<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<h4>New Question</h4>
<table>
	<tr>
		<td>Text</td>
		<td><input name="qText" /></td>
	</tr>
	<br />
	<tr>
		<td>testId:</td>
		<td>${test.id}</td>
		<input type="hidden" name="testId" value="${test.id}" />
	</tr>
	<tr>
		<td>categoryId:</td>
		<td>${test.categoryId}</td>
		<input type="hidden" name="categoryId" value="${test.categoryId}" />
		<input type="hidden" name="levelId" value="${test.levelId}" />
	</tr>

	<c:forEach var='i' begin='1' end="${addAnswer}">
		<input type="hidden" name="addA" value="${addAnswer}" />
		<tr>
			<td>Answer</td>
			<td><input name="aText${i}" /></td>
			<td><select name="correct${i}">
					<option value='0'>false</option>
					<option value='1'>true</option>
			</select></td>
		</tr>
	</c:forEach>

	<tr>
		<c:choose>
			<c:when test="${addAnswer == 1 }"></c:when>
			<c:otherwise>
				<td><a
					href="controller?command=detailTest&testId=${test.id}&addQ=1&addA=${addAnswer - 1}">--answer</a></td>
			</c:otherwise>
		</c:choose>
		<td><a
			href="controller?command=detailTest&testId=${test.id}&addQ=1&addA=${addAnswer + 1}">answer++</a></td>
	</tr>
	<tr>
		<td><input type="submit" value="Add" /></td>
		<td><a
			href="controller?command=detailTest&testId=${test.id}&addQ=0">Cancel</a></td>
	</tr>
</table>