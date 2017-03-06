package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.Admin;
import com.test.bl.AdminLogic;

 
 
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Admin admin=null;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("login") != null) {
			HttpSession session=request.getSession(); 
			String user=request.getParameter("username");
			String pass=request.getParameter("password");
			AdminLogic lc=new AdminLogic(); 
			try {
				if(lc.check(user,pass))
				{	admin=new Admin(user, pass); 
					session.setAttribute("admin",admin);
			        session.setAttribute("username",user); //use this attribute to display data
			        response.sendRedirect("./Admin/adminPreloader.jsp");
				}
				else
				{
					session.setAttribute("message","Invalid Credentials");
					session.setAttribute("message1","Hey Admin,We Don't Expect This From You.");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				 session.setAttribute("message","Server Down.");
					session.setAttribute("message1","Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
			}
		} 
		else if(request.getParameter("update") != null)
		{
			HttpSession session=request.getSession(false); 
	        String user="admin";
		     AdminLogic adLogic=new AdminLogic();
		     Admin ad=null;
		     try {		 
				String newpass=request.getParameter("password");
				if(adLogic.update(user, newpass))
				{
					session.setAttribute("message","Your Credentials Have Been Updated");
					session.setAttribute("message1","Please Login In Again!!");
					response.sendRedirect("./lost.jsp");
				}
				else
				{
					session.setAttribute("message","");
					session.setAttribute("message1","");
					response.sendRedirect("./lost.jsp");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
		  
				 session.setAttribute("message","Server Down.");
					session.setAttribute("message1","Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
			}
		}
		else if (request.getParameter("search") != null) {
			HttpSession session=request.getSession(false);  
	        String user=(String)session.getAttribute("username");  
		     AdminLogic adLogic=new AdminLogic();
		     try {
				Admin ad=adLogic.search(user);
				request.setAttribute("adminsearch", ad);//use this attribute to display data
				RequestDispatcher dispatch=request.getRequestDispatcher("./admin.jsp");
				dispatch.forward(request, response);
			}
		     catch (ClassNotFoundException | SQLException e) {
		    	 session.setAttribute("message","Server Down.");
					session.setAttribute("message1","Please Contact The Administrator.");
					response.sendRedirect("./lost.jsp");
			} 
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		doGet(request, response);
	}
}
