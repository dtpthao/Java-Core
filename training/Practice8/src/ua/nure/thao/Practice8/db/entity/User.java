package ua.nure.thao.Practice8.db.entity;

public class User {
	
	private int id;
	private String login;
	
	public static User createUser(String string) {
		User user = new User();
		user.login = string;
		return user;
	}
	
	public String getLogin() {
		return this.login;
	}

	public void setId(int int1) {
		this.id = int1;
	}

	public void setLogin(String string) {
		this.login = string;
	}
	
	public String toString() {
		return this.login;
	}

	public int getId() {
		return this.id;
	}
}
