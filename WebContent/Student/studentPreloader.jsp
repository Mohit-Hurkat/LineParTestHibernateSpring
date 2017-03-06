<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<meta http-equiv="refresh" content="6;url=${pageContext.request.contextPath}/Student/student.jsp"/>
<link rel="stylesheet" type="text/css" href="./style.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
<script src="https://cdn.rawgit.com/coderitual/odoo/feature/codevember16/lib/odoo.js" type="text/javascript"></script>
<script src="http://code.responsivevoice.org/responsivevoice.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	<% String name=(String)session.getAttribute("sessionName");%>
	var parts = "<%=name%>".split(" ");
	var first = parts.shift();
			odoo.default({ el:'.js-odoo', from: 'Hola', to: first, animationDelay: 1000 });
			responsiveVoice.speak("Hello"+"<%=name%>", "UK English Male",{rate:0.75});
});
</script>
</head>
<body>
<div class="js-odoo"></div>
</body>
</html>