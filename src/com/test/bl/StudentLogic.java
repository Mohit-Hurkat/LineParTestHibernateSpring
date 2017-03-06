package com.test.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Student;
import com.test.dao.StudentDao;
import com.test.dao.StudentDaoImpl;

public class StudentLogic {
	private StudentDao studentDao = new StudentDaoImpl();
	
	public boolean delete(String username) throws IOException, ClassNotFoundException, SQLException{
		return studentDao.delete(username);
	}
	
	public boolean insert(Student student) throws IOException, ClassNotFoundException, SQLException{
		return studentDao.insert(student);
	}
	
	public Student search(String username) throws IOException, ClassNotFoundException, SQLException{
		return studentDao.search(username);
	}
	
	public List<Student> displayAll() throws IOException,ClassNotFoundException, SQLException{
		return studentDao.displayAll();
	}
	
	public boolean update(String username, Student newStudent) throws IOException, ClassNotFoundException, SQLException{
		return studentDao.update(username, newStudent);
	}
	
	public boolean updatePass(String mailId, String password) throws IOException, ClassNotFoundException, SQLException{
		return studentDao.updatePass(mailId, password);
	}
	public boolean check(String username,String password)throws IOException,ClassNotFoundException, SQLException {
		return studentDao.check(username, password);
	}

}
