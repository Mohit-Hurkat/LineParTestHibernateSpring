<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.bean.PrintResult"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.3/jspdf.debug.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style1.css" />
<script src="${pageContext.request.contextPath}/javascript/pdf.js"
	type="text/javascript"></script>
<style>
#pdf {
	text-align: centre;
}
</style>
</head>
<body>
	<c:if test="${empty sessionScope.student}">
		<c:redirect url="/home.jsp" />
	</c:if>
	<%
		String result = (String) session.getAttribute("message1");
		String username = (String) session.getAttribute("sessionUsername");
	%>
	<div class="form">
		<div class="tab-group">
			<form action="${pageContext.request.contextPath}/TestController"
				method="post">
				<h1>Test Completed</h1>
				<div id="content" style="color: white;">

					<h2>
						Username:
						<%=username%></h2>
					<h1>
						Result:
						<%=result%></h1>
				</div>
				<br>
			</form>
		</div>
		<div id="editor"></div>
		<input type="button" onclick="javascript:demoFromHTML();"
			class="button-block" id="pdf" value="Save As Pdf"><br>
		<br>
		<form action="${pageContext.request.contextPath}/StudentHelper?printt=printt"
			method="post">
			<input type="submit" class="button-block" value="Back">
		</form>
	</div>
</body>
</html>