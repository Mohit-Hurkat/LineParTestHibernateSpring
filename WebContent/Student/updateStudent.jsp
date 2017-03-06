<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
<title>UPDATED</title>
</head>
<%String message=(String)request.getAttribute("studentUpdate");%>
<body>
<c:if test="${empty sessionScope.student}"><c:redirect url="/home.jsp" /></c:if> 
<div class="form">
          <h1 class="sign"><%=message%></h1>
         <button  class="back-button"><a href="home.jsp" >BACK</a></button>
</div>
</body>
</html>