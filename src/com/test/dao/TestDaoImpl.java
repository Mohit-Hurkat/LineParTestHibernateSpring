package com.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;
import java.sql.Connection;
import com.test.bean.Question;
import com.test.bean.Result;
import com.test.bean.Subject;
import com.test.bl.SubjectLogic;
import com.test.helper.JDBCConnection;
import com.test.helper.StudentData;

public class TestDaoImpl implements TestDao {
	private static final String Set_Value="update QUESTIONS SET VALUE = 0 WHERE VALUE= ?";
	private static final String Call_Question="select * FROM ( "+
			"select * FROM QUESTIONS ORDER BY DBMS_RANDOM.RANDOM)"+
			"WHERE rownum <=10 AND SUBJECT_ID = ? AND VALUE != ? ";
	private static final String Set_Value1="update QUESTIONS SET VALUE = ? where QUESTION_ID = ?";
	private static final String Set_Result="INSERT INTO RESULT(USERNAME,SUBJECT_ID,RESULT,TIME_) VALUES(?,?,?,to_date(sysdate,'yyyy-mm-dd'))";
	private static final String Check_Result="Select * from RESULT WHERE USERNAME=? AND SUBJECT_ID=? ";
	private static final String Check_Questions="Select COUNT(*) from QUESTIONS WHERE SUBJECT_ID=? ";
	private static final String Check_="Select COUNT(*) from SUBJECT WHERE SUBJECT_ID=? ";
	private static final String Check_Date="Select START_DATE,END_DATE from SUBJECT WHERE SUBJECT_ID=? ";
	private static final String Check_Result2="Select SUBJECT_ID,RESULT from RESULT WHERE USERNAME=?";
	private static final String Check_Res="Select COUNT(*) from RESULT WHERE USERNAME =?";
	private StudentData studentD=new StudentData();
	private int ques=0;
	
	public boolean giveTest1(String username,int subjectId) throws ClassNotFoundException, SQLException, InterruptedException{
		Scanner scanner=new Scanner(System.in);
		 int ans1=0,count=0;
		 int i,j,k,l;
		 int numAffectedRows;
		 String ans=null;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement1 = connection.prepareStatement(Call_Question);
		preparedStatement1.setInt(1,subjectId);
		ResultSet rs = preparedStatement1.executeQuery();
		PreparedStatement preparedStatement2 = connection.prepareStatement(Set_Value1);
			while(rs.next()){
				long endTime = System.currentTimeMillis() + 25000;
				int questionid = rs.getInt(1);
				String question=rs.getString(3);
				i= (int)(Math.random()*4);
				i=i+4;
				j=((i+1)%4)+4;
				k=((j+1)%4)+4;
				l=((k+1)%4)+4;
				String choice1=rs.getString(i);
				String choice2=rs.getString(j);
				String choice3=rs.getString(k);
				String choice4=rs.getString(l);
				String answer=rs.getString("ANS");
				System.out.println("Question: "+question+"\n 1. "+
				choice1+"\n 2. "+choice2+"\n 3. "+choice3+"\n 4. "+choice4 );
				System.out.println("Enter Your Answer Number");
				ans=scanner.nextLine();
				switch(ans){
				case "1": ans=choice1;
				break;
				case "2": ans=choice2;
				break;
				case "3": ans=choice3;
				break;
				case "4": ans=choice4;
				break;
				default: ans=" ";
					System.out.println("Invalid Choice");
				}
				System.out.println("");
				if (System.currentTimeMillis() > endTime){
					System.out.println("Time Exceeded");
					ans=" ";
				}				
				if(ans.equals(answer)){
					count++;					
				}
				preparedStatement2.setInt(1,questionid);
				preparedStatement2.executeQuery();
			}
			PreparedStatement preparedStatement3 = connection.prepareStatement(Set_Result);
			preparedStatement3.setString(1,username);
			preparedStatement3.setInt(2, subjectId);
			preparedStatement3.setInt(3, count);
			numAffectedRows = preparedStatement3.executeUpdate();
			preparedStatement1.close();
			preparedStatement2.close();
			connection.close();
			return numAffectedRows > 0;
	}
	
