package ua.nure.thao.SummaryTask4.db.entity;

import java.util.ArrayList;
import java.util.List;

public class Question extends Entity{
	
	private static final long serialVersionUID = -7773919145623346389L;

	private String name;
	
	private long categoryId;
	
	private int levelId = 0;
	
	private List<Answer> answers;
	
	public Question() {};
	
	public Question(String name, long categoryId, int levelId) {
		this.name = name;
		this.categoryId = categoryId;
		this.levelId = levelId;
	}

	public void setName(String textContent) {
		name = textContent;
	}
	
	public String getName() {
		return name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public List<Answer> getAnswers() {
		if (answers == null) {
			answers = new ArrayList<Answer>();
		}
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		if (this.answers == null) {
			this.answers = new ArrayList<Answer>();
		}
		this.answers.addAll(answers);
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder(name).append('\n');
		int i = 1;
		for (Answer answer : answers) {
			result.append(i++ + ")\t" + answer).append('\n');
		}
		return result.toString();
	}

	public String basicInfo() {
		return "(" + this.getId() + ", " + name + ")";
	}
}
