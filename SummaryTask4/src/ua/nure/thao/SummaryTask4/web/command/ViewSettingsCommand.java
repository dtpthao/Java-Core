package ua.nure.thao.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.Path;
import ua.nure.thao.SummaryTask4.exception.AppException;

public class ViewSettingsCommand extends Command {
	
	private static final long serialVersionUID = 2523247625718219434L;
	
	private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		
		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}
