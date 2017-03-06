<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.util.ArrayList"%>
    <%@page import="com.test.bean.Question" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN QUESTION DELETE</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
<script type="text/javascript">
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
<div class="form"> 
      <div class="tab-content">
<h1>Select Questions To be Deleted</h1>
</div>
 <br>
<% ArrayList<Question> questionList=(ArrayList<Question>)session.getAttribute("sessionQuestionAll"); %>
<div class="form">
<div class="tab-group">
 <form action="${pageContext.request.contextPath}/QuestionController">

 	<div style="color: white;">
	<table border="1" style="width:100%;">
	<tr>
	<th>Questions</th>
 
 
	</tr>
<%for (Question ques:questionList){%>
		<tr>
		<td><%=ques.getQuestionId()%></td>
			<td><%=ques.getQuestion()%></td>
			<td><input type="radio" name="questionId" value="<%=ques.getQuestionId()%>"></td>
		</tr>
	<% } %>
	</table>
 	</div><br>
 	 <div id="dis">
 <h1>Please Select a question to delete</h1>
 </div>
 <input type="submit" id="sub">
</form>
</div><br>
 <form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post">
 <input type="submit" class="button-block" value="Back">
 </form>
</div>
</div>
</body>
</html>