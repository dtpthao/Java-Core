package ua.nure.thao.SummaryTask4.db.bean;

import ua.nure.thao.SummaryTask4.db.entity.Entity;
import ua.nure.thao.SummaryTask4.db.entity.Test;
import ua.nure.thao.SummaryTask4.db.entity.User;

public class UserTestBean extends Entity{

	private static final long serialVersionUID = 7633563199902238443L;

	private long userId;
	
	private long testId;
	
	private int categoryId;
	
	private int result;
	
	private String date;
	
	private int statusId;
	
	private Test test;
	
	private User user;
	
	private float percent;
	
	public UserTestBean() {};
	
	public UserTestBean(long userId, int categoryId, long testId, String date, int result, int statusId){
		this.userId = userId;
		this.testId = testId;
		this.categoryId = categoryId;
		this.date = date;
		this.result = result;
		this.statusId = statusId;
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public float getPercent() {
		return (result/test.getNumberQuestions()) * 100 ;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public int getResult() {
		return result;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getStatusId() {
		return statusId;
	}

	@Override
	public String toString() {
		return "UserTestBean [userId=" + userId 
				+ ", testId=" + testId 
				+ ", categoryId" + categoryId
				+ ", Date=" + date
				+ ", result=" + result
				+ ", statusId=" + statusId + "]";
	}

	public String basicInfo() {
		return "(" + this.getId() + ", " + userId + ", " + testId + ")";
	}
}
