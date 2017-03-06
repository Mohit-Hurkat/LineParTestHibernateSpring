<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.bean.Student"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.debug.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style1.css" />
<script src="${pageContext.request.contextPath}/javascript/pdf.js"
	type="text/javascript"></script>
<title>ADMIN STUDENT DISPLAY</title>
</head>
<body>
	<c:if test="${empty sessionScope.admin}">
		<c:redirect url="/home.jsp" />
	</c:if>
	<%
		ArrayList<Student> studentList = (ArrayList<Student>) session.getAttribute("studentDisplay");
	%>
	<div class="form">
		<div class="tab-group">
			<form action="${pageContext.request.contextPath}/StudentController">

				<div id="content" style="color: white;">
					<table border="1" style="width: 100%;">
						<tr>
							<th>Name</th>
							<th>Username</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Password</th>
						</tr>
						<%
							for (Student stu : studentList) {
						%>
						<tr>
							<td><%=stu.getName()%></td>
							<td><%=stu.getUsername()%></td>
							<td><%=stu.getPhone()%></td>
							<td><%=stu.getEmail()%></td>
							<td><%=stu.getPassword()%></td>

						</tr>
						<%
							}
						%>
					</table>
				</div>
				<br>

			</form>
		</div>
		<br> <input type="button" onclick="printDiv('content');"
			class="button-block" id="pdf" value="Print Content"><br>
		<br>
		<form
			action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp"
			method="post" name="backForm">
			<input type="submit" class="button-block" value="Back">
		</form>
	</div>

</body>
</html>