<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="com.test.bean.Subject" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
input[type=radio]{
height:auto;
width: auto;
display:inline;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN QUESTION</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
    <script>
    $(document).ready(function(){
    	var $submit = $("#sub").hide();
    	var $submit1 = $("#dis").show();
    	var    $cbs = $("input[type='radio']").click(function() {
            $submit.toggle( $cbs.is(":checked") );
            $submit1.hide();
        });
       });

</script>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<% ArrayList<Subject> subList=(ArrayList<Subject>)session.getAttribute("subjectDisplay");%>
<div class="form">
<div class="tab-group">
 <form action="${pageContext.request.contextPath}/QuestionController">

 <div style="color: white;">
	<table border="1" style="width:100%;">
	<tr>
	<th>Subject Id</th>
	<th>Subject Name</th>
	<th>Examination Start Date</th>
	<th>Examination End Date</th>
	<th>Select</th>
	</tr>
	<%for (Subject sub:subList){%>
		<tr>
			<td><%=sub.getSubjectId()%></td>
			<td><%=sub.getSubject()%></td>
			<td><%=sub.getStart()%></td>
			<td><%=sub.getEnd()%></td>
			<td><input type="radio" name="subject" value="<%=sub.getSubjectId()%>"></td>
		</tr>
	<% } %>
</table>
 </div><br>
 <div id="dis">
 <h1>Please select the Subject</h1>
 </div>
 <input type="submit" class="button-block" id="sub" name="Subject" value="Select">
</form>
</div>
<form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post" name="backForm">
 <input type="submit" class="button-block" value="Back">
          </form>
</div>
</body>
</html>