package com.test.bean;

public class Student extends User{
     private String name;
     private String phone;
     private String email;

	public Student(String username, String password, String name, String phone, String email) {
		super(username, password);
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return " UserName=" + getUsername() + ",Name=" + name + ",Phone No =" + phone + ",Email=" + email;
	}
	

	
	
     
}
