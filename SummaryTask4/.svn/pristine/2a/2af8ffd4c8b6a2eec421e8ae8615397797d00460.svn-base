package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.entity.Answer;
import ua.nure.thao.SummaryTask4.db.entity.Question;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class AddQuestionCommand extends Command {

	private static final long serialVersionUID = 929908691810726820L;
	
	private static final Logger LOG = Logger.getLogger(AddQuestionCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		session.setAttribute("addQuestion", null);
		LOG.trace("Set the request attribute: addQuestion --> " + null);
		
		int addAnswer = Integer.parseInt(request.getParameter("addA"));
		LOG.trace("Request attribute: addA --> " + addAnswer);
		
		String qText = request.getParameter("qText");
		LOG.trace("Request attribute: qText --> " + qText);
		System.out.println(qText);
		if (qText == null ) {};
		
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		session.setAttribute("categoryId", categoryId);
		LOG.trace("Set the request attribute: categoryId --> " + categoryId);
		
		int levelId = Integer.parseInt(request.getParameter("levelId"));
		session.setAttribute("levelId", levelId);
		LOG.trace("Set the request attribute: levelId --> " + levelId);
		
		int testId = Integer.parseInt(request.getParameter("testId"));
		session.setAttribute("testId", testId);
		LOG.trace("Set the request attribute: testId --> " + testId);
		
		Question question = new Question(qText, categoryId, levelId);
		List<Answer> aList = new ArrayList<>();
		for (int i = 1; i <= addAnswer; i++) {
			String aText = request.getParameter("aText" + i);
			LOG.trace("Request attribut: aText" + i + " --> " + aText);
			Boolean correct = (request.getParameter("correct" + i).equals("0")) ? false : true;
			LOG.trace("Request attribut: correct" + i + " --> " + correct);
			aList.add(new Answer(aText, categoryId, correct));
		}
		question.setAnswers(aList);
		
		session.setAttribute("qText", qText);
		LOG.trace("Set the request attribute: qText --> " + qText);
		session.setAttribute("aList", aList);
		LOG.trace("Set the request attribute: aList --> " + aList);
		
		DBManager manager = DBManager.getInstance();
		manager.insertQuestion(question);
		manager.insertTestQuestions(categoryId, testId, question.getId());
		LOG.trace("Insert to DB: question --> " + question.basicInfo());
		LOG.trace("Insert to DB: (test, question) --> (" + testId + ", " + question.getId() + ")");
		
		LOG.debug("Command finished");
		return Path.COMMAND_ADMIN_TEST_DETAIL + testId;
	}

}
