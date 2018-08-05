package ua.nure.thao.Practice8.db;

public class Constants {
	
	static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/Thao"
			+ "?autoReconnect=true&useSSL=false&user=root&password=1&relaxAutoCommit=true";
	static final String SQL_FIND_ALL_USERS = "SELECT * FROM users ORDER BY id";
	static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups ORDER BY id";
	static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	static final String SQL_FIND_GROUP_BY_NAME = "SELECT * FROM groups WHERE name=?";
	static final String SQL_FIND_ID_GROUP_BY_ID_USER = "SELECT * FROM users_groups WHERE user_id=?";
	static final String SQL_FIND_GROUP_BY_ID = "SELECT * FROM groups WHERE id=?";
	static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?)";
	static final String SQL_INSERT_GROUP = "INSERT INTO groups VALUES (DEFAULT, ?)";
	static final String SQL_SET_GROUP_FOR_USER = "INSERT INTO users_groups VALUES (?, ?)";
	static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE name=?";
	static final String SQL_UPDATE_GROUP = "UPDATE groups SET name=? WHERE id=?";
}
