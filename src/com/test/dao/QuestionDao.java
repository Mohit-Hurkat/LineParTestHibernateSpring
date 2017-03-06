package com.test.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Question;

public interface QuestionDao {
	boolean insert(Question question) throws IOException, ClassNotFoundException, SQLException;
	Question search(int question_Id) throws IOException, ClassNotFoundException, SQLException;
	List<Question> displayAll(int subjectId) throws IOException, ClassNotFoundException, SQLException;
	boolean update(int questionId, Question question) throws IOException, ClassNotFoundException, SQLException;
	boolean delete(int questionID) throws IOException, ClassNotFoundException, SQLException;
    public String answer(int questionID) throws IOException, ClassNotFoundException, SQLException;
	

}
