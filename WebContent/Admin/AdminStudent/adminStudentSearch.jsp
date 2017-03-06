<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="com.test.bean.Student" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN STUDENT SEARCH</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
    <script src="../javascript/homepage.js" type="text/javascript"></script>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<div class="form">
<div class="tab-group">
 <form action="${pageContext.request.contextPath}/StudentController">
 <div style="color: white;">
 <h1>Student Details</h1>
	<table border="1" style="width:100%;">
	<tr>
	<th>Name</th>
	<th>Username</th>
	<th>Phone</th>
	<th>Email</th>
	<th>Password</th>
	</tr>
	<% Student stu=(Student)session.getAttribute("studentSearch"); %>
		<tr>
			<td><%=stu.getName()%></td>
			<td><%=stu.getUsername()%></td>
			<td><%=stu.getPhone()%></td>
			<td><%=stu.getEmail()%></td>
			<td><%=stu.getPassword()%></td>
		</tr>
	</table>
	</div>
	</form>
	</div><br>
	<form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post" name="backForm">
 <input type="submit" class="button-block" value="Back">
          </form>
	</div>
</body>
</html>