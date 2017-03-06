package com.test.bean;

public class Subject {
	private int subjectId;
	private String subject;
	private String start;
	private String end; 
	
	
	public Subject(int subjectId, String subject, String start, String end) {
		super();
		this.subjectId = subjectId;
		this.subject = subject;
		this.start = start;
		this.end = end;
	}

	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "SubjectId=" + subjectId + ", subject=" + subject + "\nTest can be given between "+ start + " and " + end + "\n" ;
	}
	
	
	
}
