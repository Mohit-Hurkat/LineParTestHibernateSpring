package com.test.bean;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "SUBJECT")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int subject_Id;
	private String subject;
	private String start_;
	private String end_;

	public Subject() {

	}

	public Subject(int subject_Id, String subject, String start_, String end_) {
		super();
		this.subject_Id = subject_Id;
		this.subject = subject;
		this.start_ = start_;
		this.end_ = end_;
	}

	public int getSubject_Id() {
		return subject_Id;
	}

	public void setSubject_Id(int subject_Id) {
		this.subject_Id = subject_Id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStart_() {
		return start_;
	}

	public void setStart_(String start_) {
		this.start_ = start_;
	}

	public String getEnd_() {
		return end_;
	}

	public void setEnd_(String end_) {
		this.end_ = end_;
	}

	@Override
	public String toString() {
		return "SubjectId=" + subject_Id + ", subject=" + subject + "\nTest can be given between " + start_ + " and "
				+ end_ + "\n";
	}

}
