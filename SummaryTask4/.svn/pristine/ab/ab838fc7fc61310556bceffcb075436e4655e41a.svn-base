<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="Edit" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<form id="test_info" accept-charset="UTF-8" action="controller">
		<div>
			<input type="hidden" name="command" value="updateTest" />
			<p style="color: red;">${errorString}</p>
			<table>
				<tr>
					<td><h4>Text</h4></td>
					<td><h4>
							<input name="text" value="${test.name}">
						</h4></td>
					<input type="hidden" name="testId" value="${test.id}">
				</tr>
				<tr>
					<td>Category</td>
					<td><select name="categoryId">
							<option value="${test.categoryId}">${test.getCategoryName(categories)}</option>
							<c:forEach var="cbean" items="${categories}">
								<option value="${cbean.id}">${cbean.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Level</td>
					<td><select name="levelId">
							<option value="${test.levelId}">${test.getLevelName(levels)}</option>
							<c:forEach var="bean" items="${levels}">
								<option value="${bean.id}">${bean.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Duration</td>
					<td><input type="number" name="duration" value="${test.duration}" min="1"></td>
					<td>minutes</td>
<!-- 					<td><select name="duration"> -->
<%-- 							<option>${test.duration}</option> --%>
<%-- 							<c:forEach begin='30' end='180' step='30' var='i'> --%>
<%-- 								<option>${i}</option> --%>
<%-- 							</c:forEach> --%>
<!-- 					</select></td> -->
				</tr>
			</table>
		</div>

		<div>
			<input type="submit" value="Save" /> <a
				href="controller?command=detailTest&testId=${test.id}">Finish</a>
		</div>

		<div>
			<h4>List Questions</h4>
		</div>

		<div id="add_question">
			<c:if test="${empty addQuestion}">
				<a
					href="controller?command=detailTest&testId=${test.id}&addQ=1&addA=1">Add
					Question</a>
			</c:if>

			<c:if test="${not empty addQuestion}">
				<%@ include file="/WEB-INF/jsp/admin/add_question.jsp"%>
			</c:if>
		</div>
		<h1></h1>
		<div id="edit_questions">
			<c:choose>
				<c:when test="${fn:length(test.questions) == 0}"> No such tests </c:when>

				<c:otherwise>
					<table border='1'>
						<tr>
							<td>№</td>
							<td>Name</td>
							<td></td>
							<td>Update</td>
							<td>Delete</td>
						</tr>
						<c:set var="i" value="0" />
						<c:forEach var="q" items="${test.questions}">
							<c:set var="i" value="${i+1}" />
							<c:choose>
								<c:when test="${q.id == qId}">
									<td><h4>${i}</h4></td>
									<td><h4>
											<input name="qUpdText" value="${q.name}">
										</h4> <input type="hidden" name="qText" value="${q.name}" /> <input
										type="hidden" name="qId" value="${q.id}" /> <input
										type="hidden" name="qcmd" value="0" />
									<td><input type="submit" value="Save" /></td>
									<td><a
										href="controller?command=editTest&testId=${test.id}">Cancel</a></td>
								</c:when>
								<c:otherwise>
									<tr>
										<td><h4>${i}</h4></td>
										<td><h4>${q.name}</h4></td>
										<td><a
											href="controller?command=editTest&testId=${test.id}&aQId=${q.id}">answer++</a></td>
										<td><a
											href="controller?command=editTest&testId=${test.id}&qId=${q.id}&qcmd=0">Edit</a></td>
										<td><a
											href="controller?command=updateTest&testId=${test.id}&qId=${q.id}&qcmd=1">Delete</a></td>
									</tr>
								</c:otherwise>
							</c:choose>

							<c:set var="j" value="0" />
							<c:forEach var="a" items="${q.answers}">
								<c:set var="j" value="${j+1}" />
								<c:choose>
									<c:when test="${a.id == aId}">
										<tr>
											<td>${i}.${j}</td>
											<td><input name="aUpdText" value="${a.name}"></td>
											<td><select name="udpcorrect">
													<option value="${a.correct}">${a.correct}</option>
													<option value="${!a.correct}">${!a.correct}</option>
											</select></td>
											<input type="hidden" name="aText" value="${a.name}" />
											<input type="hidden" name="aId" value="${a.id}" />
											<input type="hidden" name="acmd" value="0" />
<%-- 											<input type="hidden" name="aCor" value="${a.correct}" /> --%>
											<td><input type="submit" value="Save"></td>
											<td><a
												href="controller?command=editTest&testId=${test.id}">Cancel</a></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>${i}.${j}</td>
											<td>${a.name}</td>
											<td>${a.correct}</td>
											<td><a
												href="controller?command=editTest&testId=${test.id}&aId=${a.id}&acmd=0">Edit</a></td>
											<td><a
												href="controller?command=updateTest&testId=${test.id}&aId=${a.id}&acmd=1">Delete</a></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${q.id == aQId}">
									<input type="hidden" name="setCategory" value="${q.categoryId}" />
									<input type="hidden" name="qId" value="${q.id}" />
									<input type="hidden" name="qcmd" value="2" />
									<tr>
										<td></td>
										<td><input name="newAnswer"></intput></td>
										<td><select name="newCorrect">
												<option value='0'>false</option>
												<option value='1'>true</option>
										</select></td>
										<td><input type="submit" value="Save" /></td>
										<td><a
											href="controller?command=editTest&testId=${test.id}">Cancel</a></td>
								</c:when>
							</c:choose>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
		</div>


	</form>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>