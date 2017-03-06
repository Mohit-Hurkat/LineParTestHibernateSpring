
package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.test.bean.Admin;

public interface AdminDao {
	
	boolean update(String username, String password) throws IOException, ClassNotFoundException, SQLException;
	Admin search(String username) throws IOException, ClassNotFoundException, SQLException;
	boolean check(String username,String password)throws IOException,ClassNotFoundException, SQLException;
}
