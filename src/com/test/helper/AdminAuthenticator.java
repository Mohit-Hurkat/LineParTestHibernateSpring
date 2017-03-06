package com.test.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

import com.test.bean.Admin;
import com.test.dao.AdminDao;
import com.test.dao.AdminDaoImpl;


public class AdminAuthenticator {
	public Map.Entry<Admin, Boolean> authenticate(String username, String password) throws ClassNotFoundException, IOException, SQLException{
		AdminDao adminDao = new AdminDaoImpl();
		Admin admin = adminDao.search(username);
		if(admin == null)
			return new AbstractMap.SimpleEntry<>(null, false);
		if(password.equals(admin.getPassword()))
			return new AbstractMap.SimpleEntry<>(admin, true);
		return new AbstractMap.SimpleEntry<>(admin, false);
	}
}
