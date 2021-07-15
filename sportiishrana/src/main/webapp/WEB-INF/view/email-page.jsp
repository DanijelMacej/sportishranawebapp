<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">


<link rel="stylesheet" type="text/css"
	href="/sportiishrana/URLForCSS/emailPage.css">



</head>
<body>

	<div id="header">
		<h1>
			<a href="/sportiishrana/headerFrontPageLink"> Sport & ishrana <span>Sve
					na jednom mestu</span>
			</a>
		</h1>

		<ul id="navbar">

			<li><a href="/sportiishrana/mylogin">Login</a></li>

			<li><a href="/sportiishrana/registration">Registration</a></li>

			<li><a href="/sportiishrana/email">Contact</a></li>

		</ul>
	</div>


	<div id="bodyEmail">
		<h3 id="naslovH3" align="center">Contact</h3>
		<div align="center" class="container">

			<form:form action="processEmail" method="post"
				modelAttribute="emailDto">

				<div class="form-group">
					<label for="fn"><i>Name and surname :</i></label>
					<form:input id="fn" path="fullName" class="form-control" />
				</div>
				<br>

				<div class="form-group">
					<label for="em"><i>Email :</i></label>
					<form:input path="email" class="form-control" />
					<form:errors path="email" cssClass="error" />
					<br>
				</div>
				<div class="form-group">
					<label for="su"><i>Subject :</i></label>
					<form:input id="su" path="subject" class="form-control" />
				</div>
				<br>
				<div class="form-group">
					<label for="co"><i>Message :</i></label>
					<form:textarea id="co" path="content" class="form-control" />
				</div>
				<br>

				<div class="form-group">
					<input id="button" type="submit" value="send" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>