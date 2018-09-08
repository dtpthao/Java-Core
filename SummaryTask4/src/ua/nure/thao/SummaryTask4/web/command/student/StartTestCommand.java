package ua.nure.thao.SummaryTask4.web.command.student;

import java.io.IOException;

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

public class StartTestCommand extends Command {

	private static final long serialVersionUID = 2731155014365753864L;
	
	private static final Logger LOG = Logger.getLogger(StartTestCommand.class);

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
		LOG.trace("Found in DB: test --> " + test.basicInfo());
		
		session.setAttribute("test", test);
		LOG.trace("Set the request attribute: test --> " + test.basicInfo());
		
		request.setAttribute("duration", test.getDuration());
		
		LOG.debug("Command finished.");
		return Path.PAGE_START_TEST;
	}
}
