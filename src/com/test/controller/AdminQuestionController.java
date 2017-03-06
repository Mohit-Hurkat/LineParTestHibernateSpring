package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.Subject;
import com.test.bl.SubjectLogic;

/**
 * Servlet implementation class AdminQuestionController
 */
public class AdminQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
			SubjectLogic lc=new SubjectLogic();
			try {
				List<Subject> sub=lc.displayAll();
				if(sub!=null)
				{
					if (request.getParameter("delete") != null) {
						session.setAttribute("call","delete");
					}
					else if (request.getParameter("insert") != null) {
						session.setAttribute("call","insert");
					}
					else if (request.getParameter("update") != null) {
						session.setAttribute("call","update");
					}
					else if (request.getParameter("viewAll") != null) {
						session.setAttribute("call","displayAll");
					}
				session.setAttribute("subjectDisplay", sub);//use this attribute to display data
				response.sendRedirect("../Admin/AdminQuestion/Question.jsp");
				}
				else
				{
					session.setAttribute("message","");
					session.setAttribute("message1","");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException | NullPointerException e) {
				session.setAttribute("message","Server Down!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				response.sendRedirect("./lost.jsp");
			}  
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
