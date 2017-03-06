<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="com.test.bean.Student" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
<title>ADMIN STUDENT SEARCH</title>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<% ArrayList<Student> studentList=(ArrayList<Student>)session.getAttribute("studentDisplay");%>
<div class="form">
<div class="tab-group">
 <form action="${pageContext.request.contextPath}/StudentController">

 	<div style="color: white;">
	<table border="1" style="width:100%;">
	<tr>
	<th>Username</th>

	</tr>
	<%for (Student stu:studentList){%>
		<tr>
			<td><%=stu.getUsername()%></td>
		</tr>
	<% } %>
	</table>
 	</div><br>
 	<h1> Search User :</h1><input type="text" name="username" ><br>
 	<input type="submit"  value="search" name="search">
</form>
</div>
<br>
<form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post" name="backForm">
 <input type="submit" class="button-block" value="Back">
          </form>
</div>
</body>
</html>