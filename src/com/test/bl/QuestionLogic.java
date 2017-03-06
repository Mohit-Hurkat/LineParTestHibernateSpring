package com.test.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.test.bean.Question;
import com.test.dao.QuestionDao;
import com.test.dao.QuestionDaoImpl;

public class QuestionLogic 
{
	private QuestionDao qdao=new QuestionDaoImpl();
	
	public boolean insert(Question question) throws IOException, ClassNotFoundException, SQLException
	{
		return qdao.insert(question);
		
	}
	public Question search(int question_Id) throws IOException, ClassNotFoundException, SQLException
	{
		return qdao.search(question_Id);
		
	}
	public List<Question> displayAll(int subjectId) throws IOException, ClassNotFoundException, SQLException
	{
		return qdao.displayAll(subjectId);
		
	}
	public boolean update(int questionId, Question question) throws IOException, ClassNotFoundException, SQLException
	{
		return qdao.update(questionId, question);
		
	}
	public boolean delete(int questionID) throws IOException, ClassNotFoundException, SQLException
	{
		return qdao.delete(questionID);
		
	}
    public String answer(int questionID) throws IOException, ClassNotFoundException, SQLException {
    	return qdao.answer(questionID);
    }
	
}
