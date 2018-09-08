package ua.nure.thao.SummaryTask4.web.command.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.bean.UserTestBean;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class ViewResultCommand extends Command{

	private static final long serialVersionUID = -2894937770315590321L;
	
	private static final Logger LOG = Logger.getLogger(ViewResultCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		
		String UTBeanId = request.getParameter("utbeanId");
		LOG.trace("Request parameter: UTBeanId --> " + UTBeanId);
		
		if (UTBeanId == null) {
			throw new AppException("UTBeanId cannot be empty");
		}
		
		UserTestBean utbean = manager.findUserTestBean(Long.parseLong(UTBeanId));
		LOG.trace("Found in DB: utbean --> " + utbean);
		session.setAttribute("utbean", utbean);
		LOG.trace("Set request attribute: utbean --> " + utbean.basicInfo());
		
		utbean.setTest(manager.findTestById(utbean.getTestId()));
		LOG.trace("Found in DB: test --> " + utbean.getTest().basicInfo());
		
		String level = manager.findLevelById(utbean.getTest().getLevelId()).getName();
		LOG.trace("Found in DB: level --> " + level);
		session.setAttribute("level", level);
		LOG.trace("Set request attribute: level --> " + level);
		
		String category = manager.findCategoryById(utbean.getCategoryId()).getName();
		LOG.trace("Found in DB: category --> " + category);
		session.setAttribute("category", category);
		LOG.trace("Set request attribute: category --> " + category);
		
		LOG.debug("Command finished");
		return Path.PAGE_RESULT_TEST;
	}
}
