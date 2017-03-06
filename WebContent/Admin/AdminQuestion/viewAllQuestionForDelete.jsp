<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="com.test.bean.Question" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ADMIN QUESTION DISPLAY</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
    <script>
$(document).ready(function() {
        $("#giveTest").addClass("active");
});
</script>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<% ArrayList<Question> subList=(ArrayList<Question>)session.getAttribute("sessionQuestionAll"); 
int subject=(Integer)(session.getAttribute("sessionSubjectId"));%>
<div class="form">
	<div class="tab-group">
 		<div style="color: white;">
 			<h2>Subject ID: <%=subject%></h2>
			<table border="1" style="width:100%;">
				<tr>
					<th>Question Id</th>
					<th>Question</th>
				</tr>
				<%for (Question sub:subList){%>
				<tr>
					<td><%=sub.getQuestionId()%></td>
					<td><%=sub.getQuestion()%></td> 
				</tr>
				<% } %>
			</table>
 		</div>
 	</div>

 <br>
 <form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post">
 <input type="submit" class="button-block" value="Back">
 </form>
  </div>
</body>
</html>