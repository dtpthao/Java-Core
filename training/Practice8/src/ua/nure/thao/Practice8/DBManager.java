package ua.nure.thao.Practice8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	
	
	private static DBManager instance; 

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {};
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = DriverManager.getConnection(Constants.CONNECTION_URL);
		return con;
	}
	
	private void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				throw new IllegalStateException("Cannot close " + ac);
			}
		}
	}

	public boolean insertUser(User user) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants
					.SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getLogin());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return false;
	}

	public List<User> findAllUsers() throws SQLException {

		List<User> users = new ArrayList<User>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_USERS);
		while (rs.next()) {
			User user = extractUser(rs);
			users.add(user);
		}
		close(rs);
		close(stmt);
		close(con);
		return users;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		return user;
	}

	public boolean insertGroup(Group group) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants
					.SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, group.getName());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					group.setId(rs.getInt(1));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return false;
	}

	public List<Group> findAllGroups() throws SQLException {

		List<Group> groups = new ArrayList<Group>();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_GROUPS);
		while (rs.next()) {
			Group group = extractGroup(rs);
			groups.add(group);
		}
		close(rs);
		close(stmt);
		close(con);
		return groups;
	}

	private Group extractGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getInt("id"));
		group.setName(rs.getString("name"));
		return group;
	}
	
	public User getUser(String string) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, string);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractUser(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return null;
	}
	
	public Group getGroup(String string) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_GROUP_BY_NAME);
			pstmt.setString(1, string);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractGroup(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return null;
	}
	
	private Group getGroup(int id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_FIND_GROUP_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractGroup(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return null;
	}

	public boolean setGroupsForUser(User user, Group team) throws SQLException{

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Constants.SQL_SET_GROUP_FOR_USER);
			pstmt.setInt(1, user.getId());
			pstmt.setInt(2, team.getId());
			
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return false;
	}

	public boolean setGroupsForUser(User user, 
			Group team1, Group team2) throws SQLException {
		
		if (setGroupsForUser(user,team1)) {
			return setGroupsForUser(user,team2);
		};
		return false;
	}

	public boolean setGroupsForUser(User user, Group team1, 
			Group team2, Group team3) throws SQLException {

		if (setGroupsForUser(user,team1)) {
			if (setGroupsForUser(user,team2)) {
				return setGroupsForUser(user,team3);
			};
		};
		return false;
	}
	
	public List<Group> getUserGroups(User user) throws SQLException {

		List<Group> groups = new ArrayList<Group>();
		Connection con = getConnection();
		PreparedStatement pstmt = con
				.prepareStatement(Constants.SQL_FIND_ID_GROUP_BY_ID_USER);
		pstmt.setInt(1, user.getId());
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int groupId = rs.getInt("group_id");
			groups.add(getGroup(groupId));
		}
		close(rs);
		close(pstmt);
		close(con);
		return groups;
	}

	public void deleteGroup(Group team) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(Constants.SQL_DELETE_GROUP);
		pstmt.setString(1, team.getName());
		
		if (pstmt.executeUpdate() > 0) {
			System.out.println("Deleted!");
		}
		else System.out.println("Oops!");
		close(pstmt);
		close(con);
	}

	public void updateGroup(Group team) throws SQLException {

		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(Constants.SQL_UPDATE_GROUP);
		pstmt.setString(1, team.getName());
		pstmt.setInt(2, team.getId());
		
		if (pstmt.executeUpdate() > 0) {
			System.out.println("Updated!");
		}
		else System.out.println("Oops!");
		close(pstmt);
		close(con);
	}

}
