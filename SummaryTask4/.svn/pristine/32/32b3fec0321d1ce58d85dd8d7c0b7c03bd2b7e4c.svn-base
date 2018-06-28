package ua.nure.thao.SummaryTask4.web.command.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class DeleteResultCommand extends Command {

	private static final long serialVersionUID = -7108898086064086073L;
	
	private static final Logger LOG = Logger.getLogger(DeleteResultCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		
		String utbeanId = request.getParameter("utbeanId");
		LOG.trace("Request attribute: utbeanId --> " + utbeanId);
		
		DBManager.getInstance().deleteUserTestBean(Long.parseLong(utbeanId));
		LOG.trace("Delete from DB: utbeanId --> " + utbeanId);
		
		String userId = request.getParameter("userId");
		LOG.trace("Request attribute: userId --> " + userId);
		request.setAttribute("userId", userId);
		LOG.trace("Set request attribute: userId --> " + userId);
		
		LOG.debug("Command finished");
		return Path.COMMAND_STUDENT_LIST_RESULTS;
	}

}
