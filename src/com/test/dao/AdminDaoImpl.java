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
	
	private Configuration cfg;
	private SessionFactory factory;
	private Session session ;
	public AdminDaoImpl(){
		cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();

	}

	public boolean update(String username, String password) throws IOException, ClassNotFoundException, SQLException {
		session = factory.openSession();
		Transaction tx = null;
		try {
			System.out.println("hellaaa");
			tx = session.beginTransaction();
			System.out.println("hellbbb");
			Query query = session.createQuery("UPDATE ADMIN SET PASSWORD=:pass WHERE USERNAME =:user");
			query.setString("pass", password);
			query.setString("user", username);
			List<Admin> adminList = query.list();
			System.out.println("hellccc");
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
		session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM ADMIN WHERE USERNAME=:user");
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
