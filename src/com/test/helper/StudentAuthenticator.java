package com.test.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

import com.test.bean.Student;
import com.test.dao.StudentDao;
import com.test.dao.StudentDaoImpl;

public class StudentAuthenticator {
	
	public Map.Entry<Student, Boolean> authenticate(String username, String password) throws ClassNotFoundException, IOException, SQLException{
		StudentDao cdao = new StudentDaoImpl();
		Student student = cdao.search(username);
		if(student == null)
			return new AbstractMap.SimpleEntry<>(null, false);
		if(password.equals(student.getPassword()))
			return new AbstractMap.SimpleEntry<>(student, true);
		return new AbstractMap.SimpleEntry<>(student, false);
	}
}
