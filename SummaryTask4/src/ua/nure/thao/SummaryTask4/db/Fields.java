package ua.nure.thao.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Fields {
	
	// entities
	public static final String ENTITY_ID = "id";
	
	//user
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "pwd";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_ROLE_ID = "role_id";
	
	//category
	public static final String CATEGORY_NAME = "name";
	
	//common
	public static final String CATEGORY_ID = "category_id";
	public static final String TEST_ID = "test_id";
	public static final String QUESTION_ID = "question_id";
	
	//test
	public static final String TEST_TEXT = "name";
	public static final String LEVEL_ID = "level_id";
	public static final String TEST_DURATION = "duration";
	public static final String DATE = "date";
	public static final String RESULT = "result";
	public static final String STATUS = "status_id";
	public static final String USER_ID = "user_id";
	public static final String QUESTIONS = "questions";

	//question
	public static final String QUESTION_TEXT = "name";	

	//answer
	public static final String ANSWER_TEXT = "text";
	public static final String ANSWER_CORRECT = "correct";

	public static final String LEVEL_NAME = "name";

	
}