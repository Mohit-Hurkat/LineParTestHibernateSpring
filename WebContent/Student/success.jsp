<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
<title>SUCCESS</title>
</head>
<body>
	<div class="form">
		<div class="tab-group">
			<h1 id="construct" style="font-family: samarkan;">NAMASTE</h1><br>
			<h1>Successfully sign up</h1>
			<br>
 			<form action="${pageContext.request.contextPath}/home.jsp" method="post">
 				<input type="submit" class="button-block" value="Back">
 			</form>
		</div>
	</div>
</body>
</html>