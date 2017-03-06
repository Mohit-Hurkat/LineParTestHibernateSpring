<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN SUBJECT INSERT</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style1.css"/>
    <script src="${pageContext.request.contextPath}/javascript/homepage.js" type="text/javascript"></script>
<script type="text/javascript">
$( document ).ready(function(){
$('.tab a').on('click', function (e) {
	  
	  e.preventDefault();
	  
	  $(this).parent().addClass('active');
	  $(this).parent().siblings().removeClass('active');
	  
	  var target = $(this).attr('href');

	  $('.tab-content > div').not(target).hide();
	  
	  $(target).fadeIn(600);
	  
	});	
	});

	</script>
</head>
<body>
<div class="form">
      
      
      <div class="tab-content">
          <h1 class="sign">Insert Subject</h1>
          
          <form action="${pageContext.request.contextPath}/SubjectController " method="post">
          
          <div class="top-row">
          
          	<div class="field-wrap">
            <label>
            Subject Name <span class="req">*</span>
            </label>
            <input type="text"  name="subjectName" required autocomplete="off"/>
            </div>

          
			<div class="field-wrap">
			<label class="active">
           Start Date (mm/dd/yyyy)<span class="req">*</span>
            </label>
            <input type="date" pattern="\d{1,2}/\d{1,2}/\d{4}"  name="subjectDate1" required autocomplete="off"/>
          	</div>
          
            <div class="field-wrap">
	        <label class="active">
           	End State(mm/dd/yyyy)<span class="req">*</span>
            </label>
            <input type="date" pattern="\d{1,2}/\d{1,2}/\d{4}" name="subjectDate2"  required autocomplete="off"  />
            </div>
          
            
          	    
          <button type="submit" class="button button-block" name="insert"/>Insert</button>
          </div><!-- top-row -->
          </form>

        
       
      </div><!-- tab-content -->
     <br> <form action="${pageContext.request.contextPath}/Admin/adminSignIn.jsp" method="post" name="backForm">
 <input type="submit" class="button-block" value="Back">
          </form>
</div> <!-- /form -->
</body>
</html>