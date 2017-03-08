package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.Result;
import com.test.bl.TestLogic;

public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestLogic lc = new TestLogic();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			String username = (String) session.getAttribute("sessionUsername");
			if (request.getParameter("giveTest") != null) {// check the
															// parameter name
				try {
					List<Result> result = new ArrayList<>();
					 System.out.println("a");
					System.out.println(subjectId);
					System.out.println(username);
					System.out.println(result);
					if (lc.result(username, subjectId)==0) {
						System.out.println("aa");
						if (lc.check_questions(subjectId, username)) {
							System.out.println("aaa");
							if (lc.dateCheck(subjectId)) {
								System.out.println("aaaa");
								request.setAttribute("sessionSubjectId", subjectId);
								RequestDispatcher dispatch = request.getRequestDispatcher("./Test/Rules.jsp");// change
																												// this
																												// to
																												// appropriate
																												// path
								dispatch.forward(request, response);
							} else {
								session.setAttribute("message", "Please check Test Time Period");
								session.setAttribute("message1", "Please Try Again");
								response.sendRedirect("./lost.jsp");
							}
						} else {
							session.setAttribute("message", "Questions Yet To Be Updated.");
							session.setAttribute("message1", "Please Try Again");
							response.sendRedirect("./lost.jsp");
						}
					} else {
						session.setAttribute("message", "Test Already Given");
						session.setAttribute("message1", "Please Try Again");
						response.sendRedirect("./lost.jsp");
					}
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e);
					session.setAttribute("message", "Server Down!!!");
					session.setAttribute("message1", "Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
				}
			} else {
				session.setAttribute("message", "Test Error");
				session.setAttribute("message1", "Please Try Again");
				response.sendRedirect("./lost.jsp");
			}
		} catch (NullPointerException | NumberFormatException e) {
			session.setAttribute("message", "Please Select A Subject!");
			session.setAttribute("message1", "Oops");
			response.sendRedirect("./lost.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
