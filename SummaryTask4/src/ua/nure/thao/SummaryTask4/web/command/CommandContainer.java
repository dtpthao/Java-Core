package ua.nure.thao.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.web.command.admin.AddQuestionCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.AddTestCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.DeleteTestCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.EditTestCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.ListTestToEditCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.ListUserCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.SaveTestCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.StatisticResultsCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.TestDetailCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.UpdateRoleCommand;
import ua.nure.thao.SummaryTask4.web.command.admin.UpdateTestCommand;
import ua.nure.thao.SummaryTask4.web.command.student.DeleteResultCommand;
import ua.nure.thao.SummaryTask4.web.command.student.FinishTestCommand;
import ua.nure.thao.SummaryTask4.web.command.student.ListResultsCommand;
import ua.nure.thao.SummaryTask4.web.command.student.ListTestToDoCommand;
import ua.nure.thao.SummaryTask4.web.command.student.StartTestCommand;
import ua.nure.thao.SummaryTask4.web.command.student.ViewResultCommand;
import ua.nure.thao.SummaryTask4.web.command.student.sTestDetailCommand;

public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		commands.put("register", new RegisterCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("noCommand", new NoCommand());
		
		
		commands.put("listTestToEdit", new ListTestToEditCommand());
		commands.put("detailTest", new TestDetailCommand());
		commands.put("addTest", new AddTestCommand());
		commands.put("editTest", new EditTestCommand());
		commands.put("deleteTest", new DeleteTestCommand());
		commands.put("saveTest", new SaveTestCommand());
		commands.put("updateTest", new UpdateTestCommand());
		commands.put("addQuestion", new AddQuestionCommand());
		commands.put("listUser", new ListUserCommand());
		commands.put("updateRole", new UpdateRoleCommand());
		commands.put("statisticUsersResult", new StatisticResultsCommand());
		
		
		commands.put("listTestToDo", new ListTestToDoCommand());
		commands.put("detailTest1", new sTestDetailCommand());
		commands.put("startTest", new StartTestCommand());
		commands.put("finishTest", new FinishTestCommand());
		commands.put("viewResult", new ViewResultCommand());
		commands.put("listResult", new ListResultsCommand());
		commands.put("deleteResult", new DeleteResultCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}
	
	public static Command get(String commandName) {

		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}

}
