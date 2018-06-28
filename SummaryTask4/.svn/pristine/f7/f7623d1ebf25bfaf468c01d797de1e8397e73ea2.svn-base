package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.entity.Answer;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.exception.DBException;
import ua.nure.thao.SummaryTask4.exception.Messages;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class UpdateTestCommand extends Command {

	private static final long serialVersionUID = -1258418003914065804L;

	private static final Logger LOG = Logger.getLogger(UpdateTestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		request.setCharacterEncoding("UTF-8");

		String testId = request.getParameter("testId");
		LOG.trace("Request attribute: testId --> " + testId);
		String qId = request.getParameter("qId");
		LOG.trace("Request attribute: qId --> " + qId);
		String aId = request.getParameter("aId");
		LOG.trace("Request attribute: aId --> " + aId);
		String qcmd = request.getParameter("qcmd");
		LOG.trace("Request attribute: qcmd --> " + qcmd);
		String acmd = request.getParameter("acmd");
		LOG.trace("Request attribute: acmd --> " + acmd);

		if (aId == null || aId.isEmpty()) {
			if (qId == null || qId.isEmpty()) {
				updateTest(request, response, Long.parseLong(testId));
			}
			else {
				if ("0".equals(qcmd)) {
					updateQuestion(request, response, Long.parseLong(qId));
				}
				else if ("1".equals(qcmd)){
					DBManager.getInstance().deleteQuestion(Long.parseLong(qId));
					LOG.trace("Delete in DB: question --> qId=" + qId);
					
				} else if ("2".equals(qcmd)){
					String newA = request.getParameter("newAnswer");
					LOG.trace("Request attribute: newAnswer --> " + newA);
					String corr = request.getParameter("newCorrect");
					LOG.trace("Request attribute: newCorrect --> " + corr);
					String categoryId = request.getParameter("setCategory");
					LOG.trace("Request attribute: aCategory --> " + categoryId);

					Answer a= new Answer();
					a.setCategoryId(Integer.parseInt(categoryId));
					a.setQuestionId(Long.parseLong(qId));
					a.setName(newA);
					a.setCorrect(corr.equals("true"));
					System.out.println(a.toString());
					DBManager.getInstance().insertAnswer(Long.parseLong(qId), a);
					LOG.trace("Insert in DB: answer --> " + a);
				}
				request.setAttribute("qId", null);
				LOG.trace("Set request attribute: qId --> " + qId);
				request.setAttribute("qcmd",null);
				LOG.trace("Set request attribute: qcmd --> " + qcmd);
			}
		} else {
			if ("0".equals(acmd)) {
				updateAnswer(request, response, Long.parseLong(aId));
			}
			else {
				DBManager.getInstance().deleteAnswer(Long.parseLong(aId));
				LOG.trace("Delete in DB: answer --> aId=" + aId);
			}
			request.setAttribute("aId", null);
			LOG.trace("Set request attribute: aId --> " + aId);
			request.setAttribute("acmd", null);
			LOG.trace("Set request attribute: acmd --> " + acmd);
		}
		
		LOG.trace("Command finished");
		return Path.COMMAND_EDIT_TEST + testId;
	}

	public static void updateTest(HttpServletRequest request, HttpServletResponse response, long testId) throws DBException {
		
		String errorString;
		String text = (String) request.getParameter("text");
		LOG.trace("Request parameter: text --> " + text);
		
		if (text == null || text.isEmpty()) {
			errorString = "<font color=red>Text cannot be empty.</font>";
			request.setAttribute("errorString", errorString);
			LOG.error(Messages.ERR_EMPTY_TEST_TEXT);
		} else {
			Test test = DBManager.getInstance().findTestById(testId);
			LOG.trace("Found in DB: test --> " + test.basicInfo());
			
			Test testUpdate = new Test();
			testUpdate.setId(test.getId());
			testUpdate.setName(text);
			testUpdate.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			LOG.trace("Request parameter: categoryId --> " + testUpdate.getCategoryId());

			testUpdate.setLevelId(Integer.parseInt(request.getParameter("levelId")));
			LOG.trace("Request parameter: levelId --> " + testUpdate.getLevelId());

			testUpdate.setDuration(request.getParameter("duration"));
			LOG.trace("Request parameter: duration --> " + testUpdate.getDuration());
			
			if (!testUpdate.equals(test)) {
				DBManager.getInstance().updateTest(testUpdate);
				LOG.trace("Update to DB: testUpdate --> " + testUpdate.basicInfo());
			}
		}
	}
	
	public static void updateQuestion(HttpServletRequest request, HttpServletResponse response, long qId) throws DBException {
		
		String qText = request.getParameter("qText");
		LOG.trace("Request attribute: qText --> " + qText);
		String updText = request.getParameter("qUpdText");
		LOG.trace("Request attribute: qUpdText --> " + updText);
		
		if (!qText.equals(updText)) {
			DBManager.getInstance().updateQuestion(qId, updText);
			LOG.trace("Update to DB: questionUpdate --> (" + qId + ", " + updText + ")");
		}
	}
	
	public static void updateAnswer(HttpServletRequest request, HttpServletResponse response, long aId) throws DBException {
		String aText = request.getParameter("aText");
		LOG.trace("Request attribute: aText --> " + aText);
		String updText = request.getParameter("aUpdText");
		LOG.trace("Request attribute: aUpdText --> " + updText);
		
		Boolean correct = "true".equals(request.getParameter("udpcorrect"));
		request.setAttribute("udpcorrect", correct);
		LOG.trace("Request attribute: correct --> " + correct);
		DBManager.getInstance().updateAnswer(aId, updText, correct);
		LOG.trace("Update to DB: answerUpdate --> (" + aId + ", " + updText + ")");
		
	}
}
