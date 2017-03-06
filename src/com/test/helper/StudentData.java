package com.test.helper;

import java.util.Scanner;

import com.test.bean.Student;
public class StudentData {
	private Student student;
	private String username,password,name,phone,email;
	Scanner sc=new Scanner(System.in);
	
	public void input(){
		try {
			System.out.println("Enter Username : ");
			username=sc.nextLine();
			System.out.println("Enter Password : ");
			password=sc.nextLine();
			System.out.println("Enter Name : ");
			name=sc.nextLine();
			System.out.println("Enter Phone Number : ");
			phone=sc.nextLine();
			System.out.println("Enter Email : ");
			email=sc.nextLine();
			student=new Student(username,password,name,phone,email);
		} catch (Exception e) {
			System.out.println("Enter Appropriate Information");
		}
		
	}
	public Student update(){
		 try {
			System.out.println("Enter New Details :\n");
			 System.out.println("Enter New Student Name:");
			 name=sc.nextLine();
			 System.out.println("Enter New Password:");
			 password=sc.nextLine();
			 System.out.println("Enter New Phone Number:");
			 phone=sc.nextLine();
			 System.out.println("Enter New E-Mail Id:");
			 email=sc.nextLine();
			 student=new Student(username,password,name, phone, email);
			 return student;
		} catch (Exception e) {
			System.out.println("Enter Appropriate Information");
		}
		 return null;
	}
	
	public void instruction(){
		System.out.println("\n------------Don't Press Anything Until The Test Starts------------");
		System.out.println("\n-------------------INSTRUCTIONS FOR ONLINE EXAM-------------------");
		System.out.println("1)You will be given only 25 seconds to attempt every question");
		System.out.println("2)You can't skip any question");
		System.out.println("3)Every question has 4 options out of which only one is correct");
		System.out.println("4)You can't attempt the same test twice");
		System.out.println("5)Once answered you can't change your answer");
		System.out.println("6)Each question is of one mark");
		System.out.println("7)Your result will be displayed as per the number of correct questions you have answered in percentage");
		System.out.println("\n");
	}
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}