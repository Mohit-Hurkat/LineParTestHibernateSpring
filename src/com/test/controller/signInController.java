package com.test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class signInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("username");
		String pass=request.getParameter("password");
		String login= request.getParameter("login");
		String admin="admin";
		if(user.equals(admin)){
			request.setAttribute("username", user);
			request.setAttribute("password", pass);
			request.setAttribute("login", login);
			RequestDispatcher dispatch=request.getRequestDispatcher("./AdminController");
			dispatch.forward(request, response);
		}
		else{
			request.setAttribute("username", user);
			request.setAttribute("password", pass);
			request.setAttribute("login", login);
			RequestDispatcher dispatch=request.getRequestDispatcher("./StudentHelper");
			dispatch.forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
