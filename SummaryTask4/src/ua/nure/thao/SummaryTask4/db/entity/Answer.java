package ua.nure.thao.SummaryTask4.db.entity;

public class Answer extends Entity{

	private static final long serialVersionUID = -631891471556823183L;

	private String name;
	
	private int categoryId;
	
	private long questionId;

	private Boolean correct;
	
	public Answer() {};

	public Answer(String name, int categoryId, Boolean correct) {
		this.name = name;
		this.categoryId = categoryId;
		this.correct = correct;
	}
	
	public Answer(int categoryId, long qId, String newA, Boolean corr) {
		this.name = name;
		this.categoryId = categoryId;
		this.correct = corr;
		this.questionId = qId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long question_id) {
		this.questionId = question_id;
	}

	public boolean isCorrect() {
		if (correct == null) { 
			return false;
		}
		return correct;
	}

	public void setCorrect(Boolean value) {
		this.correct = value;
	}
	
	@Override
	public String toString() {
		return name + (isCorrect() ? " [correct=true]" : "");		
	}

}
