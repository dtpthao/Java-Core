package ua.nure.thao.SummaryTask4.web.command.student;

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
import ua.nure.thao.SummaryTask4.db.entity.Category;
import ua.nure.thao.SummaryTask4.db.entity.Level;
import ua.nure.thao.SummaryTask4.db.entity.Question;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class sTestDetailCommand extends Command {

	private static final long serialVersionUID = -6711275881414076148L;

	private static final Logger LOG = Logger.getLogger(sTestDetailCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		String testId = request.getParameter("testId");
		LOG.trace("Request parameter: testId --> " + testId);

		if (testId == null) {
			throw new AppException("Test Id cannot be empty");
		}

		DBManager manager = DBManager.getInstance();
		Test test = manager.findTestById(Long.parseLong(testId));
		LOG.trace("Found in DB: test --> testId: " + test.getId() + ", Text: " + test.getName());
		Category category = manager.findCategoryById(test.getCategoryId());
		LOG.trace("Found in DB: category --> " + category);
		Level level = manager.findLevelById(test.getLevelId());
		LOG.trace("Found in DB: level --> " + level);

		session.setAttribute("category", category);
		LOG.trace("Set the request attribute: category --> " + category);

		session.setAttribute("level", level);
		LOG.trace("Set the request attribute: level --> " + level);

		session.setAttribute("test", test);
		LOG.trace("Set the request attribute: testId --> " + test.getId() + ", Text --> " + test.getName());

		LOG.debug("Command finished.");
		return Path.PAGE_USER_TEST_DETAIL;
	}

	public static Question AddQuestion(HttpServletRequest request, HttpServletResponse response, Test test) {

		HttpSession session = request.getSession();
		int addQuestion = 1;
		session.setAttribute("addQuestion", addQuestion);
		LOG.trace("Set the request attribute: addQuestion --> " + addQuestion);

		int addAnswer = Integer.parseInt(request.getParameter("addA"));
		session.setAttribute("addAnswer", addAnswer);
		LOG.trace("Set the request attribute: addAnswer --> " + addAnswer);

		String qText = request.getParameter("qText");
		System.out.println(qText);
		if (qText == null)
			return null;

		Question question = new Question(qText, test.getCategoryId(), test.getLevelId());
		List<Answer> aList = new ArrayList<>();
		for (int i = 0; i < addAnswer; i++) {
			String aText = request.getParameter("aText");
			Boolean correct = request.getParameter("correct") == "0" ? false : true;
			aList.add(new Answer(aText, test.getCategoryId(), correct));
		}
		question.setAnswers(aList);

		session.setAttribute("qText", qText);
		LOG.trace("Set the request attribute: qText --> " + qText);
		session.setAttribute("aList", aList);
		LOG.trace("Set the request attribute: aList --> " + aList);

		return question;
	}

}
