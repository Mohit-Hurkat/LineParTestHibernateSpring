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

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.test.bean.Student;
import com.test.bl.StudentLogic;
 
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(signInController.class);
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if (request.getParameter("insert") != null)
		{
		 
			StudentLogic lc=new StudentLogic(); 
			String username=request.getParameter("username");
			String userpass=request.getParameter("password");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			Student student=new Student(username, userpass, name, phone, email);
			try {
				if(lc.insert(student))
				{
					request.setAttribute("studentinsert","Successfully Inserted.");
					BasicConfigurator.configure();
				 	logger.info("Sign up working!!");
					response.sendRedirect("./Student/success.jsp");
				}
				else	 
				{
					request.setAttribute("studentinsert"," Error.");
					BasicConfigurator.configure();
				 	logger.info("Sign up not working!!");
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				BasicConfigurator.configure();
			 	logger.info("Sign up not working!!");
				e.printStackTrace();
			}
		} 
		else if(request.getParameter("delete") != null)
		{
			String username=request.getParameter("username");
			StudentLogic lc=new StudentLogic(); 
			try {
				if(lc.delete(username))
				{
					session.setAttribute("studentDelete","Successfully Deleted.");
					BasicConfigurator.configure();
				 	logger.info("Student delete working!!");
					response.sendRedirect("./Admin/AdminStudent/adminStudent.jsp");
				}
				else	 
				{
					request.setAttribute("studentDelete","Error.");
					BasicConfigurator.configure();
				 	logger.info("Student delete not working!!");
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				BasicConfigurator.configure();
			 	logger.info("Student delete not working!!");
			    e.printStackTrace();
			}
		      
		}
		else if (request.getParameter("search") != null) {
			String suname=request.getParameter("username");
			StudentLogic lc=new StudentLogic(); 
			try {
				Student stu=lc.search(suname);
				if(stu.getUsername().equals(suname))
				{
					session.setAttribute("studentSearch",stu);
					BasicConfigurator.configure();
				 	logger.info("Student search working!!");
					response.sendRedirect("./Admin/AdminStudent/adminStudentSearch.jsp");
				}
				else
				{
					request.setAttribute("studentDelete","Error.");
					BasicConfigurator.configure();
				 	logger.info("Student search not working!!");
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} 
			catch(NullPointerException e){
				request.setAttribute("message","Please Select a Valid Username");
		    	 request.setAttribute("message1","Visit lenskart for better vision.");
		    	 RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");
		    	 dispatch.forward(request, response);
		    	 
			}
			catch (ClassNotFoundException | SQLException e) {
				BasicConfigurator.configure();
			 	logger.info("Student search not working!!");
				e.printStackTrace();
			}
		  
		}
		else if (request.getParameter("display") != null) {
			 
			StudentLogic lc=new StudentLogic(); 
			try {
				List<Student> stu=lc.displayAll();
				if(stu!=null)
				{
				request.setAttribute("studentdisplay", stu);
				RequestDispatcher dispatch=request.getRequestDispatcher("./student.jsp");
				dispatch.forward(request, response);
				}
				else
				{
					request.setAttribute("studentDelete","Error.");
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
			 
				e.printStackTrace();
			}
		  
		}
		else if (request.getParameter("update") != null) {
			String suname=request.getParameter("suname");
			String spass=request.getParameter("spass");
			String sname=request.getParameter("sname");
			String sphone=request.getParameter("sphone");
			String semail=request.getParameter("semail");
			Student student=new Student(suname, spass, sname, sphone, semail);
			StudentLogic lc=new StudentLogic(); 
			try {
				if(lc.update(suname, student))
				{
					request.setAttribute("studentUpdate","Successfully Updated.");
					BasicConfigurator.configure();
				 	logger.info("Student Update working!!");
					RequestDispatcher dispatch=request.getRequestDispatcher("./student.jsp");
					dispatch.forward(request, response);
				}
				else	 
				{
					request.setAttribute("studentUpdate","Error.");
					BasicConfigurator.configure();
				 	logger.info("Student Update not working!!");
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				BasicConfigurator.configure();
			 	logger.info("Student Update not working!!");
				e.printStackTrace();
			}
		  
		}
		else if (request.getParameter("updatepass") != null) {
		 
			String spass=request.getParameter("spass");
			String semail=request.getParameter("semail");
			StudentLogic lc=new StudentLogic(); 
			try {
				if(lc.updatePass(semail, spass))
				{
					request.setAttribute("studentUpdate","Successfully Updated.");
					RequestDispatcher dispatch=request.getRequestDispatcher("./student.jsp");
					dispatch.forward(request, response);
				}
				else	 
				{
					request.setAttribute("studentUpdate","Error.");
					RequestDispatcher dispatch=request.getRequestDispatcher("./lost.jsp");//change this to appropriate path
					dispatch.forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		}
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
