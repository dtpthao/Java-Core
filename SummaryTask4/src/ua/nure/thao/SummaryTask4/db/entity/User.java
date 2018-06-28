package ua.nure.thao.SummaryTask4.db.entity;

import java.util.List;

import ua.nure.thao.SummaryTask4.db.bean.UserTestBean;

public class User extends Entity{

	private static final long serialVersionUID = -5498347506537324630L;
	
	private String login;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private int roleId = 1;
	
	private List<UserTestBean> utbeans;
	
	private float avgPercent = 0;
	
	public User() {};
	
	public User(String login, String pwd, String firstName, String lastName, int roleId) {
		this.login = login;
		this.password = pwd;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleId = roleId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	public String toString() {
		return "User [login=" + login
				+ ", pwd=" + password
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", roleId=" + roleId + "]";
	}
	
	public String basicInfo() {
		return "(" + this.getId() + ", " + login + ")";
	}

	public List<UserTestBean> getUtbeans() {
		return utbeans;
	}

	public void setUtbeans(List<UserTestBean> utbeans) {
		this.utbeans = utbeans;
	}

	public float getAvgPercent() {
		avgPercent = 0;
		for(UserTestBean bean : utbeans) {
			avgPercent += bean.getPercent();
		}
		return avgPercent/utbeans.size();
	}

	public void setAvgPercent(float avgPercent) {
		this.avgPercent = avgPercent;
	}

//	public List<UserTestBean> getTests() {
//		return tests;
//	}
//
//	public void setTests(List<UserTestBean> tests) {
//		this.tests = tests;
//	}

}
