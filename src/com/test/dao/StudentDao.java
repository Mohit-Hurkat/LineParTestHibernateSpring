package com.test.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Student;

public interface StudentDao {
	public boolean delete(String username) throws IOException, ClassNotFoundException, SQLException;
	boolean insert(Student student) throws IOException, ClassNotFoundException, SQLException;
	Student search(String username) throws IOException, ClassNotFoundException, SQLException;
	List<Student> displayAll() throws IOException,ClassNotFoundException, SQLException;
    boolean update(String username, Student newStudent) throws IOException, ClassNotFoundException, SQLException;
	boolean updatePass(String mailId, String password) throws IOException, ClassNotFoundException, SQLException;
	public boolean check(String username,String password)throws IOException,ClassNotFoundException, SQLException;
}
