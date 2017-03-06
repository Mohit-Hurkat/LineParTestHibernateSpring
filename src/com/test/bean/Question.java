package com.test.bean;

public class Question {
   private int questionId;
   private int subjectId;
   private String question;
   private int answer;
   private String choice1;
   private String choice2;
   private String choice3;
   private String choice4;
   private String ans;

   
   
public Question(int questionId, int subjectId, String question, int answer, String choice1, String choice2,
		String choice3, String choice4,String ans) {
	super();
	this.questionId = questionId;
	this.subjectId = subjectId;
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
}
public String getAns() {
	return ans;
}
public void setAns(String ans) {
	this.ans = ans;
}
@Override
public String toString() {
	return "SubjectId=" + subjectId +" QuestionId=" + questionId + "\nQuestion=" + question + ",\nAnswer="+ ans;
}
public String display() {
	return "Question=" + question + "\n1." + choice1 + "\n2." + choice2 + "\n3." + choice3
			+ "\n4." + choice4;
}
public int getQuestionId() {
	return questionId;
}
public void setQuestionId(int questionId) {
	this.questionId = questionId;
}
public int getSubjectId() {
	return subjectId;
}
public void setSubjectId(int subjectId) {
	this.subjectId = subjectId;
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

   
}
