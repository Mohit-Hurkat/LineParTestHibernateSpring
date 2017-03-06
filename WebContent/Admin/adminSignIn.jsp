<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN HOME PAGE</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Admin/admin.css">
    <script>
	var audio = new Audio('../heartbeat.mp3');
    audio.play();
    </script>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
	<div class="form">
      
      	<ul class="tab-group">
        	<li class="tab " id="question"><a>Question</a>
        	<ul class="dropdown-content">
        		<li style="height: 49px;"></li>
        	 	
        		<li class="float"><a href="../AdminQuestion/?delete=delete"> Delete Question</a></li>
        		<li class="float"><a href="../AdminQuestion/?insert=insert">Insert Question</a></li>
        		<li  class="float"><a href="../AdminQuestion/?update=update">Update Question</a></li>
        		<li  class="float"><a href="../AdminQuestion/?viewAll=viewAll"> View All Questions</a></li>
        	</ul>
        	</li>
        	<li class="tab" id="updateInfo"><a>Student</a>
        	<ul class="dropdown-content">
        		<li style="height: 49px;"></li>
        		<li class="float"><a  href="../AdminStudentHelper?display=display">Delete Student</a></li>
        		<li class="float"><a  href="../AdminStudentHelper?search=search">Search Student</a></li>
        		<li class="float"><a  href="../AdminStudentHelper?displayAll=displayAll">View All Students</a></li>
        	</ul>
			 </li>
         	<li class="tab " id="subject" ><a>Subject</a>
         	<ul class="dropdown-content">
        		<li style="height: 49px;"></li>
        		<li class="float"><a href="../SubjectHelper?display=display">Delete Subject</a></li>
        		<li class="float"><a href="../SubjectHelper?display1=display1">Display Subject</a></li>
        		<li class="float"><a href="./AdminSubject/insertSubject.jsp">Insert Subject</a></li>
        		<li class="float"><a href="../SubjectHelper?display3=display3">Search Subject</a></li>
        		<li class="float"><a href="../SubjectHelper?display2=display2">Update Subject</a></li>
        	</ul>
        	</li>
        	<li class="tab " id="update" ><a>Admin</a>
        	<ul class="dropdown-content">
        		<li style="height: 49px;"></li>
        		<li class="float"><a href="../Admin/AdminAdmin/adminAdmin.jsp">Update Admin</a></li>
        	</ul>
        	</li>
		</ul>
	</div> <!-- /form -->
 <%@include file="../Test/logout.jsp" %>
</body>
</html>