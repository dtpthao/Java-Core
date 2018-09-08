package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class DeleteTestCommand extends Command {

	private static final long serialVersionUID = 1970993719554765982L;
	
	private static final Logger LOG = Logger.getLogger(DeleteTestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		String testId = request.getParameter("testId");
		LOG.trace("Request parameter: testId --> " + testId);
		
		if (testId == null) {
			throw new AppException("Test Id cannot be empty");
		}
		
		DBManager manager = DBManager.getInstance();
		Test test = manager.findTestById(Long.parseLong(testId));
		LOG.trace("Found in DB: test --> testId: " 
				+ test.getId() + ", Text: " + test.getName());
		System.out.println(test);
		
		DBManager.getInstance().deleteTest(test.getId());
		LOG.trace("Delete from DB: test --> " + test);
		
		List<Test> tests = manager.findAllTest();
		LOG.trace("Found in DB: tests --> " + tests);
		request.setAttribute("tests", tests.size());
		LOG.trace("Set request attribute: tests --> " + tests.size());
		
		LOG.debug("Command finished.");
		return Path.COMMAND_ADMIN_LIST_TESTS;
	}
}
