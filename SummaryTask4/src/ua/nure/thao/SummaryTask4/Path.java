package ua.nure.thao.SummaryTask4;

public final class Path {

	public static final String PAGE_LOGIN = "/login.jsp";
	
	public static final String PAGE_REGISTER = "/register.jsp";
	
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	
	/*
	 * Admin
	 */
	public static final String PAGE_ADMIN_LIST_TESTS = "/WEB-INF/jsp/admin/list_edit_test.jsp";
	
	public static final String PAGE_TEST_DETAIL = "/WEB-INF/jsp/admin/admin_test_detail.jsp";
	
	public static final String PAGE_ADD_TEST = "/WEB-INF/jsp/admin/add_test.jsp";
	
	public static final String PAGE_EDIT_TEST = "/WEB-INF/jsp/admin/edit_test.jsp";
	
	public static final String PAGE_LIST_USER = "/WEB-INF/jsp/admin/list_user.jsp";
	
	public static final String PAGE_ALL_USER_TEST_BEAN = "/WEB-INF/jsp/admin/list_statistic_results.jsp";
	
	/*
	 * Student
	 */
	public static final String PAGE_STUDENT_LIST_TESTS = "/WEB-INF/jsp/student/list_do_test.jsp";
	
	public static final String PAGE_USER_TEST_DETAIL = "/WEB-INF/jsp/student/student_test_detail.jsp";
	
	public static final String PAGE_START_TEST = "/WEB-INF/jsp/student/start_test.jsp";
	
	public static final String PAGE_RESULT_TEST = "/WEB-INF/jsp/student/result_user_test.jsp";
	
	public static final String PAGE_STUDENT_LIST_RESULTS = "/WEB-INF/jsp/student/list_result.jsp";
	
	
	/*
	 * Command
	 */
	public static final String COMMAND_LIST_USER = "/controller?command=listUser";
	
	public static final String COMMAND_ADMIN_LIST_TESTS = "/controller?command=listTestToEdit";
	
	public static final String COMMAND_ADD_QUESTION = "/controller?command=detailTest&testId=";

	public static final String COMMAND_UPDATE_TEST = "/controller?command=updateTest&testId=";

	public static final String COMMAND_EDIT_TEST = "/controller?command=editTest&testId=";

	public static final String COMMAND_ADD_TEST = "/controller?command=addTest";

	public static final String COMMAND_ADMIN_TEST_DETAIL = "/controller?command=detailTest&testId=";

	
	public static final String COMMAND_STUDENT_LIST_TESTS = "/controller?command=listTestToDo";
	
	public static final String COMMAND_STUDENT_LIST_RESULTS = "/controller?command=listResult";
	
	
	public static final String COMMAND_VIEW_SETTINGS = "/controller?command=viewSettings";
	
	public static final String COMMAND_RESULT_TEST = "/controller?command=viewResult&utbeanId=";

	
}
