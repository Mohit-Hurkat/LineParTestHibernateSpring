<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ONLINE TEST</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="stylemy.css">
<script src="javascript/homepage.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.tab a').on('click', function(e) {

			e.preventDefault();

			$(this).parent().addClass('active');
			$(this).parent().siblings().removeClass('active');

			var target = $(this).attr('href');
			$('.tab-content > div').not(target).hide();

			$(target).fadeIn(600);

		});
	});
</script>
<%
	session.invalidate();
%>
</head>
<body>
	<div class="form">

		<ul class="tab-group">
			<li class="tab active"><a href="#signup">Sign Up</a></li>
			<li class="tab"><a href="#login">Log In</a></li>
		</ul>

		<div class="tab-content">
			<div id="signup">
				<h1 class="sign">Sign Up</h1>

				<form action="./StudentController" method="post">

					<div class="top-row">

						<div class="field-wrap">
							<label> Name (FirstName LastName)<span class="req">*</span>
							</label> <input type="text" name="name" pattern="^[a-zA-Z]{2,30}\s[a-zA-Z]{2,30}$" required autocomplete="off" />
						</div>


						<div class="field-wrap">
							<label> Email Address<span class="req">*</span>
							</label> <input type="email" name="email" required autocomplete="off" />
						</div>

						<div class="field-wrap">
							<label> Phone No(10 digits)<span class="req">*</span>
							</label> <input type="tel" name="phone" pattern="^\d{10}$" required
								autocomplete="off" />
						</div>

						<div class="field-wrap">
							<label> Username<span class="req">*</span>
							</label> <input type="text" name="username" required autocomplete="off" />
						</div>

						<div class="field-wrap">
							<label>Password(0-9,A-Z,a-z required)<span class="req">*</span>
							</label> <input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,})$" name="password" required
								autocomplete="off" />
						</div>

						<button type="submit" class="button button-block" name="insert" />
						Get Started
						</button>
					</div>
					<!-- top-row -->
				</form>
			</div>

			<div id="login">
				<h1>Welcome Back!</h1>

				<form action="./signIn" method="post">

					<div class="field-wrap">
						<label> Username<span class="req">*</span>
						</label> <input type="text" name="username" required autocomplete="off">
					</div>

					<div class="field-wrap">
						<label> Password<span class="req">*</span>
						</label> <input type="password" name="password" required
							autocomplete="off">
					</div>

					<p class="forgot">
						<a href="./lost.jsp">Forgot Password?</a>
					</p>

					<button class="button button-block" name="login">Log In</button>
				</form>
			</div>
			<!-- login -->
		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->
</body>
</html>