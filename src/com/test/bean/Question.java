package com.test.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="QUESTIONS")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
   private int question_Id;
   private int subject_Id;
   private String question;
   private int answer;
   private String choice1;
   private String choice2;
   private String choice3;
   private String choice4;
   private String ans;
   private String value;

   public Question(){
	   
   }
   

public Question(int question_Id,int subject_Id, String question, int answer, String choice1, String choice2,
		String choice3, String choice4,String ans,String value) {
	this.question_Id = question_Id;
	this.subject_Id = subject_Id;
	this.question = question;
	this.answer = answer;
	this.choice1 = choice1;
	this.choice2 = choice2;
	this.choice3 = choice3;
	this.choice4 = choice4;
	switch(answer){
	case 1:this.ans= choice1;
	break;
	case 2:this.ans= choice2;
	break;
	case 3:this.ans= choice3;
	break;
	case 4:this.ans= choice4;
	break;
	default: this.ans=null;
	}
	this.value = value;
}

@Override
public String toString() {
	return "SubjectId=" + subject_Id +" QuestionId=" + question_Id + "\nQuestion=" + question + ",\nAnswer="+ ans;
}
public String display() {
	return "Question=" + question + "\n1." + choice1 + "\n2." + choice2 + "\n3." + choice3
			+ "\n4." + choice4;
}


public int getQuestion_Id() {
	return question_Id;
}


public void setQuestion_Id(int question_Id) {
	this.question_Id = question_Id;
}


public int getSubject_Id() {
	return subject_Id;
}


public void setSubject_Id(int subject_Id) {
	this.subject_Id = subject_Id;
}


public String getQuestion() {
	return question;
}


public void setQuestion(String question) {
	this.question = question;
}


public int getAnswer() {
	return answer;
}


public void setAnswer(int answer) {
	this.answer = answer;
}


public String getChoice1() {
	return choice1;
}


public void setChoice1(String choice1) {
	this.choice1 = choice1;
}


public String getChoice2() {
	return choice2;
}


public void setChoice2(String choice2) {
	this.choice2 = choice2;
}


public String getChoice3() {
	return choice3;
}


public void setChoice3(String choice3) {
	this.choice3 = choice3;
}


public String getChoice4() {
	return choice4;
}


public void setChoice4(String choice4) {
	this.choice4 = choice4;
}


public String getAns() {
	return ans;
}


public void setAns(String ans) {
	this.ans = ans;
}


public String getValue() {
	return value;
}


public void setValue(String value) {
	this.value = value;
}



   
}
