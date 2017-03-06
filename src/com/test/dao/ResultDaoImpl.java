package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.test.bean.Result;
import com.test.helper.JDBCConnection;

public class ResultDaoImpl implements ResultDao{

	private static final String Check_Result2="Select * from RESULT WHERE USERNAME=?";
	private static final String Set_Result="INSERT INTO RESULT(USERNAME,SUBJECT_ID,RESULT,TIME_) VALUES(?,?,?,to_date(sysdate,'dd-mm-yy'))";
	private static final String Update_Result="Update RESULT SET RESULT = ? WHERE USERNAME = ? AND SUBJECT_ID= ?" ;
	public List<Result> show(String username) throws ClassNotFoundException, SQLException, IOException{
		List<Result> resultList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_Result2);
		preparedStatement.setString(1,username);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()){
			int subjectId = rs.getInt("SUBJECT_ID");
			int result = rs.getInt("RESULT");
			Date date = rs.getDate("TIME_");
			Result res = new Result(username, subjectId, result, date);
			resultList.add(res);
		}
		preparedStatement.close();
		connection.close();
		return resultList;
	}
	
	public boolean set(String username,int subjectId,int result) throws ClassNotFoundException, SQLException, IOException{
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Set_Result);
		preparedStatement.setString(1,username);
		preparedStatement.setInt(2,subjectId);
		preparedStatement.setInt(3,result);
		preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		return false;
		
	}
	public boolean update(String username,int subjectId,int result) throws ClassNotFoundException, SQLException, IOException{
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Set_Result);
		preparedStatement.setString(2,username);
		preparedStatement.setInt(3,subjectId);
		preparedStatement.setInt(1,result);
		preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		return false;
		
	}
}
