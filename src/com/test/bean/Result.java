package com.test.bean;

import java.util.Date;

public class Result {
	 private String username;
     private int subjectId;
     private int result;
     private Date date;
     
	public String getUsername() {
		return username;
	}
	
	public Result(String username, int subjectId, int result, Date date) {
		super();
		this.username = username;
		this.subjectId = subjectId;
		this.result = result;
		this.date = date;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSubject() {
		return subjectId;
	}
	public void setSubject(int subject) {
		this.subjectId = subject;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "SubjectId=" + subjectId + ", Result=" + result + ", Date=" + date + "]";
	}
    
     
}
