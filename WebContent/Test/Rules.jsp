<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
<title>RULES PAGE</title>
<style>
p{
color:white;
}
</style>
<%
if(session.getAttribute("giveTestSession")!=null){
	session.setAttribute("forceSubmit", "force");
    response.sendRedirect("./Test/giveTest.jsp");
}
%>
</head>
<body>
<div class="form" style="margin: 40px 300px 40px 300px;text-align: center;">
    <div class="wrap">
  <form action="${pageContext.request.contextPath}/GiveTest" >
  <input type="hidden" name="subjectIdd" value="<%=(Integer)request.getAttribute("sessionSubjectId")%>">
<p>------------Don't Press Anything Until The Test Starts------------</p>
		<p>-------------------INSTRUCTIONS FOR ONLINE EXAM-------------------</p>
		<p>You will be given only 25 seconds to attempt every question</p>
		<p>You can't skip any question</p>
		<p>Every question has 4 options out of which only one is correct</p>
		<p>You can't attempt the same test twice</p>
		<p>Once answered you can't change your answer</p>
		<p>Each question is of one mark</p>
		<p>Your result will be displayed as per the number of correct questions you have answered in percentage</p>
 		<input type="submit" class="button-block" value="Start">
		</form>
		</div>
</div>
</body>
</html>