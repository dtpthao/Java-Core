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
import ua.nure.thao.SummaryTask4.db.entity.User;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class ListUserCommand extends Command {

	private static final long serialVersionUID = 7148788853001955268L;

	private static final Logger LOG = Logger.getLogger(ListUserCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		DBManager manager = DBManager.getInstance();

		List<User> listUser = manager.findAllUsers();
		LOG.debug("Found in DB: listUser --> " + listUser.size());

		String userId = request.getParameter("userId");
		LOG.trace("Request attribute: userId --> " + userId);
		String login = request.getParameter("login");
		LOG.trace("Request attribute: login --> " + login);
		String roleId = request.getParameter("roleId");
		LOG.trace("Request attribute: roleId --> " + roleId);
		String updId = request.getParameter("updId");
		LOG.trace("Request attribute: updId --> " + updId);

		User user;
		if (roleId == null || roleId.isEmpty() || "null".equals(roleId)) {} 
		else {
			listUser.clear();
			listUser.addAll(manager.findUserByRole(Integer.parseInt(roleId)));
			LOG.trace("Found in DB: users --> " + listUser.size());
		}

		if (userId == null || userId.isEmpty()) {
		} else {
			listUser.clear();
			user = manager.findUserById(Long.parseLong(userId));
			if (user != null)
				listUser.add(user);
			LOG.trace("Found in DB: users --> " + listUser.size());
		}

		if (login == null || login.isEmpty()) {
		} else {
			listUser.clear();
			user = manager.findUserByLogin(login);
			if (user != null)
				listUser.add(user);
			LOG.trace("Found in DB: users --> " + listUser.size());
		}

		request.setAttribute("updId", updId);
		LOG.trace("Set request attribute: updId --> " + updId);

		request.setAttribute("users", listUser);
		LOG.trace("Set request attribute: users --> " + listUser);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_USER;
	}
}
