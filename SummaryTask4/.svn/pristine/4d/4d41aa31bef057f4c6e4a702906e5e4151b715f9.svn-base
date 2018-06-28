package ua.nure.thao.SummaryTask4.web.command.student;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.bean.UserTestBean;
import ua.nure.thao.SummaryTask4.db.entity.Category;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class ListResultsCommand extends Command {

	private static final long serialVersionUID = 4391809318970484649L;
	
	private static final Logger LOG = Logger.getLogger(ListResultsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		DBManager manager = DBManager.getInstance();
		
		List<Category> categories = manager.findCategories();
		LOG.trace("Found in DB: categories --> " + categories);
		request.setAttribute("categories", categories);
		LOG.trace("Set the request attribute: categories --> " + categories);
		List<UserTestBean> userTests;
		
		String categoryId = request.getParameter("categoryId");
		LOG.trace("Request parameter: categoryId --> " + categoryId);
		String sortByName = request.getParameter("sortByName");
		LOG.trace("Request parameter: sortByName --> " + sortByName);		
		String sortByLevel = request.getParameter("sortByLevel");
		LOG.trace("Request parameter: sortByLevel --> " + sortByLevel);		
		String sortByQuestions = request.getParameter("sortByQuestions");
		LOG.trace("Request parameter: sortByQuestions --> " + sortByQuestions);		
		
		String userId = request.getParameter("userId");
		
		if (categoryId == null || categoryId.isEmpty()) {
			userTests = manager.findUserTestBeans(Long.parseLong(userId));
		}
		else {
			session.setAttribute("categoryId", categoryId);
			LOG.trace("Set the request attribute: categoryId --> " + categoryId);
			
			userTests = manager.findUserTestBeansByCategory(Long.parseLong(userId), Integer.parseInt(categoryId));
			LOG.trace("Found in DB: userTestBeans --> " + userTests.size());
		}
		float total = 0;
		for (UserTestBean utbean : userTests) {
			utbean.setTest(manager.findTestById(utbean.getTestId()));
			total += utbean.getPercent();
		}
		
		request.setAttribute("total", total/userTests.size());
		LOG.trace("Set request attribute: total --> " + total);
		
		if ("0".equals(sortByName)) {}
		else {
			if ("1".equals(sortByName)) {
				Collections.sort(userTests, new Comparator<UserTestBean>() {
					@Override
					public int compare(UserTestBean o1, UserTestBean o2) {
						return o1.getTest().getName().compareTo(o2.getTest().getName());
					}
				});
			} else if ("2".equals(sortByName)) {
				Collections.sort(userTests, new Comparator<UserTestBean>() {
					@Override
					public int compare(UserTestBean o1, UserTestBean o2) {
						return o2.getTest().getName().compareTo(o1.getTest().getName());
					}
				});
			}
		}
		
		if ("0".equals(sortByLevel)) {}
		else {
			if ("1".equals(sortByLevel)) {
				Collections.sort(userTests, new Comparator<UserTestBean>() {
					@Override
					public int compare(UserTestBean o1, UserTestBean o2) {
						return o1.getTest().getLevelId() - o2.getTest().getLevelId();
					}
				});
			} else if ("2".equals(sortByLevel)) {
				Collections.sort(userTests, new Comparator<UserTestBean>() {
					@Override
					public int compare(UserTestBean o1, UserTestBean o2) {
						return o2.getTest().getLevelId() - o1.getTest().getLevelId();
					}
				});
			}
		}
		
		if ("0".equals(sortByQuestions)) {}
		else {
			if ("1".equals(sortByQuestions)) {
				Collections.sort(userTests, new Comparator<UserTestBean>() {
					@Override
					public int compare(UserTestBean o1, UserTestBean o2) {
						return o1.getTest().getNumberQuestions() - o2.getTest().getNumberQuestions();
					}
				});
			} else if ("2".equals(sortByQuestions)) {
				Collections.sort(userTests, new Comparator<UserTestBean>() {
					@Override
					public int compare(UserTestBean o1, UserTestBean o2) {
						return o2.getTest().getNumberQuestions() - o1.getTest().getNumberQuestions();
					}
				});
			} 
		}
		
		session.setAttribute("sortByName", sortByName);
		LOG.trace("Set the request attribute: sortByName --> " + sortByName);
		
		session.setAttribute("sortByLevel", sortByLevel);
		LOG.trace("Set the request attribute: sortByLevel --> " + sortByLevel);
		
		session.setAttribute("sortByQuestions", sortByQuestions);
		LOG.trace("Set the request attribute: sortByQuestion --> " + sortByQuestions);
		
		request.setAttribute("utbeans", userTests);
		LOG.trace("Set the request attribute: usertestbeans --> " + userTests.size());
		
		LOG.debug("Command finished");
		return Path.PAGE_STUDENT_LIST_RESULTS;
	}
}
