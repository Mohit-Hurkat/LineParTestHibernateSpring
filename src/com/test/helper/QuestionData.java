package com.test.helper;

import java.util.Scanner;

import com.test.bean.Question;
import com.test.bean.Student;
import com.test.bl.QuestionLogic;
import com.test.bl.SubjectLogic;

public class QuestionData {

	Question question;
	private String question1,choice1,choice2,choice3,choice4,ans=null;
	private int subjectId,answer,QuestionId;
	private QuestionLogic qbl=new QuestionLogic();
	private SubjectLogic sbl=new SubjectLogic();
	Scanner scanner=new Scanner(System.in);
	
	public Question input(){
		
		try {
			System.out.println("Enter The Subject Id:");
			subjectId=Integer.parseInt(scanner.nextLine());
			System.out.println("Write The Question:");
			question1=scanner.nextLine();
			
			System.out.println("Enter Option 1:");
			choice1=scanner.nextLine();
			System.out.println("Enter Option 2:");
			choice2=scanner.nextLine();
			System.out.println("Enter Option 3:");
			choice3=scanner.nextLine();
			System.out.println("Enter Option 4:");
			choice4=scanner.nextLine();
			System.out.println("Enter Answer Of The Question(Numeric Option):");
			answer=scanner.nextInt();
			question=new Question(0,subjectId, question1, answer, choice1, choice2, choice3, choice4,ans);
			 return question;
		} catch (NumberFormatException e) {
			System.out.println("Enter Appropriate Information");
		}
		return null;
	}
	public Question update(){
		try {
			System.out.println("Enter New Details :\n");
			System.out.println("Enter Subject ID");
			subjectId=Integer.parseInt(scanner.nextLine());
			System.out.println("Enter The QuestionId You Want to Update:");
			QuestionId=Integer.parseInt(scanner.nextLine());
			if(qbl.search(QuestionId)==null || sbl.search(subjectId)==null)
			return null;
			System.out.println("Write New Question:");
			question1=scanner.nextLine();
			System.out.println("Enter Option 1:");
			choice1=scanner.nextLine();
			System.out.println("Enter Option 2:");
			choice2=scanner.nextLine();
			System.out.println("Enter Option 3:");
			choice3=scanner.nextLine();
			System.out.println("Enter Option 4:");
			choice4=scanner.nextLine();
			System.out.println("Enter Answer Of The Question:");
			answer=scanner.nextInt();
			 question=new Question(QuestionId,subjectId, question1, answer, choice1, choice2, choice3, choice4,ans);
			 return question;
		} catch (Exception e) {
			System.out.println("Enter Appropriate Information");
		}
		 return null;
		
		
	}
}
