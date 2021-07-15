<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	href="/sportiishrana/URLForCSS/forgotPassword.css">

<style type="text/css">
</style>

</head>
<body>

	<div id="displayMessage" align="center" class="container">
		<c:if test="${param.confrmemail !=null }">


			<p>Poslali smo na Vas mail link za promenu sifre</p>


		</c:if>
	</div>



	<div id="displayMessage" align="center" class="container">
		<c:if test="${param.errorNotConfirmMail !=null }">


			<p>Prvo morate potvrditi link koji smo Vam poslali za promenu
				sifre</p>


		</c:if>
	</div>


	<div id="headerForgotPassword">
		<h1>
			<a href="/sportiishrana/headerFrontPageLink"> Sport & ishrana <span>Sve
					na jednom mestu</span>
			</a>
		</h1>

		<ul id="navbar">

			<li><a href="/sportiishrana/home">Naslovna</a></li>

			<!-- LtEaJWWqPV6s3FgPsHuNCXiVJG4np56oXTBhbCrRKb5boGtH5gYDmgFhdLeg8cXT -->

		</ul>
	</div>

	<div id="SendMailForChangePasswordForm" align="center">
		<form:form action="process-forgotpassword" method="post"
			modelAttribute="userDto">

			<div class="form-group">
				<label for="em">Email :</label>
				<form:input id="em" path="mailDto.email" class="form-control"
					cssErrorClass="cssErrorClass" />
				<form:errors path="mailDto.email" cssClass="error" />

			</div>
			<div class="form-group">
				<input type="submit" value="send" class="btn btn-success btn-lg"
					class="form-control">
			</div>


		</form:form>
	</div>
</body>
</html>