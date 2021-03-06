package com.test.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Question;
import com.test.bean.Result;
import com.test.dao.TestDao;
import com.test.dao.TestDaoImpl;

public class TestLogic {
	private TestDao tdao=new TestDaoImpl();
	
	 public int result(String username,int subjectId) throws ClassNotFoundException, SQLException{
		 return  tdao.result(username, subjectId);
	 }
	 public boolean dateCheck(int subject_id) throws SQLException, ClassNotFoundException{
		 return tdao.dateCheck(subject_id);
	 }
	 public boolean check_questions(int subjectId,String username) throws ClassNotFoundException, SQLException, IOException{
			return tdao.check_questions(subjectId,username);
	 }
	 public List<Question> getQuestions(String username,int subjectId) throws ClassNotFoundException, SQLException, InterruptedException{
		 return tdao.getQuestions(username, subjectId);
	 }
}
