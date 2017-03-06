package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Result;

public interface ResultDao {
	public List<Result> show(String username) throws ClassNotFoundException, SQLException, IOException;
	public boolean set(String username,int subjectId,int result) throws ClassNotFoundException, SQLException, IOException;
	public boolean update(String username,int subjectId,int result) throws ClassNotFoundException, SQLException, IOException;
}
