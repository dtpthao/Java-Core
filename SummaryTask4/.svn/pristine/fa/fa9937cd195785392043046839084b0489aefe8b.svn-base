package ua.nure.thao.SummaryTask4.web.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.db.DBManager;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.db.xml.DOMController;
import ua.nure.thao.SummaryTask4.exception.AppException;
import ua.nure.thao.SummaryTask4.exception.Messages;
import ua.nure.thao.SummaryTask4.web.command.Command;

public class SaveTestCommand extends Command{

	private static final long serialVersionUID = 7749139876786188880L;
	
	private static final Logger LOG = Logger.getLogger(SaveTestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		String errorString;
		String forward = Path.COMMAND_ADMIN_TEST_DETAIL;
		
		Test test = new Test();
		
		String fileName = request.getParameter("file");
		LOG.trace("Request attribute: fileName --> " + fileName);
		
		if (fileName == null || fileName.isEmpty()) {
			String text = (String) request.getParameter("text");
			LOG.trace("Request parameter: text --> " + text);
			
			if (text == null || text.isEmpty()) {
				errorString = "Text cannot be empty.";
				request.setAttribute("errorString", errorString);
				LOG.error(Messages.ERR_EMPTY_TEST_TEXT);
				return Path.COMMAND_ADD_TEST;
			}
			else {
				test.setName(text);
				test.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
				LOG.trace("Request parameter: categoryId --> " + test.getCategoryId());
				
				test.setLevelId(Integer.parseInt(request.getParameter("levelId")));
				LOG.trace("Request parameter: levelId --> " + test.getLevelId());
				
				test.setDuration(request.getParameter("duration"));
				LOG.trace("Request parameter: duration --> " + test.getDuration());
				
			}
		}
		else {
			if (fileName.contains(".xml")) {
				DOMController domControl = new DOMController(fileName);
				try {
					domControl.parse(true);
				} catch (Exception ex) {
					errorString = "Error reading file.";
					request.setAttribute("errorString", errorString);
					LOG.error(Messages.ERR_DOM_READING_FILE);
					return Path.COMMAND_ADD_TEST;
				}
				test = domControl.getTest();
				
			} else {
				errorString = "Type file invalid.";
				request.setAttribute("errorString", errorString);
				LOG.error(Messages.ERR_DOM_TYPE_FILE_INVALID);
				return Path.COMMAND_ADD_TEST;
			}
		}
		
		request.setAttribute("errorString", null);
		
		DBManager.getInstance().creatTest(test);
		LOG.trace("Insert test to DB: test --> " + test);
		
		forward += test.getId();
		
		try {
			DOMController.saveToXML(test, String.valueOf(test.getId()) + test.getName() + ".xml");
		} catch (ParserConfigurationException | TransformerException e) {} 
		
		LOG.debug("Command finished");
		return forward;
	}

}
