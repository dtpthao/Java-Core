package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class UpdateRoleCommand extends Command {

	private static final long serialVersionUID = 9135189926990855362L;
	
	private static final Logger LOG = Logger.getLogger(UpdateRoleCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		String roleId = request.getParameter("updRoleId");
		LOG.trace("Request attribute: roleId --> " + roleId);
		String userId = request.getParameter("updUserId");
		LOG.trace("Request attribute: userId --> " + userId);
		
		DBManager.getInstance().updateUser(Long.parseLong(userId), Integer.parseInt(roleId));
		LOG.trace("Update to DB: (userId, roleId) --> (" + userId + ", " + roleId + ")");
		
		LOG.debug("Command finished");
		return Path.COMMAND_LIST_USER;
	}
}
