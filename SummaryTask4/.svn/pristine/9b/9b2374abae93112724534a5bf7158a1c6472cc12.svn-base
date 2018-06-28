package ua.nure.thao.SummaryTask4.db;

public class Constants {
	
	static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/SummaryTask4"
			+ "?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&user=root&password=1&relaxAutoCommit=true";
	
	/*
	 * Common
	 */
	public static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?)";
	
	
	public static final String SQL_UPDATE_USER_INFO = "UPDATE users SET first_name=?, last_name=?, pwd=? WHERE id=?";
	
	
	public static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";

	public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	public static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";
	
	public static final String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id=?";

	public static final String SQL_FIND_ALL_TEST = "SELECT * FROM test";
	
	public static final String SQL_FIND_TESTS_BY_CATEGORY = "SELECT * FROM test WHERE category_id=?";

	public static final String SQL_FIND_TEST_BY_ID = "SELECT * FROM test WHERE id=?";
	
	public static final String SQL_FIND_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name=?";

	public static final String SQL_FIND_QUESTIONS_BY_TEST_ID = "SELECT * FROM test_questions WHERE test_id=?";

	public static final String SQL_FIND_QUESTION_BY_ID = "SELECT * FROM question WHERE id=?";
	
	public static final String SQL_FIND_ANSWERS_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id=?";
	
	public static final String SQL_FIND_LEVEL_BY_ID = "SELECT * FROM level WHERE id=?";
	
	public static final String SQL_FIND_ALL_LEVELS = "SELECT * FROM level ORDER BY id";
	
	/*
	 *  Admin
	 */
	public static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	
	public static final String SQL_FIND_USERS_BY_ROLE = "SELECT * FROM users WHERE role_id=?";
	
	public static final String SQL_FIND_ALL_ROLES = "SELECT * FROM roles";
	
	
	public static final String SQL_CREATE_TEST = "INSERT INTO test VALUES (DEFAULT, ?, ?, ?, ?)";
	
	public static final String SQL_INSERT_QUESTION = "INSERT INTO question VALUES(DEFAULT, ?, ?, ?)";
	
	public static final String SQL_INSERT_TEST_QUESTION = "INSERT INTO test_questions (category_id, test_id, question_id) VALUES(?, ?, ?)";
	
	public static final String SQL_INSERT_ANSWER = "INSERT INTO answer VALUES(DEFAULT, ?, ?, ?, ?)";
	

	public static final String SQL_DELETE_TEST = "DELETE FROM test WHERE id=?";
	
	public static final String SQL_DELETE_QUESTION = "DELETE FROM question WHERE id=?";
	
	public static final String SQL_DELETE_ANSWER = "DELETE FROM answer WHERE id=?";

	
	public static final String SQL_UPDATE_USER_ROLE = "UPDATE users SET role_id=? WHERE id=?";
	
	public static final String SQL_UPDATE_TEST = "UPDATE test SET category_id=?, name=?, level_id=?, duration=? WHERE id=?";

	public static final String SQL_UPDATE_QUESTION = "UPDATE question SET name=? WHERE id=?";

	public static final String SQL_UPDATE_ANSWER = "UPDATE answer SET text=?, correct=?  WHERE id=?";

	
	/*
	 * Student
	 */
	public static final String SQL_INSERT_USER_TEST_BEAN = "INSERT INTO user_test VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	

	public static final String SQL_FIND_USER_TEST_BEANS = "SELECT * FROM user_test WHERE user_id=?";
	
	public static final String SQL_FIND_USER_TEST_BEANS_BY_CATEGORY = "SELECT * FROM user_test WHERE user_id=? and category_id=?";

	public static final String SQL_FIND_USER_TEST_BEAN = "SELECT * FROM user_test WHERE id=?";
	

	public static final String SQL_DELETE_USER_TEST_BEAN = "DELETE FROM user_test WHERE id=?";


}
