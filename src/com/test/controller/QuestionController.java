package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.test.bean.Question;
import com.test.bean.Subject;
import com.test.bl.QuestionLogic;
import com.test.bl.SubjectLogic;


 
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectLogic sLogic=new SubjectLogic();
	private Subject sub=null;
	private QuestionLogic qLogic=new QuestionLogic();
	private static Logger logger=Logger.getLogger(QuestionController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 //System.out.println("ques");
		HttpSession session=request.getSession(false);
		
		
		if (session.getAttribute("call").equals("insert")){
			int subjectId=Integer.parseInt(request.getParameter("subject")); //subject is subjectId
			session.setAttribute("sessionSubjectId", subjectId);
			try {
				sub=sLogic.search(subjectId);
					session.setAttribute("sessionSubject",sub);//use this attribute to abstract info
					session.setAttribute("call", "insertIntoTable");
					 response.sendRedirect("./Admin/AdminQuestion/insertQuestion.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				response.sendRedirect("./lost.jsp");
			}
		}
		else if(session.getAttribute("call").equals("insertIntoTable")){
			int subjectId=Integer.parseInt(request.getParameter("subject")); //subject is subjectId
			session.setAttribute("sessionSubjectId", subjectId);
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			String question=request.getParameter("question");
			String op1=request.getParameter("op1");
			String op2=request.getParameter("op2");
			String op3=request.getParameter("op3");
			String op4=request.getParameter("op4");
			int answer=Integer.parseInt(request.getParameter("answer"));
			Question ques=new Question(questionId, subjectId, question, answer, op1, op2, op3, op4, op1);
			try {
				if(qLogic.insert(ques)){
					session.setAttribute("message","Inserted Successfully");
					BasicConfigurator.configure();
				 	logger.info("Admin Questuion insert working!!");
						response.sendRedirect("./Admin/AdminQuestion/final.jsp");
					}
				else
				{
					session.setAttribute("message","Inserted Failed");
					session.setAttribute("message1", "Oops!!!");
					BasicConfigurator.configure();
				 	logger.info("Admin Questuion insert failed working!!");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Questuion insert not working!!");
				response.sendRedirect("./lost.jsp");
			}
			
		}
		else if(session.getAttribute("call").equals("deleteQues")){
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			try {
				if(qLogic.delete(questionId)){
					session.setAttribute("message","Deleted Successfully");
					BasicConfigurator.configure();
				 	logger.info("Admin Questuion delete working!!");
					response.sendRedirect("./Admin/AdminQuestion/final.jsp");
					}
				else
				{
					session.setAttribute("message","Deletion Unsuccessful");
					session.setAttribute("message1","Oops!!!");
					BasicConfigurator.configure();
				 	logger.info("Admin Questuion delete not working!!");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Questuion delete not working!!");
				response.sendRedirect("./lost.jsp");
			}
			
		}
		else if (session.getAttribute("call").equals("search")) {
			int subjectId=Integer.parseInt(request.getParameter("subject")); //subject is subjectId
			session.setAttribute("sessionSubjectId", subjectId);
			try {
				sub=sLogic.search(subjectId);
					session.setAttribute("sessionSubject",sub);//use this attribute to abstract info
					BasicConfigurator.configure();
				 	logger.info("Admin Questuion search working!!");
					response.sendRedirect("./Admin/AdminQuestion/searchQuestion.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Questuion search not working!!");
				response.sendRedirect("./lost.jsp");
			}
		  
		}
		
		else if (session.getAttribute("call").equals("displayAll")) {
			int subjectId=Integer.parseInt(request.getParameter("subject")); //subject is subjectId
			session.setAttribute("sessionSubjectId", subjectId);
			try {
				List<Question> ques=qLogic.displayAll(subjectId);
				if(ques!=null){
				session.setAttribute("sessionQuestionAll",ques);
				BasicConfigurator.configure();
			 	logger.info("Admin Questuion display all working!!");
					response.sendRedirect("./Admin/AdminQuestion/viewAllQuestion.jsp");
				}
				else{
					session.setAttribute("message","Error");
					session.setAttribute("message1","");
					BasicConfigurator.configure();
				 	logger.info("Admin Questuion display all not working!!");
						response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Questuion display all working!!");
				response.sendRedirect("./lost.jsp");
			}
		  
		}		
		
		else if (session.getAttribute("call").equals("update")) {
			int subjectId=Integer.parseInt(request.getParameter("subject")); //subject is subjectId
			session.setAttribute("sessionSubjectId", subjectId);
			try {
				List<Question> ques=qLogic.displayAll(subjectId);
				if(ques!=null){
				session.setAttribute("sessionQuestionAll",ques);
				session.setAttribute("call", "updateQues");
					response.sendRedirect("./Admin/AdminQuestion/updateQuestion.jsp");
				}
			}catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				response.sendRedirect("./lost.jsp");
			}
		}
		
		else if(session.getAttribute("call").equals("updateQues")){
			int subjectId=(Integer)session.getAttribute("sessionSubjectId"); //subject is subjectId
						int questionId=Integer.parseInt(request.getParameter("questionId"));
						try {
							Question qu=qLogic.search(questionId);
							String question=qu.getQuestion();
							String op1=qu.getChoice1();	
							String op2=qu.getChoice2();
							String op3=qu.getChoice3();
							String op4=qu.getChoice4();
							int answer=qu.getAnswer();
							Question ques=new Question(questionId, subjectId, question, answer, op1, op2, op3, op4, op1);
							session.setAttribute("sessionQuestion", ques);
							session.setAttribute("call", "finalQues");
							BasicConfigurator.configure();
						 	logger.info("Admin Questuion update working!!");
							response.sendRedirect("./Admin/AdminQuestion/finalUpdate.jsp");
						}
						catch (Exception e) {
							session.setAttribute("message","Update Unsuccessful");
							session.setAttribute("message1","Please Contact The Administrator.");
							BasicConfigurator.configure();
						 	logger.info("Admin Questuion update not working!!");
							response.sendRedirect("./lost.jsp");
						}
						
		}
		
		else if (session.getAttribute("call").equals("finalQues")) {
			int questionId=Integer.parseInt(request.getParameter("questionId"));
			int subjectId=Integer.parseInt(request.getParameter("subject"));
			String question=request.getParameter("question");
			String op1=request.getParameter("op1");
			String op2=request.getParameter("op2");
			String op3=request.getParameter("op3");
			String op4=request.getParameter("op4");
			int answer=Integer.parseInt(request.getParameter("answer"));
			Question ques=new Question(questionId, subjectId, question, answer, op1, op2, op3, op4, op1);
			try {
					if(qLogic.update(questionId, ques)){
						session.setAttribute("sessionQuestionAll",ques);
						session.setAttribute("message", "Update Successful");
					response.sendRedirect("./Admin/AdminQuestion/final.jsp");
				}
					else{
						session.setAttribute("message","Update Failed");
						session.setAttribute("message1", "");
						response.sendRedirect("./lost.jsp");
					}
			}catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				response.sendRedirect("./lost.jsp");
			}
		}
		
		
		
		
		else if(session.getAttribute("call").equals("delete")){
			int subjectId=Integer.parseInt(request.getParameter("subject")); //subject is subjectId
			session.setAttribute("sessionSubjectId", subjectId);
			try {
				sub=sLogic.search(subjectId);
				List<Question> ques=qLogic.displayAll(sub.getSubjectId());
				if(ques!=null){
					session.setAttribute("sessionQuestionAll",ques);//use this attribute to abstract info
					session.setAttribute("call", "deleteQues");
					response.sendRedirect("./Admin/AdminQuestion/deleteQuestion.jsp");
				}
				else
				{
				session.setAttribute("message","Detele Failed");
				session.setAttribute("message1", "");
				response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				response.sendRedirect("./lost.jsp");
			}
		}
		
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