	public int result(String username,int subjectId) throws ClassNotFoundException, SQLException{
		int res=0;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_Result);
		preparedStatement.setString(1,username);
		preparedStatement.setInt(2,subjectId);
		ResultSet rs1=preparedStatement.executeQuery();
		while(rs1.next()){
			res=rs1.getInt(3);
		}
		preparedStatement.close();
		connection.close();
		return res;
	}
	
	public boolean check(int Subject_id) throws SQLException, ClassNotFoundException{
		int res1=0;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_);
		preparedStatement.setInt(1,Subject_id);
		ResultSet res=preparedStatement.executeQuery();
		while(res.next()){
		res1=res.getInt(1);
		}
		if(res1==0){
			preparedStatement.close();
			connection.close();
			return false;
		}
		preparedStatement.close();
		connection.close();
		return true;
	}
	
	
	public boolean dateCheck(int Subject_id) throws SQLException, ClassNotFoundException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String date1=dateFormat.format(date);
		String res1 = null,res2=null;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_Date);
		preparedStatement.setInt(1,Subject_id);
		ResultSet res=preparedStatement.executeQuery();
		while(res.next()){
		res1=res.getString(1);
		res2=res.getString(2);
		res1=res1.substring(0, 10);
		res2=res2.substring(0, 10);
		}
		if(res1.compareTo(date1)<0 && res2.compareTo(date1)>0)
		{
			preparedStatement.close();
			connection.close();
			return true;
		}
		preparedStatement.close();
		connection.close();
		return false;
	}
	
	public boolean result_student(String username) throws ClassNotFoundException, SQLException, IOException{
		int res=0;
		SubjectLogic subjectbl=new SubjectLogic();
		Subject subject=null;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_Result2);
		preparedStatement.setString(1,username);
		ResultSet rs1=preparedStatement.executeQuery();
		PreparedStatement preparedStatement1 = connection.prepareStatement(Check_Res);
		preparedStatement1.setString(1,username);
		ResultSet rs2=preparedStatement1.executeQuery();	
		while(rs2.next()){
		res=rs2.getInt(1);
		}
		while(rs1.next()){
			int subject_id=rs1.getInt(1);
			res=rs1.getInt(2);
			subject=subjectbl.search(subject_id);
			System.out.println("Subject Name:" +subject.getSubject()+" and your Score in Percentage is "+(res*10)+"%");
		}
		preparedStatement1.close();
		preparedStatement.close();
		connection.close();
		return true;
	}
	
	
	public List<Result> giveTest(String username,int subjectId) throws ClassNotFoundException, SQLException, InterruptedException{
		List<Result> resultList = new ArrayList<>();
		Result res=null;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_Result);
		preparedStatement.setString(1,username);
		preparedStatement.setInt(2,subjectId);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next()){
			int result = rs.getInt("RESULT");
			Date date = rs.getDate("TIME_");
			System.out.println(result);
			res = new Result(username, subjectId, result, date);
			System.out.println(res+"hello");
			resultList.add(res);
		}
		preparedStatement.close();
		connection.close();
		return resultList;
	}
	
	public boolean check_questions(int subjectId,String username) throws ClassNotFoundException, SQLException, IOException{
	
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(Check_Questions);
		preparedStatement.setInt(1,subjectId);
		ResultSet res=preparedStatement.executeQuery();
		if(res.next()){
		ques=res.getInt(1);
		preparedStatement.close();
		if (ques>=10){
			preparedStatement = connection.prepareStatement(Set_Value);
			preparedStatement.setString(1,(username));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return true;
			}
		}
		preparedStatement.close();
		connection.close();
		return false;
	}
	
	public List<Question> getQuestions(String username,int subjectId) throws ClassNotFoundException, SQLException, InterruptedException{
		 int i,j,k,l;
		 List<Question> quesList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement1 = connection.prepareStatement(Call_Question);
		preparedStatement1.setInt(1,subjectId);
		preparedStatement1.setString(2,username);
		ResultSet rs = preparedStatement1.executeQuery();
		PreparedStatement preparedStatement2 = connection.prepareStatement(Set_Value1);
			while(rs.next()){
//				long endTime = System.currentTimeMillis() + 25000;
				int questionId = rs.getInt(1);
				String question=rs.getString(3);
				i= (int)(Math.random()*4);
				i=i+4;
				j=((i+1)%4)+4;
				k=((j+1)%4)+4;
				l=((k+1)%4)+4;
				String choice1=rs.getString(i);
				String choice2=rs.getString(j);
				String choice3=rs.getString(k);
				String choice4=rs.getString(l);
				int answer=rs.getInt("ANSWER");
				String ans=rs.getString("ANS");
				Question que=new Question(questionId, subjectId, question, answer, choice1, choice2, choice3, choice4, ans);
				quesList.add(que);
				preparedStatement2.setString(1,username);
				preparedStatement2.setInt(2,questionId);
				preparedStatement2.executeQuery();
			}
			preparedStatement1.close();
			preparedStatement2.close();
			connection.close();
			return quesList;
		}


}
