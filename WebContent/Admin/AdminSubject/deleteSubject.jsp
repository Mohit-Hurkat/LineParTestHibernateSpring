<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="java.util.ArrayList"%>
    <%@page import="com.test.bean.Subject" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN SUBJECT DELETE</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
    <script src="../javascript/homepage.js" type="text/javascript"></script>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<% ArrayList<Subject> subList=(ArrayList<Subject>)session.getAttribute("subjectDisplay");%>
<div class="form">
<div class="tab-group">
 <form action="${pageContext.request.contextPath}/SubjectController">

 	<div style="color: white;">
	<table border="1" style="width:100%;">
	<tr>
	<th>Subject Id</th>
	<th>Subject Name</th>
	<th>Examination Start Date</th>
	<th>Examination End Date</th>
 
	</tr>
	<%for (Subject sub:subList){%>
		<tr>
			<td><%=sub.getSubject_Id()%></td>
			<td><%=sub.getSubject()%></td>
			<td><%=sub.getStart_()%></td>
			<td><%=sub.getEnd_()%></td>
		 
		</tr>
	<% } %>
	</table>
 	</div><br>
 	<h1> Select  Subject :</h1><input type="number" min="1" name="subjectId" ><br>
 	<input type="submit"  value="delete" name="delete">
</form>
</div><br>
<form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post" name="backForm">
 <input type="submit" class="button-block" value="Back">
          </form>
</div>
		
</body>
</html>