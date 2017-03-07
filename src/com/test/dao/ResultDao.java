package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Result;

public interface ResultDao {
	public List<Result> show(String username) throws ClassNotFoundException, SQLException, IOException;
	public boolean set(Result result) throws ClassNotFoundException, SQLException, IOException;

}
