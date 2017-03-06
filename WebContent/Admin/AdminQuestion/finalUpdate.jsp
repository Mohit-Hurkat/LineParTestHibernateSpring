<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="com.test.bean.Question" %>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN QUESTION UPDATE</title>
<% Question question=(Question)request.getAttribute("sessionQuestion"); %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<div class="form"> 
      <div class="tab-content">
<h1>Please enter the question</h1>

</div>
 <br>
 <div>
 <form action="${pageContext.request.contextPath}/QuestionController">
  <% Question quest=(Question)session.getAttribute("sessionQuestion"); %>
  You requested to Update Question Id : <%=quest.getQuestionId() %>
 <br>
<input type="hidden" name="questionId" value="<%=quest.getQuestionId()%>">
 Subject ID<input type="text" name="subject" value="<%=quest.getSubjectId()%>" >
 Question<input type="text" name="question" value="<%=quest.getQuestion()%>"><br>
 Option 1 <input type="text" name="op1" value="<%=quest.getChoice1()%>">
 Option 2 <input type="text" name="op2" value="<%=quest.getChoice2()%>">
 Option 3 <input type="text" name="op3" value="<%=quest.getChoice3()%>">
 Option 4 <input type="text" name="op4" value="<%=quest.getChoice4()%>">
 <br>
 Answer <input type="number" name="answer" value="<%=quest.getAnswer()%>">
 </div>
 <br>
 <input type="submit" value="submit" name="insert">
 </form>
 <form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post">
 <input type="submit" class="button-block" value="Back">
 </form>
</div>
</body>
</html>