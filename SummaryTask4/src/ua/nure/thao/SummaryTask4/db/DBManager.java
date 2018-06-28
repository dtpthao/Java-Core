package ua.nure.thao.SummaryTask4.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import ua.nure.thao.SummaryTask4.db.bean.UserTestBean;
import ua.nure.thao.SummaryTask4.db.entity.Answer;
import ua.nure.thao.SummaryTask4.db.entity.Category;
import ua.nure.thao.SummaryTask4.db.entity.Level;
import ua.nure.thao.SummaryTask4.db.entity.Question;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.db.entity.User;
import ua.nure.thao.SummaryTask4.exception.DBException;
import ua.nure.thao.SummaryTask4.exception.Messages;

public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static DBManager instance;

	public static synchronized DBManager getInstance() throws DBException {
		return (instance == null) ? new DBManager() : instance;
	}

	private DataSource ds;

	private DBManager() throws DBException {
//		try {
//			BasicConfigurator.configure();
//			Context initContext = new InitialContext();
//			System.out.println("bbbb");
//			Context envContext = (Context) initContext.lookup("java:/comp/env");
//			System.out.println("slkjlskf");
//			ds = (DataSource) envContext.lookup("jdbc/ST4DB");
//			LOG.trace("Data source ==> " + ds);
//		} catch (NamingException ex) {
//			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
//			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
//		}
	}

	/**
	 * 
	 * @return DB connection
	 * @throws DBException
	 * @throws SQLException
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Constants.CONNECTION_URL);
		} catch (ClassNotFoundException | SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	/**
	 * Register
	 * Out of control
	 * @param user
	 * @return boolean
	 * @throws DBException
	 */
	public boolean insertUser(User user) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getLogin());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setInt(5, user.getRoleId());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getLong(1));
					con.commit();
					return true;
				}
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, e);
		} finally {
			close(con, pstmt, rs);
		}
		return false;
	}

	/**
	 * Admin task 
	 * Add and Save a new Test Command
	 * @param test
	 * @return
	 * @throws DBException
	 */
	public boolean creatTest(Test test) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_CREATE_TEST, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, test.getCategoryId());
			pstmt.setString(2, test.getName());
			pstmt.setInt(3, test.getLevelId());
			pstmt.setString(4, test.getDuration());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					test.setId(rs.getLong(1));
					for (Question q : test.getQuestions()) {
						insertQuestion(q);
						insertTestQuestions(test.getCategoryId(), test.getId(), q.getId());
					}
					con.commit();
					return true;
				}
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_CREATE_TEST, e);
			throw new DBException(Messages.ERR_CANNOT_CREATE_TEST, e);
		} finally {
			close(con, pstmt, rs);
		}
		return false;
	}

	/**
	 * Admin task
	 * Add a Question to a Test
	 * @param categoryId
	 * @param testId
	 * @param qId
	 * @return
	 * @throws DBException
	 */
	public boolean insertTestQuestions(long categoryId, long testId, long qId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_INSERT_TEST_QUESTION);
			pstmt.setLong(1, categoryId);
			pstmt.setLong(2, testId);
			pstmt.setLong(3, qId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_QUESTION_TEST, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_QUESTION_TEST, e);
		} finally {
			close(con, pstmt, rs);
		}
		return false;
	}
	
	/**
	 * Admin task
	 * Add a new Question
	 * @param q
	 * @throws DBException
	 */
	public void insertQuestion(Question q) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, q.getCategoryId());
			pstmt.setInt(2, q.getLevelId());
			pstmt.setString(3, q.getName());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					q.setId(rs.getLong(1));
					for (Answer a : q.getAnswers()) {
						insertAnswer(q.getId(), a);
					}
					con.commit();
				}
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_QUESTION, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_QUESTION, e);
		} finally {
			close(con, pstmt, rs);
		}
	}
	
	/**
	 * Admin task
	 * Insert an Answer to a Question
	 * @param qId
	 * @param a
	 * @throws DBException
	 */
	public void insertAnswer(long qId, Answer a) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_INSERT_ANSWER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getCategoryId());
			pstmt.setLong(2, qId);
			pstmt.setString(3, a.getName());
			pstmt.setBoolean(4, a.isCorrect());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					a.setId(rs.getLong(1));
					con.commit();
				}
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_ANSWER);
			throw new DBException(Messages.ERR_CANNOT_INSERT_ANSWER, e);
		} finally {
			close(con, pstmt, rs);
		}
	}
	
	/**
	 * Save result of a Test task
	 * Student task
	 * @param userTest
	 * @return
	 * @throws DBException
	 */
	public boolean insertUserTestBean(UserTestBean userTest) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_INSERT_USER_TEST_BEAN, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, userTest.getUserId());
			pstmt.setInt(2, userTest.getCategoryId());
			pstmt.setLong(3, userTest.getTestId());
			pstmt.setString(4, userTest.getDate());
			pstmt.setInt(5, userTest.getResult());
			pstmt.setInt(6, userTest.getStatusId());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					userTest.setId(rs.getLong(1));
					con.commit();
					return true;
				}
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_USER_TEST_BEAN, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER_TEST_BEAN, e);
		} finally {
			close(con, pstmt, rs);
		}
		return false;
	}

	//////////////////////////////////////////////////////////////////
	// Update tasks
	///////////////////////////////////////////////////////////////////
	
	/**
	 * Update role
	 * Admin task
	 * @param userId
	 * @param roleId
	 * @throws DBException
	 */
	public void updateUser(long userId, int roleId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_UPDATE_USER_ROLE);
			pstmt.setInt(1, roleId);
			pstmt.setLong(2, userId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER_ROLE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER_ROLE, ex);
		} finally {
			close(con);
			close(pstmt);
		}
	}
	
	/**
	 * Common task
	 * Settings
	 * @param user
	 * @return
	 * @throws DBException
	 */
	public boolean updateUser(User user) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_UPDATE_USER_INFO);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getPassword());
			pstmt.setLong(4, user.getId());
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}

	/**
	 * Edit Test
	 * Admin task
	 * @param test
	 * @return
	 * @throws DBException
	 */
	public boolean updateTest(Test test) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_UPDATE_TEST);
			pstmt.setLong(1, test.getCategoryId());
			pstmt.setString(2, test.getName());
			pstmt.setInt(3, test.getLevelId());
			pstmt.setString(4, test.getDuration());
			pstmt.setLong(5, test.getId());
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_TEST, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_TEST, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}

	/**
	 * Edit Question text
	 * Admin task
	 * @param qId
	 * @param updText
	 * @return
	 * @throws DBException
	 */
	public boolean updateQuestion(long qId, String updText) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_UPDATE_QUESTION);
			pstmt.setString(1, updText);
			pstmt.setLong(2, qId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_QUESTION, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_QUESTION, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}

	/**
	 * Edit an Answer
	 * Admin task
	 * @param aId
	 * @param updText
	 * @param correct
	 * @return
	 * @throws DBException
	 */
	public boolean updateAnswer(long aId, String updText, Boolean correct) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_UPDATE_ANSWER);
			pstmt.setString(1, updText);
			pstmt.setBoolean(2, correct);
			pstmt.setLong(3, aId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_ANSWER, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ANSWER, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}
	
	///////////////////////////////////////////////////////
	// Delete
	///////////////////////////////////////////////////////
	
	/**
	 * Delete a Test
	 * Admin task
	 * 
	 * @param test
	 * @return
	 * @throws DBException
	 */
	public boolean deleteTest(long testId) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_DELETE_TEST);
			pstmt.setLong(1, testId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_TEST, e);
			throw new DBException(Messages.ERR_CANNOT_DELETE_TEST, e);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}

	/**
	 * Delete a Question
	 * Admin task
	 * @param qId
	 * @return
	 * @throws DBException
	 */
	public boolean deleteQuestion(long qId) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_DELETE_QUESTION);
			pstmt.setLong(1, qId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_QUESTION, e);
			throw new DBException(Messages.ERR_CANNOT_DELETE_QUESTION, e);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}

	/**
	 * Delete an Answer
	 * Admin task
	 * @param aId
	 * @return
	 * @throws DBException
	 */
	public boolean deleteAnswer(long aId) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_DELETE_ANSWER);
			pstmt.setLong(1, aId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_ANSWER, e);
			throw new DBException(Messages.ERR_CANNOT_DELETE_ANSWER, e);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}

	/**
	 * Delete a result
	 * Student task
	 * @param utbeanId
	 * @return
	 * @throws DBException
	 */
	public boolean deleteUserTestBean(long utbeanId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_DELETE_USER_TEST_BEAN);
			pstmt.setLong(1, utbeanId);
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_USER_TEST_BEAN, e);
			throw new DBException(Messages.ERR_CANNOT_DELETE_USER_TEST_BEAN, e);
		} finally {
			close(con);
			close(pstmt);
		}
		return false;
	}
	
	/////////////////////////////////////////////////////
	// Find task
	/////////////////////////////////////////////////////

	/**
	 * Find all Users
	 * Admin task
	 * @return
	 * @throws DBException
	 */
	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(Constants.SQL_FIND_ALL_USERS);
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_USER, ex);
		} finally {
			close(con, stmt, rs);
		}
		return users;
	}

	/**
	 * Find an User by RoleId
	 * Admin task
	 * @param roleId
	 * @return
	 * @throws DBException
	 */
	public List<User> findUserByRole(int roleId) throws DBException {
		List<User> users = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USERS_BY_ROLE);
			pstmt.setLong(1, roleId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ROLE, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ROLE, e);
		} finally {
			close(con, pstmt, rs);
		}
		return users;
	}

	/**
	 * Find an User by userId
	 * Common task
	 * @param userId
	 * @return
	 * @throws DBException
	 */
	public User findUserById(long userId) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USER_BY_ID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Login
	 * Out of control task
	 * @param string
	 * @return User's info
	 * @throws DBException
	 */
	public User findUserByLogin(String login) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Find All categories
	 * Common task
	 * @return All categories
	 * @throws DBException
	 */
	public List<Category> findCategories() throws DBException {
		List<Category> categoriesList = new ArrayList<Category>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(Constants.SQL_FIND_ALL_CATEGORIES);
			while (rs.next()) {
				categoriesList.add(extractCategory(rs));
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORIES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return categoriesList;
	}
	
	/**
	 * Find a Category by categoryId
	 * Common task
	 * @param categoryId
	 * @return
	 * @throws DBException
	 */
	public Category findCategoryById(long categoryId) throws DBException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_CATEGORY_BY_ID);
			pstmt.setLong(1, categoryId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractCategory(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORY_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORY_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Find All Levels
	 * Common task
	 * @return
	 * @throws DBException
	 */
	public List<Level> findAllLevels() throws DBException {
		List<Level> levels = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(Constants.SQL_FIND_ALL_LEVELS);
			while (rs.next()) {
				levels.add(extractLevel(rs));
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_LEVELS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_LEVELS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return levels;
	}

	/**
	 * Find a Level by levelId
	 * Common task
	 * @param levelId
	 * @return
	 * @throws DBException
	 */
	public Level findLevelById(int levelId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_LEVEL_BY_ID);
			pstmt.setLong(1, levelId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractLevel(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CATEGORY_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CATEGORY_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Find All Tests
	 * Common task
	 * @return
	 * @throws DBException
	 */
	public List<Test> findAllTest() throws DBException {
		List<Test> tests = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(Constants.SQL_FIND_ALL_TEST);
			while (rs.next()) {
				tests.add(extractTest(rs));
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_TESTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_TESTS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return tests;
	}
	
	/**
	 * Find All Tests by categoryId
	 * Common task
	 * @param categoryId
	 * @return
	 * @throws DBException
	 */
	public List<Test> findTestsByCategory(Long categoryId) throws DBException {
		List<Test> tests = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_TESTS_BY_CATEGORY);
			pstmt.setLong(1, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tests.add(extractTest(rs));
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_TESTS_BY_CATEGORY, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_TESTS_BY_CATEGORY, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return tests;
	}

	/**
	 * Find a Test by testId
	 * Common task
	 * @param testId
	 * @return
	 * @throws DBException
	 */
	public Test findTestById(long testId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_TEST_BY_ID);
			pstmt.setLong(1, testId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractTest(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_TEST_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_TEST_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Find a Question by questionId
	 * Common task
	 * @param qId
	 * @return
	 * @throws DBException
	 */
	public Question findQuestionById(long qId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_QUESTION_BY_ID);
			pstmt.setLong(1, qId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractQuestion(rs);
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_QUESTION_BY_ID, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_QUESTION_BY_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Find a result UserTestBean by id
	 * Student task
	 * @param uTBeanId
	 * @return
	 * @throws DBException
	 */
	public UserTestBean findUserTestBean(long uTBeanId) throws DBException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USER_TEST_BEAN);
			pstmt.setLong(1, uTBeanId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractUserTestBean(rs);
			}
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_TEST_BEAN, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_TEST_BEAN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return null;
	}

	/**
	 * Find all results UserTestBean of an User
	 * Common task
	 * @param userId
	 * @return
	 * @throws DBException
	 */
	public List<UserTestBean> findUserTestBeans(long userId) throws DBException {
		List<UserTestBean> userTest = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USER_TEST_BEANS);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userTest.add(extractUserTestBean(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_TEST_BEANS, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_TEST_BEANS, e);
		} finally {
			close(con, pstmt, rs);
		}
		return userTest;
	}
	
	/**
	 * Find list results UserTestBean of an User by categoryId
	 * Student task
	 * @param userId
	 * @param categoryId
	 * @return
	 * @throws DBException
	 */
	public List<UserTestBean> findUserTestBeansByCategory(long userId, int categoryId) throws DBException {
		List<UserTestBean> utbeans = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USER_TEST_BEANS_BY_CATEGORY);
			pstmt.setLong(1, userId);
			pstmt.setInt(2, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				utbeans.add(extractUserTestBean(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_TEST_BEANS_BY_CATEGORY, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_TEST_BEANS_BY_CATEGORY, e);
		} finally {
			close(con, pstmt, rs);
		}
		return utbeans;
	}

	/*
	 * Extract tasks
	 */

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		return user;
	}

	/**
	 * 
	 * @param rs
	 * @return category
	 * @throws SQLException
	 */
	private Category extractCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong(Fields.ENTITY_ID));
		category.setName(rs.getString(Fields.CATEGORY_NAME));
		return category;
	}

	private Level extractLevel(ResultSet rs) throws SQLException {
		Level level = new Level();
		level.setId(rs.getLong(Fields.ENTITY_ID));
		level.setName(rs.getString(Fields.LEVEL_NAME));
		return level;
	}

	private Test extractTest(ResultSet rs) throws SQLException, DBException {
		Test test = new Test();
		test.setId(rs.getLong(Fields.ENTITY_ID));
		test.setCategoryId(rs.getInt(Fields.CATEGORY_ID));
		test.setName(rs.getString(Fields.TEST_TEXT));
		test.setLevelId(rs.getInt(Fields.LEVEL_ID));
		test.setDuration(rs.getString(Fields.TEST_DURATION));
		test.setQuestions(DBManager.getInstance().findQuestionsByTestId(test.getId()));
		return test;
	}

	private Question extractQuestion(ResultSet rs) throws SQLException, DBException {
		Question q = new Question();
		q.setId(rs.getLong(Fields.ENTITY_ID));
		q.setCategoryId(rs.getLong(Fields.CATEGORY_ID));
		q.setLevelId(rs.getInt(Fields.LEVEL_ID));
		q.setName(rs.getString(Fields.QUESTION_TEXT));
		q.setAnswers(DBManager.getInstance().findAnswersByQuestionId(q.getId()));
		return q;
	}

	private Answer extractAnswer(ResultSet rs) throws SQLException {
		Answer a = new Answer();
		a.setId(rs.getLong(Fields.ENTITY_ID));
		a.setCategoryId(rs.getInt(Fields.CATEGORY_ID));
		a.setQuestionId(rs.getLong(Fields.QUESTION_ID));
		a.setName(rs.getString(Fields.ANSWER_TEXT));
		a.setCorrect(rs.getBoolean(Fields.ANSWER_CORRECT));
		return a;
	}

	private UserTestBean extractUserTestBean(ResultSet rs) throws SQLException {
		UserTestBean utbean = new UserTestBean();
		utbean.setId(rs.getLong(Fields.ENTITY_ID));
		utbean.setUserId(rs.getLong(Fields.USER_ID));
		utbean.setTestId(rs.getLong(Fields.TEST_ID));
		utbean.setCategoryId(rs.getInt(Fields.CATEGORY_ID));
		utbean.setDate(rs.getString(Fields.DATE));
		utbean.setResult(rs.getInt(Fields.RESULT));
		utbean.setStatusId(rs.getInt(Fields.STATUS));
		return utbean;
	}
	
	/**
	 * Find list Questions by testId
	 * Used in "extractTest" method
	 * Private for 
	 * Common task
	 * @param testId
	 * @return
	 * @throws DBException
	 */
	private List<Question> findQuestionsByTestId(Long testId) throws DBException {
		List<Question> questions = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Long> questionsId = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_QUESTIONS_BY_TEST_ID);
			pstmt.setLong(1, testId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				questionsId.add(rs.getLong(Fields.QUESTION_ID));
			}
			questions.addAll(DBManager.getInstance().findQuestionsByListId(con, pstmt, rs, questionsId));
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_QUESTIONS_BY_TEST_ID, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_QUESTIONS_BY_TEST_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return questions;
	}

	/**
	 * Find All Questions for a Test by list questionId
	 * Used in "findQuestionsByTestId(Long testId)" method
	 * @param con
	 * @param pstmt
	 * @param rs
	 * @param questionsId
	 * @return
	 * @throws SQLException
	 * @throws DBException
	 */
	private List<Question> findQuestionsByListId(Connection con, PreparedStatement pstmt, ResultSet rs,
			List<Long> questionsId) throws SQLException, DBException {
		List<Question> questions = new ArrayList<>();
		pstmt = con.prepareStatement(Constants.SQL_FIND_QUESTION_BY_ID);
		for (long id : questionsId) {
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				questions.add(extractQuestion(rs));
			}
		}
		return questions;
	}
	
	/**
	 * Find list Answers for a Question by questionId
	 * Used in "extractQuestion" method
	 * @param qId
	 * @return
	 * @throws DBException
	 */
	private List<Answer> findAnswersByQuestionId(Long qId) throws DBException {
		List<Answer> answers = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_ANSWERS_BY_QUESTION_ID);
			pstmt.setLong(1, qId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				answers.add(extractAnswer(rs));
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ANSWERS_BY_QUESTION_ID, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ANSWERS_BY_QUESTION_ID, e);
		} finally {
			close(con, pstmt, rs);
		}
		return answers;
	}

	/*****
	 * close Connection
	 * 
	 * @param con
	 * @param stmt
	 * @param rs
	 */
	private void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	private void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * rollback
	 * 
	 * @param con
	 */
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}
}
