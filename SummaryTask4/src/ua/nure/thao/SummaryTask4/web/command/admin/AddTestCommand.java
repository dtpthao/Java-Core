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
import ua.nure.thao.SummaryTask4.db.entity.Category;
import ua.nure.thao.SummaryTask4.db.entity.Level;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class AddTestCommand extends Command{

	private static final long serialVersionUID = 4899688583015666320L;
	
	private static final Logger LOG = Logger.getLogger(AddTestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		
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
		
		LOG.debug("Command finished");
		return Path.PAGE_ADD_TEST;
	}

}
