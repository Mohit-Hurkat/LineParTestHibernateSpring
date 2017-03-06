package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.Student;
import com.test.bl.StudentLogic;


public class AdminStudentHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession(false); 
		if (request.getParameter("display") != null) {
			
				StudentLogic lc=new StudentLogic(); 
				try {
					List<Student> stu=lc.displayAll();
					if(stu!=null)
					{
						
					session.setAttribute("studentDisplay", stu);
					response.sendRedirect("./Admin/AdminStudent/deleteStudent.jsp");
					}
					else
					{
						session.setAttribute("message","Error");
						session.setAttribute("message1","");
						response.sendRedirect("./lost.jsp");
					}
				} catch (ClassNotFoundException | SQLException e) {
					session.setAttribute("message","Server Down!!!");
					session.setAttribute("message1","Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
				}
			  
			}
		else if (request.getParameter("displayAll") != null) {
				StudentLogic lc=new StudentLogic(); 
				try {
					List<Student> stu=lc.displayAll();
					if(stu!=null)
					{
						
					session.setAttribute("studentDisplay", stu);
					response.sendRedirect("./Admin/AdminStudent/viewAllStudents.jsp");
					}
					else
					{
						session.setAttribute("message","");
						session.setAttribute("message1","");
						response.sendRedirect("./lost.jsp");
					}
				} catch (ClassNotFoundException | SQLException e) {
				 
					session.setAttribute("message","Server Down!!!");
					session.setAttribute("message1","Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
				}
			  
			}
		else if (request.getParameter("search") != null) {
				StudentLogic lc=new StudentLogic(); 
				try {
					List<Student> stu=lc.displayAll();
					if(stu!=null)
					{
					session.setAttribute("studentDisplay", stu);
					response.sendRedirect("./Admin/AdminStudent/searchStudent.jsp");
					}
					else
					{
						session.setAttribute("message","");
						session.setAttribute("message1","");
						response.sendRedirect("./lost.jsp");
					}
				} catch (ClassNotFoundException | SQLException e) {
					session.setAttribute("message","Server Down!!!");
					session.setAttribute("message1","Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
				}
			  
			}
		
	}

}
