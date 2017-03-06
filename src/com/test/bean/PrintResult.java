package com.test.bean;

import java.util.Date;

public class PrintResult {
	private String username;
    private int subjectId;
    private int questionId;
    private String Question;
    private String answer;
    private String choice;
    
    
    
	@Override
	public String toString() {
		return "PrintResult [username=" + username + ", subjectId=" + subjectId + ", questionId=" + questionId
				+ ", Question=" + Question + ", answer=" + answer + ", choice=" + choice + "]";
	}
	
	
	public PrintResult(String username, int subjectId, int questionId, String question, String answer, String choice) {
		super();
		this.username = username;
		this.subjectId = subjectId;
		this.questionId = questionId;
		Question = question;
		this.answer = answer;
		this.choice = choice;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
}
