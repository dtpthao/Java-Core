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

public class UpdateSettingsCommand extends Command{

	private static final long serialVersionUID = -8875898920957431446L;
	
	private static final Logger LOG = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		String errorString = null;
		
		
		
		String userId = request.getParameter("userId");
		LOG.trace("Request attribute: userId --> " + userId);
		String firstName = request.getParameter("firstName");
		LOG.trace("Request attribute: firstName --> " + firstName);
		String lastName = request.getParameter("lastName");
		LOG.trace("Request attribute: lastName --> " + lastName);
		String corPwd = request.getParameter("corPwd");
		LOG.trace("Request attribute: correctPwd --> " + corPwd);
		String curPwd = request.getParameter("curPwd");
		LOG.trace("Request attribute: inputPwd --> " + curPwd);
		String newPwd = request.getParameter("newPwd");
		LOG.trace("Request attribute: newPwd --> " + newPwd);
		String confPwd = request.getParameter("confPwd");
		LOG.trace("Request attribute: confPwd --> " + confPwd);
		
		DBManager manager = DBManager.getInstance();
		
		if (!curPwd.equals(corPwd)) {
			errorString =  "password incorrect";
			session.setAttribute("errorString", errorString);
			LOG.trace("Set request attribute: errorString --> " + errorString);
			LOG.error("Password incorrect");
			return Path.PAGE_SETTINGS;
		}
		
		User user = manager.findUserById(Long.parseLong(userId));
		LOG.trace("Found in DB: user --> " + user.basicInfo());
		
		if (firstName == null || firstName.isEmpty()) {}
		else {
			user.setFirstName(firstName);
		}
		
		if (lastName == null || lastName.isEmpty()) {}
		else {
			user.setLastName(lastName);
		}
		
		if (newPwd == null || newPwd.isEmpty()) {}
		else {
			if (confPwd.equals(newPwd)) {
				user.setPassword(newPwd);
			} else {
				errorString = "passwords don't match";
				session.setAttribute("errorString", errorString);
				LOG.trace("Set request attribute: errorString --> " + errorString);
				return Path.PAGE_SETTINGS;
			}
		}
		
		errorString = "";
		session.setAttribute("errorString", errorString);
		LOG.trace("Set request attribute: errorString --> " + errorString);
		
		String result;
		if (manager.updateUser(user)) {
			result = " successfully";
			session.setAttribute("user", user);
		}
		else result = " failed";
		LOG.trace("Update to DB: user --> " + user + result);
		
		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_SETTINGS;
	}

}
