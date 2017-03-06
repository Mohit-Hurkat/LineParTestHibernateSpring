package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Admin;
import com.test.bean.Student;
import com.test.bl.AdminLogic;
import com.test.bl.StudentLogic;
import com.test.helper.JDBCConnection;

public class StudentDaoImpl implements StudentDao{
	private static final String UPDATE_QUERY = "UPDATE STUDENT SET PASSWORD = ?," + 
			"NAME = ?, PHONE = ?,EMAIL =? WHERE USERNAME = ?";
	private static final String DELETE_QUERY = "DELETE FROM STUDENT WHERE USERNAME = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM STUDENT";	
	private static final String SELECT_QUERY = "SELECT * FROM STUDENT WHERE USERNAME = ?";
    private static final String INSERT_QUERY="INSERT INTO STUDENT(USERNAME,PASSWORD,NAME,PHONE,EMAIL) VALUES(?,?,?,?,?)";
    private static final String UPDATE_PASS = "UPDATE STUDENT SET PASSWORD = ? WHERE EMAIL = ?";
    
    @Override
	public boolean insert(Student student) throws IOException, ClassNotFoundException, SQLException{
		int numAffectedRows=0;
		if(student.getUsername().equals("admin"))
		{
			System.out.println("admin : Username Not Allowed");
			return false;
		}
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		preparedStatement.setString(1, student.getUsername());
		preparedStatement.setString(2, student.getPassword());
		preparedStatement.setString(3, student.getName());
		preparedStatement.setString(4, student.getPhone());
		preparedStatement.setString(5, student.getEmail());
		numAffectedRows = preparedStatement.executeUpdate();  
//		System.out.println(numAffectedRows);
		preparedStatement.close();
		connection.close();
		return numAffectedRows > 0;
	}

	@Override
	public Student search(String username)throws IOException,ClassNotFoundException, SQLException {
		Student student=null;
		List<Student> studentList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){    //ask why next
			String StudentName = rs.getString("NAME");
			String password= rs.getString("PASSWORD");
			String StudentPhone = rs.getString("PHONE");
			String StudentEmail = rs.getString("Email");
			student = new Student(username,password,StudentName,StudentPhone, StudentEmail);
			studentList.add(student);
			}
		preparedStatement.close();
		connection.close();
		return student;
	}

	@Override
	public List<Student> displayAll() throws IOException,ClassNotFoundException, SQLException{
		List<Student> studentList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			String StudentUsername = rs.getString("USERNAME");
			String StudentName = rs.getString("NAME");
			String StudentPhone = rs.getString("PHONE");
			String StudentEmail = rs.getString("Email");
			Student student = new Student(StudentUsername,"********",StudentName,StudentPhone, StudentEmail);
			studentList.add(student);
		}
		preparedStatement.close();
		connection.close();
		return studentList;
	}

	@Override
	public boolean update(String username, Student newStudent)throws IOException, ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
		preparedStatement.setString(1, newStudent.getPassword());
		preparedStatement.setString(2, newStudent.getName());
		preparedStatement.setString(3, newStudent.getPhone());
		preparedStatement.setString(4, newStudent.getEmail());
		preparedStatement.setString(5, username);
		preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		return true;
	}

	@Override
	public boolean delete(String username) throws IOException, ClassNotFoundException, SQLException {
		int updateCount;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
		preparedStatement.setString(1, username);
		preparedStatement.execute();
		updateCount = preparedStatement.getUpdateCount();
		preparedStatement.close();
		connection.close();
		return updateCount > 0;
	}
	
	@Override
	public boolean updatePass(String mailId,String password)throws IOException, ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASS);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, mailId);
		preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		return true;
	}
	
	public boolean check(String username,String password)throws IOException,ClassNotFoundException, SQLException {
		StudentLogic studentLogic=new StudentLogic();
		Student checkStudent=studentLogic.search(username);
		if(checkStudent.getUsername().equals(username) && checkStudent.getPassword().equals(password))
		{
			return true;
		}
		return false;
		}

}
