<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN UPDATE</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
    <script src="../javascript/homepage.js" type="text/javascript"></script>
</head>
<body>
<c:if test="${empty sessionScope.admin}"><c:redirect url="/home.jsp" /></c:if> 
<div class="form">
    <div class="wrap">
 	<h1>Successfully Updated.</h1>
 	<h1>Please login again.</h1>
 	<br>
 	
 	</div>
<form action="${pageContext.request.contextPath}/home.jsp" method="post" name="backForm">
 <input type="submit" class="button-block" value="Back">
          </form>
          </div>

</body>
</html>