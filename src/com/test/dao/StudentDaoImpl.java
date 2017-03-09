package com.test.dao;

import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Question;
import com.test.bean.Student;

@Component
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private HibernateTemplate template;
	private Student stud = null;
	private String userNull=null;

	@Override
	public boolean insert(Student student) throws IOException, ClassNotFoundException, SQLException {
		if (student.getUsername().equals("admin")) {
			System.out.println("admin : Username Not Allowed");
			return false;
		}
		if(search(student.getUsername()).equals(userNull)){
			template.save(student);
			System.out.println(student);
			return true;
		}
		return false;

	}

	@Override
	public Student search(String username) throws IOException, ClassNotFoundException, SQLException {
		stud=(Student)template.get(Student.class, username);
		System.out.println(stud);
		return stud;
	}

	@Override
	public List<Student> displayAll() throws IOException, ClassNotFoundException, SQLException {
		List<Student> studentList =new ArrayList<Student>();
		studentList=template.loadAll(Student.class);
		return studentList;
	}

	@Override
	public boolean update(String username, Student student) throws IOException, ClassNotFoundException, SQLException {
		template.update(student);
		return true;			
	}

	@Override
	public boolean delete(String username) throws IOException, ClassNotFoundException, SQLException {
		stud=(Student)template.get(Student.class, username);
		template.delete(stud);
		return true;
	}

	public boolean check(String username, String password) throws IOException, ClassNotFoundException, SQLException {
		Student checkStudent = search(username);
		if (checkStudent.getUsername().equals(username) && checkStudent.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}