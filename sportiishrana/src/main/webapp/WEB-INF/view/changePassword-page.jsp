
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">


<link rel="stylesheet" type="text/css"
	href="/sportiishrana/URLForCSS/changePassword.css">


<script type="text/javascript">
	function showPassword() {
		var pass = document.getElementById("pass");

		if (pass.type === "password") {
			pass.type = "text";
		} else {

			pass.type = "password";
		}
	}

	function showConfirmPassword() {
		var pass = document.getElementById("conf");

		if (pass.type === "password") {
			pass.type = "text";
		} else {

			pass.type = "password";
		}
	}
</script>

<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>

	<div id="changePasswordForm">
		<form:form action="process-changepassword" modelAttribute="userDto">
			<form:label for="pass" path="password">New password:</form:label>
			<div class="buttonInside">
				<form:password id="pass" path="password" class="form-control"
					cssErrorClass="errorInput" />
				<button class="btn btn-outline-none" type="button"
					onclick="showPassword()">
					<i class="fa fa-eye"></i>
				</button>
				<form:errors path="password" cssClass="error" />

			</div>


			<form:label for="conf" path="confirmpassword">Confirm password:</form:label>
			<div class="buttonInside">
				<form:password id="conf" path="confirmpassword" class="form-control"
					cssErrorClass="errorInput" />
				<button class="btn btn-outline-none" type="button"
					onclick="showConfirmPassword()">
					<i class="fa fa-eye"></i>
				</button>
				<form:errors path="confirmpassword" cssClass="error" />

			</div>


			<input type="submit" value="confirm" class="btn btn-success btn-lg">


		</form:form>
	</div>
</body>
</html>