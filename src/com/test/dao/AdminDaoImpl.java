package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.test.bean.Admin;
import com.test.bl.AdminLogic;

public class AdminDaoImpl implements AdminDao {

	public boolean update(String username, String password) throws IOException, ClassNotFoundException, SQLException {
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure("hibernateAdmin.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("update admin set PASSWORD =:pass where USERNAME =:user");
			query.setString("pass", password);
			query.setString("user", username);
			List<Admin> adminList = query.list();
			System.out.println("hell");
			for (Admin admin : adminList) {
				System.out.println(admin);
			}
			tx.commit();
			return true;

		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return false;
	}
	
	public Admin search(String username)throws IOException,ClassNotFoundException, SQLException {
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		// cfg.configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("SELECT * FROM ADMIN WHERE USERNAME=:user");
			query.setString("user", username);
			List<Admin> adminList = query.list();

			for (Admin admin : adminList) {
				System.out.println(admin);
			}
			tx.commit();
			Admin admin =adminList.get(0);
			return admin;

		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
		return null;
	}
	

	// private static final String UPDATE_QUERY = "UPDATE ADMIN SET PASSWORD = ?
	// WHERE USERNAME = ?";
	// private static final String SELECT_QUERY = "SELECT * FROM ADMIN WHERE
	// USERNAME = ?";
	//
	// @Override
	// public boolean update(String username,String password) throws
	// IOException, ClassNotFoundException, SQLException {
	// int numAffectedRows=0;
	// Connection connection = JDBCConnection.getConnection();
	// PreparedStatement preparedStatement =
	// connection.prepareStatement(UPDATE_QUERY);
	// preparedStatement.setString(1, password);
	// preparedStatement.setString(2, username);
	// numAffectedRows=preparedStatement.executeUpdate();
	// preparedStatement.close();
	// connection.close();
	// return numAffectedRows>0;
	// }
	//
	// @Override
	// public Admin search(String username)throws
	// IOException,ClassNotFoundException, SQLException {
	// Admin admin=new Admin("","");
	// List<Admin> adminList = new ArrayList<>();
	// Connection connection = JDBCConnection.getConnection();
	// PreparedStatement preparedStatement =
	// connection.prepareStatement(SELECT_QUERY);
	// preparedStatement.setString(1, username);
	// ResultSet rs = preparedStatement.executeQuery();
	// if(rs.next()){ //ask why next
	// String password= rs.getString("PASSWORD");
	// admin = new Admin(username,password);
	// adminList.add(admin);
	// }
	// preparedStatement.close();
	// connection.close();
	// return admin;
	// }
	//
	 public boolean check(String username,String password)throws
	 IOException,ClassNotFoundException, SQLException {
	 AdminLogic adLogic=new AdminLogic();
	 Admin checkAd=adLogic.search(username);
	 if(checkAd.getUsername().equals(username) &&
	 checkAd.getPassword().equals(password))
	 {
	 return true;
	 }
	 return false;
	 }
}
