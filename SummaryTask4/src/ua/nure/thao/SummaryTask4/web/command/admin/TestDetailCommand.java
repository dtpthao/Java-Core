package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.entity.Category;
import ua.nure.thao.SummaryTask4.db.entity.Level;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class TestDetailCommand extends Command{

	private static final long serialVersionUID = -6674207662302900275L;
	
	private static final Logger LOG = Logger.getLogger(TestDetailCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String testId = request.getParameter("testId");
		LOG.trace("Request parameter: testId --> " + testId);
		
		if (testId == null) {
			throw new AppException("Test Id cannot be empty");
		}
		
		DBManager manager = DBManager.getInstance();
		Test test = manager.findTestById(Long.parseLong(testId));
		LOG.trace("Found in DB: test --> testId: " 
				+ test.getId() + ", Text: " + test.getName());
		Category category = manager.findCategoryById(test.getCategoryId());
		LOG.trace("Found in DB: category --> " + category);
		Level level = manager.findLevelById(test.getLevelId());
		LOG.trace("Found in DB: level --> " + level);
		
		session.setAttribute("category", category);
		LOG.trace("Set the request attribute: category --> " + category);
		
		session.setAttribute("level", level);
		LOG.trace("Set the request attribute: level --> " + level);
		
		session.setAttribute("test", test);
		LOG.trace("Set the request attribute: testId --> " 
				+ test.getId() + ", Text --> " + test.getName());
		
		if (request.getParameter("addQ") != null && request.getParameter("addA") != null) {
			
			int addQuestion = 1;
			request.setAttribute("addQuestion", addQuestion);
			LOG.trace("Set the request attribute: addQuestion --> " + addQuestion);
			
			int addAnswer = Integer.parseInt(request.getParameter("addA"));
			request.setAttribute("addAnswer", addAnswer);
			LOG.trace("Set the request attribute: addAnswer --> " + addAnswer);
		}
		else {
			request.setAttribute("addQuestion", null);
			LOG.trace("Set the request attribute: addQuestion --> " + null);
		}
		
		LOG.debug("Command finished.");
		return Path.PAGE_TEST_DETAIL;
	}
}
