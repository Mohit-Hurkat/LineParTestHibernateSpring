package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Question;
import com.test.bean.Result;

public interface TestDao {
	public List<Result> giveTest(String username,int sujectId) throws ClassNotFoundException, SQLException, InterruptedException;
	public int result(String username,int subjectId) throws ClassNotFoundException, SQLException;
	public boolean check(int subjectId) throws ClassNotFoundException, SQLException;
	public boolean dateCheck(int subject_id) throws SQLException, ClassNotFoundException;
	public boolean result_student(String username) throws ClassNotFoundException, SQLException, IOException;
	public boolean check_questions(int subjectId,String username) throws ClassNotFoundException, SQLException, IOException;
	public List<Question> getQuestions(String username,int subjectId) throws ClassNotFoundException, SQLException, InterruptedException;
}
