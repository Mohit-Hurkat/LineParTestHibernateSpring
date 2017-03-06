package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.Subject;
import com.test.bl.SubjectLogic;
 
public class SubjectHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
			SubjectLogic lc=new SubjectLogic();
			try {
				List<Subject> sub=lc.displayAll();
				if(sub!=null)
				{
					session.setAttribute("subjectDisplay", sub);//use this attribute to display data
					if (request.getParameter("display") != null){
				response.sendRedirect("./Admin/AdminSubject/deleteSubject.jsp");
				}
					else if (request.getParameter("display1") != null){
						response.sendRedirect("./Admin/AdminSubject/displaySubject.jsp");
						}
					else if (request.getParameter("display2") != null){
						response.sendRedirect("./Admin/AdminSubject/updateSubject.jsp");
						}
					else if (request.getParameter("display3") != null){
						response.sendRedirect("./Admin/AdminSubject/searchSubject.jsp");
						}
				}
				else
				{
				 
					request.setAttribute("subjectDisplay","Error.");//use this attribute to abstract info
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException | NullPointerException e) {
				System.out.println("hell3");
				e.printStackTrace();
			}  
		}
		
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
