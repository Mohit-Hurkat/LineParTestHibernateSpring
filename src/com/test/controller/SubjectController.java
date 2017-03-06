package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.test.bean.Subject;
import com.test.bl.SubjectLogic;
 
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(AdminController.class);
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if (request.getParameter("insert") != null) {
		
			SubjectLogic lc=new SubjectLogic(); 
			String subname=request.getParameter("subjectName");
			String subdate1=request.getParameter("subjectDate1");
			String subdate2=request.getParameter("subjectDate2");
			 
			try {
				
				if(lc.insert(subname,subdate1,subdate2))
				{
				 
					session.setAttribute("mess","Successfully Inserted.");//use this attribute to abstract info
					BasicConfigurator.configure();
				 	logger.info("Admin Subject insert working!!");
					response.sendRedirect("./Admin/adminSubject.jsp");
					 
				}
				else	 
				{
					session.setAttribute("message","Insertion Failed");
					session.setAttribute("message1", "");
					BasicConfigurator.configure();
				 	logger.info("Admin Subject insert not working!!");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Invalid Input Format");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Subject insert not working!!");
				response.sendRedirect("./lost.jsp");
			}
		} 
		else if(request.getParameter("delete") != null)
		{
			try {
			int subid=Integer.parseInt(request.getParameter("subjectId"));
			SubjectLogic lc=new SubjectLogic(); 
			
				if(lc.delete(subid))
				{
					session.setAttribute("mess","Successfully Deleted.");//use this attribute to abstract info
					BasicConfigurator.configure();
				 	logger.info("Admin Subject delete working!!");
					response.sendRedirect("./Admin/adminSubject.jsp");
				}
				else	 
				{
					session.setAttribute("message","Deletion Failed");
					session.setAttribute("message1", "");
					BasicConfigurator.configure();
				 	logger.info("Admin Subject delete not working!!");
					response.sendRedirect("./lost.jsp");
				}
			}
			catch(NullPointerException | NumberFormatException e){
					session.setAttribute("message","Please Select a Valid Subject");
		    	 session.setAttribute("message1","Visit lenskart for better vision.");
		    	 BasicConfigurator.configure();
				 	logger.info("Admin Subject delete not working!!");
		    	response.sendRedirect("./lost.jsp");
		    	 
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Subject delete not working!!");
				response.sendRedirect("./lost.jsp");
			}
		      
		}
		else if (request.getParameter("search") != null) {
			int subid=Integer.parseInt(request.getParameter("subjectId"));
			SubjectLogic lc=new SubjectLogic();
		 
			try {
				Subject sub=lc.search(subid);
				if(sub.getSubjectId()==subid)
				{				 
					session.setAttribute("subjectSearch", sub);//use this attribute to display data
					BasicConfigurator.configure();
				 	logger.info("Admin Subject search working!!");
					response.sendRedirect("./Admin/AdminSubject/adminSubjectSearch.jsp");
				}
				else
				{
					session.setAttribute("message","Search Failed");
					session.setAttribute("message1", "");
					BasicConfigurator.configure();
				 	logger.info("Admin Subject search not working!!");
					response.sendRedirect("./lost.jsp");
				}
			} catch (NullPointerException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Subject search not working!!");
				response.sendRedirect("./lost.jsp");
			}
			
			catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Subject search not working!!");
				response.sendRedirect("./lost.jsp");
			}
		  
		}
		else if (request.getParameter("display") != null) {
			 
			SubjectLogic lc=new SubjectLogic();
			try {
				List<Subject> sub=lc.displayAll();
				if(sub!=null)
				{
					session.setAttribute("subjectDisplay", sub);//use this attribute to display data
					BasicConfigurator.configure();
				 	logger.info("Admin Subject display all working!!");
					response.sendRedirect("./Admin/AdminSubject/displaySubject.jsp");
				 
				}
				else
				{
					session.setAttribute("message","Error");
					session.setAttribute("message1", "");
					BasicConfigurator.configure();
				 	logger.info("Admin Subject display all not working!!");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException | NullPointerException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Subject display all not working!!");
				response.sendRedirect("./lost.jsp");
			}
		  
		}
		else if (request.getParameter("update") != null) {
			int subid=Integer.parseInt(request.getParameter("subjectId"));
			String subname=request.getParameter("subjectName");
			String subdate1=request.getParameter("subjectDate1");
			System.out.println(subdate1);
			String res1=subdate1.substring(0,3);
			System.out.println(res1);
			String subdate2=request.getParameter("subjectDate2");
			System.out.println(subdate2);
			Subject sub=new Subject(subid, subname, subdate1, subdate2);
			SubjectLogic lc=new SubjectLogic();
			try {
				if(lc.update(subid,sub))
				{
					session.setAttribute("mess","Successfully Updated.");//use this attribute to abstract info
					BasicConfigurator.configure();
				 	logger.info("Admin Subject update working!!");
					 response.sendRedirect("./Admin/adminSubject.jsp");
				}
				else	 
				{
					session.setAttribute("message","Update Failed");
					session.setAttribute("message1", "");
					BasicConfigurator.configure();
				 	logger.info("Admin Subject update not working!!");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				BasicConfigurator.configure();
			 	logger.info("Admin Subject update not working!!");
				response.sendRedirect("./lost.jsp");
			}
		  
		}
		else if (request.getParameter("showsubject") != null) {//use this function in appropriate place.It is not called directly through main page.
		 
			String suname=request.getParameter("suname");
			SubjectLogic lc=new SubjectLogic();
			try {
				List<Subject> sub=lc.showSubject(suname);
				if(sub!=null)
				{
				request.setAttribute("subjectShow", sub);//use this attribute to display data
				RequestDispatcher dispatch=request.getRequestDispatcher("./subject.jsp");
				dispatch.forward(request, response);
				}
				else
				{
					session.setAttribute("message","");
					session.setAttribute("message1", "");
					response.sendRedirect("./lost.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				session.setAttribute("message","Server Error!!!");
				session.setAttribute("message1","Please Contact The Administrator.");
				response.sendRedirect("./lost.jsp");
			}
		  
		}
		else if (request.getParameter("subjectname") != null) {//use this function in appropriate place.It is not called directly through main page.
			 
			int subid=Integer.parseInt(request.getParameter("subid"));
			SubjectLogic lc=new SubjectLogic();
			try {
				String sub=lc.subname(subid);
				if(sub!=null)
				{
				request.setAttribute("subjectName", sub);//use this attribute to display data
				RequestDispatcher dispatch=request.getRequestDispatcher("./subject.jsp");
				dispatch.forward(request, response);
				}
				else
				{
					session.setAttribute("message","");
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
