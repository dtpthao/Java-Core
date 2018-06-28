package ua.nure.thao.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.Role;
import ua.nure.thao.SummaryTask4.db.entity.User;
import ua.nure.thao.SummaryTask4.exception.AppException;

@WebServlet
public class LoginCommand extends Command {
	
	private static final long serialVersionUID = 2795417386378115180L;
	
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		String errorString;
		
		String forward = Path.PAGE_LOGIN;
		
		DBManager manager = DBManager.getInstance();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);
		
		String pwd = request.getParameter("password");
		if (login == null || pwd == null || login.isEmpty() || pwd.isEmpty())	{
			errorString = "Login/password cannot be empty";
			request.setAttribute("errorString", errorString);
		}
		else {
			User user = manager.findUserByLogin(login);
			LOG.trace("Found in DB: user --> " + user);
			
			if (user == null || !pwd.equals(user.getPassword())) {
				errorString = "Cannot find user with such login/password";
				request.setAttribute("errorString", errorString);
			}
			else {
				Role userRole = Role.getRole(user);
				System.out.println(userRole);
				LOG.trace("userRole --> " + userRole);
				
				if (userRole == Role.ADMIN) {
					forward = Path.COMMAND_ADMIN_LIST_TESTS;
				}
				
				if (userRole == Role.STUDENT) {
					forward = Path.COMMAND_STUDENT_LIST_TESTS;
				}
				
				if (userRole == Role.BLOCK) {
					errorString = "Incorrect login or password.";
					request.setAttribute("errorString", errorString);
					return forward;
				}
				
				session.setAttribute("user", user);
				LOG.trace("Set the session attribute: user --> " + user);
				session.setAttribute("userRole", userRole);
				LOG.trace("Set the session attribute: userRole --> " + userRole);
				
				LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());
			}
		}
		
		LOG.debug("Command finished");
		return forward;
	}
}
