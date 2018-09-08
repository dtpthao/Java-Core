package ua.nure.thao.SummaryTask4.web.command.student;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.bean.UserTestBean;
import ua.nure.thao.SummaryTask4.db.entity.Answer;
import ua.nure.thao.SummaryTask4.db.entity.Question;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.db.entity.User;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class FinishTestCommand extends Command {

	private static final long serialVersionUID = 7503239267787708767L;
	
	private static final Logger LOG = Logger.getLogger(FinishTestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		DBManager manager = DBManager.getInstance();
		
		long userId = Long.parseLong(request.getParameter("userId"));
		LOG.trace("Request parameter: userId --> " + userId);
		
		long testId = Long.parseLong(request.getParameter("testId"));
		LOG.trace("Request parameter: testId --> " + testId);
		
		Test test = manager.findTestById(testId);
		LOG.trace("Found in DB: test(testId, name) --> " + test.basicInfo());
		session.setAttribute("test", test);
		LOG.trace("Set the request attribute: test --> " + test.basicInfo());
		
		User user = manager.findUserById(userId);
		LOG.trace("Found in DB: user(id, login) --> " + user.basicInfo());
		
		int result = test.getQuestions().size();
		for(Question q : test.getQuestions()) {
			for(Answer a : q.getAnswers()) {
				boolean check = "on".equals(request.getParameter(String.valueOf(a.getId())));
				if (check != a.isCorrect()) {
					result--;
					break;
				}
			}
		}
		session.setAttribute("result", result);
		LOG.trace("Set the request attribute: result --> " + result);
		System.out.println("result : " + result/test.getNumberQuestions());
		int statusId = ((float)result/test.getNumberQuestions()) >= 0.5 ? 1 : 0;
		String status = statusId == 1 ? "Passed" : "Failed";
		session.setAttribute("status", status);
		LOG.trace("Set the request attribute: status --> " + status);
		
		String date = (new Date()).toString();
		
		UserTestBean userTest = new UserTestBean(userId, test.getCategoryId(), testId, date, result, statusId);

		manager.insertUserTestBean(userTest);
		LOG.trace("Insert into DB: userTest --> " + userTest);
		
		LOG.debug("Command finished");
		return Path.COMMAND_RESULT_TEST + userTest.getId();
	}
}
