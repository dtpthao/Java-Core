package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import ua.nure.thao.SummaryTask4.exception.Messages;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class EditTestCommand extends Command {

	private static final long serialVersionUID = -6674207662302900275L;
	
	private static final Logger LOG = Logger.getLogger(EditTestCommand.class);

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
		
		/*
		 * Option categories
		 */
		List<Category> categories = manager.findCategories();
		LOG.trace("Found in DB: categories --> " + categories);
		request.setAttribute("categories", categories);
		LOG.trace("Set the request attribute: categories --> " + categories);
		
		/*
		 * Option levels
		 */
		List<Level> levels = manager.findAllLevels();
		LOG.trace("Found in DB: levels --> " + levels);
		request.setAttribute("levels", levels);
		LOG.trace("Set the request attribute: levels --> " + levels);
		
		Test test = manager.findTestById(Long.parseLong(testId));
		LOG.trace("Found in DB: test --> test: " + test.basicInfo());
		
		session.setAttribute("test", test);
		LOG.trace("Set the session attribute: test --> " + test.basicInfo());
		
		String editT = request.getParameter("editT");
		String qId = request.getParameter("qId");
		String aQId = request.getParameter("aQId");
		String aId = request.getParameter("aId");
		session.setAttribute("qId", qId);
		session.setAttribute("aId", aId);
		session.setAttribute("aQId", aQId);
		session.setAttribute("editT", editT);
		
		LOG.debug("Command finished.");
		return Path.PAGE_EDIT_TEST;
	}

}
