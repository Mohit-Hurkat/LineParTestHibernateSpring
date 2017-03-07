package com.test.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "RESULT")
public class Result implements Serializable {
	 @Id
	private int Result_No;
	private String username;
	private int subjectId;
	private int result;
	private Date date1;
	
	public Result(){
		
	}
	
	public Result(String username, int subjectId, int result, Date date1) {
		super();
		this.username = username;
		this.subjectId = subjectId;
		this.result = result;
		this.date1 = date1;
	}

	public String getUsername() {
		return username;
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

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	@Override
	public String toString() {
		return "SubjectId=" + subjectId + ", Result=" + result + ", Date=" + date1 + "]";
	}

}
