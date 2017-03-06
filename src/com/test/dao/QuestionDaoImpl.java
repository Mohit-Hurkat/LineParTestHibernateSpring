package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Question;
import com.test.helper.JDBCConnection;

public class QuestionDaoImpl implements QuestionDao{
	private static final String UPDATE_QUERY = "UPDATE QUESTIONS SET SUBJECT_ID = ?, QUESTION = ?," + 
			"CHOICE1 = ?, CHOICE2 = ?, CHOICE3 = ?, CHOICE4 = ?, ANSWER = ?, VALUE = ?,ANS=? WHERE QUESTION_ID = ?";
	private static final String DELETE_QUERY = "DELETE FROM QUESTIONS WHERE QUESTION_ID = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM QUESTIONS WHERE SUBJECT_ID= ?";	
	private static final String SELECT_QUERY = "SELECT * FROM QUESTIONS WHERE QUESTION_ID = ?";
    private static final String INSERT_QUERY="INSERT INTO QUESTIONS(QUESTION_ID,SUBJECT_ID,QUESTION,CHOICE1,CHOICE2,CHOICE3,CHOICE4,ANSWER,ANS,VALUE) VALUES(?,?,?,?,?,?,?,?,?,0)";
    private static final String GET_MAX_ID_QUERY = "SELECT COALESCE(MAX(QUESTION_ID), 0) AS COUNT FROM QUESTIONS";
    private static final String GET_ANS = "SELECT ANS FROM QUESTIONS WHERE QUESTION_ID = ?";
    private int question_id;
    private int subject_id;
    private String question_1;
    private String choice_1;
    private String choice_2;
    private String choice_3;
    private String choice_4;
    private int ans2;
    private String ans1;
    
    @Override
	public boolean insert(Question question) throws IOException, ClassNotFoundException, SQLException{
		int numAffectedRows=0;
		Connection connection = JDBCConnection.getConnection();
		int questionId = this.getMaxId() + 1;
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		preparedStatement.setInt(1, questionId);
		preparedStatement.setInt(2, question.getSubjectId());
		preparedStatement.setString(3, question.getQuestion());
		preparedStatement.setString(4, question.getChoice1());
		preparedStatement.setString(5, question.getChoice2());
		preparedStatement.setString(6, question.getChoice3());
		preparedStatement.setString(7, question.getChoice4());
		preparedStatement.setInt(8, question.getAnswer());
		preparedStatement.setString(9, question.getAns());
		numAffectedRows = preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		return numAffectedRows > 0;
	}
    
    
    @Override
	public Question search(int questionId)throws IOException,ClassNotFoundException, SQLException {
		Question question = null;
		List<Question> questionList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setInt(1, questionId);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			subject_id = rs.getInt("SUBJECT_ID");
			question_1 = rs.getString("QUESTION");
			choice_1= rs.getString("CHOICE1");
			choice_2= rs.getString("CHOICE2");
			choice_3= rs.getString("CHOICE3");
			choice_4= rs.getString("CHOICE4");
			ans2 = rs.getInt("ANSWER");
			ans1=rs.getString("ANS");
			question = new Question(questionId, subject_id, question_1, ans2, choice_1, choice_2, choice_3, choice_4,ans1);
			questionList.add(question);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return question;
	}
    
    @Override
	public List<Question> displayAll(int subjectId) throws IOException,ClassNotFoundException, SQLException{
    	Question question = null;
    	List<Question> questionList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
		preparedStatement.setInt(1, subjectId);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			question_id = rs.getInt("QUESTION_ID");
			question_1 = rs.getString("QUESTION");
			choice_1= rs.getString("CHOICE1");
			choice_2= rs.getString("CHOICE2");
			choice_3= rs.getString("CHOICE3");
			choice_4= rs.getString("CHOICE4");
			ans2 = rs.getInt("ANSWER");
			ans1=rs.getString("ANS");
			question = new Question(question_id, subjectId, question_1, ans2, choice_1, choice_2, choice_3, choice_4,ans1);
			questionList.add(question);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return questionList;
	}
    
    @Override
	public boolean update(int questionId, Question question)throws IOException, ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
		preparedStatement.setInt(1, question.getSubjectId());
		preparedStatement.setString(2, question.getQuestion());
		preparedStatement.setString(3, question.getChoice1());
		preparedStatement.setString(4, question.getChoice2());
		preparedStatement.setString(5, question.getChoice3());
		preparedStatement.setString(6, question.getChoice4());
		preparedStatement.setInt(7, question.getAnswer());
		preparedStatement.setInt(8,0);
		preparedStatement.setString(9, question.getAns());
		preparedStatement.setInt(10, questionId);
		preparedStatement.executeQuery();
		preparedStatement.close();
		connection.close();
		return true;
	}
    
    @Override
	public boolean delete(int questionID) throws IOException, ClassNotFoundException, SQLException {
		int updateCount;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
		preparedStatement.setInt(1, questionID);
		preparedStatement.execute();
		updateCount = preparedStatement.getUpdateCount();
		preparedStatement.close();
		connection.close();
		return updateCount > 0;
	}
    
    
    
    public int getMaxId() throws SQLException, ClassNotFoundException{
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_ID_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		int result = rs.getInt("COUNT");
		rs.close();
		preparedStatement.close();
		connection.close();
		return result;
	}
    
    public String answer(int questionID) throws IOException, ClassNotFoundException, SQLException {
    	Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_ANS);
		preparedStatement.setInt(1, questionID);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		String ans = rs.getString("ANS");
		preparedStatement.close();
		connection.close();
    	return ans;
    
    }
}
