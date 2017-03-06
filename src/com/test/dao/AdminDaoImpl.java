package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Admin;
import com.test.bean.Student;
import com.test.bl.AdminLogic;
import com.test.helper.JDBCConnection;

public class AdminDaoImpl implements AdminDao{
	private static final String UPDATE_QUERY = "UPDATE ADMIN SET PASSWORD = ? WHERE USERNAME = ?";
	private static final String DELETE_QUERY = "DELETE FROM ADMIN WHERE USERNAME = ?";
	private static final String SELECT_QUERY = "SELECT * FROM ADMIN WHERE USERNAME = ?";
	
	@Override
	public boolean update(String username,String password) throws IOException, ClassNotFoundException, SQLException {
		int numAffectedRows=0;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, username);
		numAffectedRows=preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		return numAffectedRows>0;
	}
 
	@Override
	public Admin search(String username)throws IOException,ClassNotFoundException, SQLException {
		Admin admin=new Admin("","");
		List<Admin> adminList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){    //ask why next
			String password= rs.getString("PASSWORD");
			admin = new Admin(username,password);
			adminList.add(admin);
			}
		preparedStatement.close();
		connection.close();
		return admin;
	}
	
	public boolean check(String username,String password)throws IOException,ClassNotFoundException, SQLException {
	AdminLogic adLogic=new AdminLogic();
	Admin checkAd=adLogic.search(username);
	if(checkAd.getUsername().equals(username) && checkAd.getPassword().equals(password))
	{
		return true;
	}
	return false;
	}
}
