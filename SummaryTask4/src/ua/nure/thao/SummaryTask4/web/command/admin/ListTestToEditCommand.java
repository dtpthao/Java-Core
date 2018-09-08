package ua.nure.thao.SummaryTask4.web.command.admin;

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
import ua.nure.thao.SummaryTask4.db.entity.Category;
import ua.nure.thao.SummaryTask4.db.entity.Level;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class ListTestToEditCommand extends Command {

	private static final long serialVersionUID = 3613001801839251466L;
	
	private static final Logger LOG = Logger.getLogger(ListTestToEditCommand.class);

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
		List<Test> tests;
		
		List<Level> levels = manager.findAllLevels();
		LOG.trace("Found in DB: levels --> " + levels);
		request.setAttribute("levels", levels);
		LOG.trace("Set the request attribute: levels --> " + levels);
		
		String categoryId = request.getParameter("categoryId");
		LOG.trace("Request parameter: categoryId --> " + categoryId);
		String sortByName = request.getParameter("sortByName");
		LOG.trace("Request parameter: sortByName --> " + sortByName);		
		String sortByLevel = request.getParameter("sortByLevel");
		LOG.trace("Request parameter: sortByLevel --> " + sortByLevel);		
		String sortByQuestions = request.getParameter("sortByQuestions");
		LOG.trace("Request parameter: sortByQuestions --> " + sortByQuestions);		
		
		if (categoryId == null || categoryId.isEmpty()) {
			tests = manager.findAllTest();
		}
		else {
			session.setAttribute("categoryId", categoryId);
			LOG.trace("Set the request attribute: categoryId --> " + categoryId);
			
			tests = manager.findTestsByCategory(Long.parseLong(categoryId));
			LOG.trace("Found in DB: tests --> " + tests.size());
		}
		
		if ("0".equals(sortByName)) {}
		else {
			if ("A-Z".equals(sortByName)) {
				Collections.sort(tests, new Comparator<Test>() {
					public int compare(Test t1, Test t2) {
						return t1.getName().compareTo(t2.getName());
					}
				});
			} else if ("Z-A".equals(sortByName)) {
				Collections.sort(tests, new Comparator<Test>() {
					public int compare(Test t1, Test t2) {
						return t2.getName().compareTo(t1.getName());
					}
				});
			}
		}
		
		if ("0".equals(sortByLevel)) {}
		else {
			if ("Easiest".equals(sortByLevel)) {
				Collections.sort(tests, new Comparator<Test>() {
					public int compare(Test t1, Test t2) {
						return t1.getLevelId() - t2.getLevelId();
					}
				});
			} else if ("Hardest".equals(sortByLevel)) {
				Collections.sort(tests, new Comparator<Test>() {
					public int compare(Test t1, Test t2) {
						return t2.getLevelId() - t1.getLevelId();
					}
				});
			}
		}
		
		if ("0".equals(sortByQuestions)) {}
		else {
			if ("Min".equals(sortByQuestions)) {
				Collections.sort(tests, new Comparator<Test>() {
					public int compare(Test t1, Test t2) {
						return t1.getNumberQuestions() - t2.getNumberQuestions();
					}
				});
			} else if ("Max".equals(sortByQuestions)) {
				Collections.sort(tests, new Comparator<Test>() {
					public int compare(Test t1, Test t2) {
						return t2.getNumberQuestions() - t1.getNumberQuestions();
					}
				});
			} 
		}
		session.setAttribute("sortByName", sortByName);
		LOG.trace("Set the request attribute: sortBy --> " + sortByName);
		
		session.setAttribute("sortByLevel", sortByLevel);
		LOG.trace("Set the request attribute: sortBy --> " + sortByLevel);
		
		session.setAttribute("sortByQuestions", sortByQuestions);
		LOG.trace("Set the request attribute: sortBy --> " + sortByQuestions);
		
		request.setAttribute("tests", tests);
		LOG.trace("Set the request attribute: tests --> " + tests.size());
		
		LOG.debug("Command finished");
		return Path.PAGE_ADMIN_LIST_TESTS;
	}
}
