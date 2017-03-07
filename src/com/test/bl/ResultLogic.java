package com.test.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.dao.ResultDao;
import com.test.dao.ResultDaoImpl;
import com.test.bean.Result;


public class ResultLogic {
	private ResultDao rdao=new ResultDaoImpl();
	public List<Result> show(String username) throws ClassNotFoundException, SQLException, IOException{
		return rdao.show(username);
	}
	public boolean set(Result result) throws ClassNotFoundException, SQLException, IOException{
		return rdao.set(result);
	}
}
