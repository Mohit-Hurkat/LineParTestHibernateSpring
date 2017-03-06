package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.Question;
import com.test.bean.Student;
import com.test.bean.PrintResult;
import com.test.bl.QuestionLogic;
import com.test.bl.ResultLogic;
import com.test.bl.TestLogic;

public class TestResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionLogic qLogic = new QuestionLogic();
	private ResultLogic rLogic = new ResultLogic();
	private TestLogic lc = new TestLogic();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("sessionUsername");		
		ArrayList<Question> ques = (ArrayList<Question>) session.getAttribute("Questions");
		List<PrintResult> resultList1 = new ArrayList<>();
		try {
			if(lc.giveTest(username, ques.get(0).getSubjectId()).equals(resultList1)){
		List<PrintResult> resultList = new ArrayList<>();
					int count = 0;
		Question questtt = null;
			Enumeration<String> questions = request.getParameterNames();
			while (questions.hasMoreElements()) {
				String question = questions.nextElement();
				int questionId = Integer.parseInt(question);
				questtt = qLogic.search(questionId);
				String ans = questtt.getAns();
				System.out.println(ans + " = " + request.getParameter(question));
				System.out.println(request.getParameter(question));
				PrintResult rest = new PrintResult(username, questtt.getSubjectId(), questionId, questtt.getQuestion(),
						questtt.getAns(), request.getParameter(question));
				resultList.add(rest);
				if (request.getParameter(question).equals(ans)) {
					count++;
				}
				System.out.println(count);
			}
			String Coun = Integer.toString(count * 10).concat(" %");
			session.setAttribute("sessionResultSet", resultList);
			session.setAttribute("sessionResult", count);
			session.setAttribute("message", "You Scored");
			session.setAttribute("message1", Coun);
			Question questt = ques.get(0);
			int subId = questt.getSubjectId();
			System.out.println(subId);
			rLogic.set(username, subId, count * 10);
			if (session.getAttribute("dontGive")==null)
				response.sendRedirect("./Student/printResult.jsp");
			else {
				response.sendRedirect("./Student/printBack.jsp");
			}
		} 
			else{
				session.setAttribute("message","Test Already Given");
				session.setAttribute("message1","Stop Using The Browser Back Button");
				response.sendRedirect("./lost.jsp");
			}
		}	catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}