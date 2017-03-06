package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bl.TestLogic;
import com.test.bean.Question;

public class GiveTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestLogic lc=new TestLogic();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		
		String username=(String)session.getAttribute("sessionUsername");
		try {
			int subject=Integer.parseInt(request.getParameter("subjectIdd"));
			List<Question> quest=new ArrayList<>();
			quest=lc.getQuestions(username, subject);
			session.setAttribute("Questions",quest);
			response.sendRedirect("./Test/giveTest.jsp");
		} catch (InterruptedException | ClassNotFoundException | SQLException | NullPointerException e) {
			session.setAttribute("message","Server Down!!!");
			session.setAttribute("message1","Please Contact The Administrator.");
			response.sendRedirect("./lost.jsp");
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
