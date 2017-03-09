package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.test.bean.Admin;

@Component
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private HibernateTemplate template;
	private Admin admin = null;

	public boolean update(String username, String password) throws IOException, ClassNotFoundException, SQLException {
		admin = (Admin) template.get(Admin.class, username);
		admin.setPassword(password);
		template.update(admin);
		return true;
	}
		
		

	public Admin search(String username) throws IOException, ClassNotFoundException, SQLException {
		admin = template.get(Admin.class, username);
		return admin;
	}

	public boolean check(String username, String password) throws IOException, ClassNotFoundException, SQLException {
		Admin checkAd = search(username);
		if (checkAd.getUsername().equals(username) && checkAd.getPassword().equals(password)) {
			return true;
		}
		return false;
		}
}
