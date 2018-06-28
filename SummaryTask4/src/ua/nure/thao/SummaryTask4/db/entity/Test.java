package ua.nure.thao.SummaryTask4.db.entity;

import java.util.ArrayList;
import java.util.List;

public class Test extends Entity{
	
	private static final long serialVersionUID = -6670314090353235190L;

	private String name;
	
	private int categoryId;
	
	private int levelId;
	
	private int numberQuestions;
	
	private String duration;
	
	private List<Question> questions;

	public String getName() {
		return name;
	}

	public void setName(String tema) {
		this.name = tema;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getNumberQuestions() {
		return questions.size();
	}

	public void setNumberQuestions(int numberQuestions) {
		this.numberQuestions = numberQuestions;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String string) {
		this.duration = string;
	}

	public List<Question> getQuestions() {
		if (questions == null) {
			questions = new ArrayList<Question>();
		}
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		if (this.questions == null) {
			this.questions = new ArrayList<Question>();
		}
		this.questions.addAll(questions);
	}
	
	public String toString() {
		
		StringBuilder result = new StringBuilder(name).append(System.lineSeparator())
				.append(String.valueOf("categoryId: " + categoryId)).append(System.lineSeparator())
				.append("LevelId: " + levelId).append(System.lineSeparator())
				.append("Questions: " + this.getQuestions().size()).append(System.lineSeparator())
				.append(String.valueOf("Duration: " + duration) + '\n');
		if (questions == null || questions.size() == 0) {
			return result.append("Test contains no questions").toString();
		}
		for (Question question : questions) {
			result.append(question).append("\n");
		}
		return result.toString();
	}
	
	public String basicInfo() {
		return "(" + this.getId() + ", " + name + ")";
	}
	
	public String getCategoryName(List<Category> categories) {
		for(Category c : categories) {
			if (categoryId == c.getId()) {
				return c.getName();
			}
		}
		return null;
	}
	
	public String getLevelName(List<Level> levels) {
		for(Level l : levels) {
			if (levelId == l.getId()) {
				return l.getName();
			}
		}
		return null;
	}
}
