package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Subject;
import com.test.helper.JDBCConnection;

public class SubjectDaoImpl implements SubjectDao {
	private static final String INSERT_QUERY="INSERT INTO SUBJECT(SUBJECT_ID,SUBJECT_NAME,START_DATE,END_DATE) VALUES(?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'))";
	private static final String GET_MAX_ID_QUERY = "SELECT COALESCE(MAX(SUBJECT_ID), 0) AS COUNT FROM SUBJECT";
	private static final String SELECT_QUERY = "SELECT * FROM SUBJECT WHERE SUBJECT_ID = ?";   
	private static final String SELECT_ALL_QUERY = "SELECT * FROM SUBJECT";
	private static final String UPDATE_QUERY = "UPDATE SUBJECT SET SUBJECT_NAME = ? ,START_DATE = to_date(?,'yyyy-mm-dd'),END_DATE = to_date(?,'yyyy-mm-dd') WHERE SUBJECT_ID = ?";
	private static final String DELETE_QUERY = "DELETE FROM SUBJECT WHERE SUBJECT_ID = ?";
	private static final String SELECT_RESULT = "SELECT SUBJECT_ID,SUBJECT_NAME FROM SUBJECT WHERE SUBJECT_ID IN (SELECT SUBJECT_ID FROM RESULT WHERE USERNAME = ?)";
	private String start,end;
	private String dated,monthm,yeary;
	
	@Override
	public boolean insert(String sub,String date1,String date2) throws IOException, ClassNotFoundException, SQLException {
		 
			int numAffectedRows;
			Connection connection = JDBCConnection.getConnection();
			int subjectId = this.getMaxId() + 1;
			System.out.println(subjectId);
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1,subjectId);
			preparedStatement.setString(2, sub);
			preparedStatement.setString(3, date1);
			preparedStatement.setString(4, date2);
			numAffectedRows = preparedStatement.executeUpdate();  
			//System.out.println(numAffectedRows);
			preparedStatement.close();
			connection.close();
			return numAffectedRows > 0;	
	}
	private int getMaxId() throws ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_ID_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		int result = rs.getInt("COUNT");
		rs.close();
		preparedStatement.close();
		connection.close();
		return result;
	}

	@Override
	public Subject search(int subjectId) throws IOException, ClassNotFoundException, SQLException {
		Subject subject = null;
		List<Subject> subjectList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setInt(1, subjectId);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			String subject1 = rs.getString("SUBJECT_NAME");
			start = rs.getString("START_DATE");
			start=start.substring(0, 10);
			end = rs.getString("END_DATE");
			end=end.substring(0, 10);
			subject = new Subject(subjectId, subject1,start,end);
			subjectList.add(subject);
		}
		rs.close();
		preparedStatement.close();  
		connection.close();
		return subject;
	}

	@Override
	public List<Subject> displayAll() throws IOException, ClassNotFoundException, SQLException {
		List<Subject> subjectList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			int subjectId = rs.getInt("SUBJECT_ID");
			String subjectName = rs.getString("SUBJECT_NAME");
			start = rs.getString("START_DATE");
			start=start.substring(0, 10);
			end = rs.getString("END_DATE");
			end=end.substring(0, 10);
			Subject subOb = new Subject(subjectId, subjectName,start,end);
			subjectList.add(subOb);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return subjectList;
	}

	@Override
	public boolean update(int subjectId, Subject subject) throws IOException, ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
//		monthm=subject.getStart().substring(0,3);
//		dated=subject.getStart().substring(3,6);
//		yeary=subject.getStart().substring(6,10);
//		start=dated.concat(monthm);
//		start=start.concat(yeary);
		preparedStatement.setString(2, subject.getStart());
//		System.out.println(start);
//		monthm=subject.getEnd().substring(0,3);
//		dated=subject.getEnd().substring(3,6);
//		yeary=subject.getEnd().substring(6,10);
//		end=dated.concat(monthm);
//		end=start.concat(yeary);
//		System.out.println(end);
		preparedStatement.setString(3, subject.getEnd());
		preparedStatement.setString(1, subject.getSubject());
		preparedStatement.setInt(4, subjectId);
		preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		return true;
	}

	@Override
	public boolean delete(int subjectId) throws IOException, ClassNotFoundException, SQLException {
		int updateCount;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
		preparedStatement.setInt(1, subjectId);
		updateCount=preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		return updateCount > 0;
	}
	
	
	@Override
	public String subname(int subjectId) throws IOException, ClassNotFoundException, SQLException {
		String subject1=null;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setInt(1, subjectId);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
		subject1 = rs.getString("SUBJECT_NAME");
		}
		rs.close();
		preparedStatement.close();  
		connection.close();
		return subject1;
	}
	
	@Override
	public List<Subject> showSubject(String username) throws IOException, ClassNotFoundException, SQLException {
		List<Subject> subjectList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESULT);
		preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()==false){
			System.out.println("Student hasn't given any test.");
			subjectList=null;
		}
		while(rs.next()){
			int subjectId=rs.getInt("SUBJECT_ID");
			String subject = rs.getString("SUBJECT_NAME");
			System.out.println("\n Subject Id: "+subjectId+"  Subject Name: "+subject);
			Subject subOb = new Subject(subjectId, subject,"","");
			subjectList.add(subOb);
		}
		rs.close();
		preparedStatement.close();  
		connection.close();
		return subjectList;
	}

	
	

}
