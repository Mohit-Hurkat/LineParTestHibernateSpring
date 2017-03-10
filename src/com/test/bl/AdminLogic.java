package com.test.bl;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.test.bean.Admin;
import com.test.dao.AdminDao;
import com.test.dao.AdminDaoImpl;



public class AdminLogic {
	private ApplicationContext ctx=new ClassPathXmlApplicationContext("com/test/applicationCtx.xml");
	AdminDao adminDao=(AdminDao)ctx.getBean("AdminDao");
	
	public boolean update(String username, String password) throws IOException, ClassNotFoundException, SQLException{
		return adminDao.update(username, password);
	}
	
	public Admin search(String username) throws IOException, ClassNotFoundException, SQLException{
		return adminDao.search(username);
	}
	public boolean check(String username,String password)throws IOException,ClassNotFoundException, SQLException {
		return adminDao.check(username, password);
	}
}
