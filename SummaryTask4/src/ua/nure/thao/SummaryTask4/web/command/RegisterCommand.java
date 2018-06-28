package ua.nure.thao.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.entity.User;
import ua.nure.thao.SummaryTask4.exception.AppException;

public class RegisterCommand extends Command {

	private static final long serialVersionUID = 416231069425788163L;
	
	private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		String firstName = request.getParameter("firstName");
		LOG.trace("Request attribute: firstName --> " + firstName);
		String lastName = request.getParameter("lastName");
		LOG.trace("Request attribute: lastName --> " + lastName);
		String login = request.getParameter("login");
		LOG.trace("Request attribute: login --> " + login);
		String Pwd = request.getParameter("Pwd");
		LOG.trace("Request attribute: inputPwd --> " + Pwd);
		String confPwd = request.getParameter("confPwd");
		LOG.trace("Request attribute: confPwd --> " + confPwd);
		
		String forward = Path.PAGE_REGISTER;
		String errorString;
		User user = new User();
		if (firstName == null || firstName.isEmpty()) {
			errorString = "First name cannot be empty";
			request.setAttribute("errorString", errorString);
			return forward;
		}
		else {
			user.setFirstName(firstName);
		}
		
		if (lastName == null || lastName.isEmpty()) {
			errorString = "Last name cannot be empty";
			request.setAttribute("errorString", errorString);
			return forward;
		}
		else {
			user.setLastName(lastName);
		}
		
		if (login == null || login.isEmpty()) {
			errorString = "Login cannot be empty";
			request.setAttribute("errorString", errorString);
			return forward;
		}
		else {
			user.setLogin(login);
			if (DBManager.getInstance().findUserByLogin(login) != null) {
				errorString = "Login has been registered";
				request.setAttribute("errorString", errorString);
				return forward;
			}
		}
		
		if (Pwd == null || Pwd.isEmpty()) {
			errorString = "Password cannot be empty";
			request.setAttribute("errorString", errorString);
			return forward;
		}
		else {
			if (confPwd == null || confPwd.isEmpty()) {
				errorString = "Confirm password please";
				request.setAttribute("errorString", errorString);
				return forward;
			} else {
				if (confPwd.equals(Pwd)) {
					user.setPassword(Pwd);
				} else {
					errorString = "passwords don't match";
					session.setAttribute("errorString", errorString);
					LOG.trace("Set request attribute: errorString --> " + errorString);
					return forward;
				}
			}
		}
		session.setAttribute("errorString", null);
		 DBManager.getInstance().insertUser(user);
		
		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}
