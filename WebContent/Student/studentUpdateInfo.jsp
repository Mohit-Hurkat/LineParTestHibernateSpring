<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@page import="com.test.bean.Student" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPDATE INFORMATION</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylemy.css"/>
    <script src="../javascript/homepage.js" type="text/javascript"></script>
    <script>
$(document).ready(function() {
        $("#updateInfo").addClass("active");
});

function Validation() {
	    var x = document.getElementById("updatePassword").value;
	    var y = document.getElementById("confirmPassword").value;
	    if(x!=y){
	    	alert("Password doesn't match.");
	    	 document.getElementById('confirmPassword').value="";
	    	 document.getElementById('updatePassword').value="";
	    	return false;
	    }
	    return true;
	
}
</script>
</head>
<body>
<c:if test="${empty sessionScope.student}"><c:redirect url="/home.jsp" /></c:if> 
<div class="form">
<form action="../StudentHelper?update=update" method="post">
          
          <div class="top-row">
          	<div class="field-wrap">
            <label class="active highlight">
           Student name<span class="req">*</span>
            </label>
            <input type="text"  name="updateName" value="${studentUpdate.name}" required autocomplete="off"/>
            </div>
			<div class="field-wrap">
            <label class="active highlight">            
          Update Email Address<span class="req">*</span>
            </label>
            <input type="email"  name="updateEmail" value="${studentUpdate.email}" required autocomplete="off"/>
          	</div>
            <div class="field-wrap">
            <label class="active highlight">            
            Update Password(0-9,A-Z,a-z required)<span class="req">*</span>
            </label>
            <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,})$" name="updatePassword" id="updatePassword" required autocomplete="off"/>
          	</div>   
          	<div class="field-wrap">
            <label class="active highlight">            
           Confirm Password<span class="req">*</span>
            </label>
            <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,})$" name="confirmPassword" id="confirmPassword" required autocomplete="off" onBlur="Validation();"/>
          	</div>  
          	<div class="field-wrap">
            <label class="active highlight">            
          Update Phone No (10 digits)<span class="req">*</span>
            </label>
            <input type="tel"  name="updatePhone" pattern="^\d{10}$" value="${studentUpdate.phone}" required autocomplete="off" />
            </div>
            
          <input type="submit" class="button button-block" value="Submit">
          </div><!-- top-row -->
</form><br>

 <form action="${pageContext.request.contextPath}/Student/student.jsp" method="post">
 <input type="submit" class="button-block" value="Back">
 </form>
</div>
</body>
</html>