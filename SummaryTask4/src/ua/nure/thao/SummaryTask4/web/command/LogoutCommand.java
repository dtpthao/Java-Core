package ua.nure.thao.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.exception.AppException;

public class LogoutCommand extends Command {

	private static final long serialVersionUID = -9010292395507689146L;
	
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}
}
