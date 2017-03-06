package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Subject;

public interface SubjectDao {
	Subject search(int subjectId) throws IOException, ClassNotFoundException, SQLException;
	boolean update(int subjectId, Subject subject) throws IOException, ClassNotFoundException, SQLException;
	boolean delete(int subjectId) throws IOException, ClassNotFoundException, SQLException;
	List<Subject> displayAll() throws IOException, ClassNotFoundException, SQLException;
	boolean insert(String sub,String sub1,String sub2) throws IOException, ClassNotFoundException, SQLException;
	String subname(int subjectId) throws IOException, ClassNotFoundException, SQLException;
	List<Subject> showSubject(String username) throws IOException, ClassNotFoundException, SQLException;
	 
}
