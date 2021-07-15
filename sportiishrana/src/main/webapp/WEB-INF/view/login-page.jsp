<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

<link rel="stylesheet" type="text/css"
	href="/sportiishrana/URLForCSS/login.css">

<script type="text/javascript">
	function showPassword() {
		var pass = document.getElementById("pa");

		if (pass.type === "password") {
			pass.type = "text";
		} else {

			pass.type = "password";
		}
	}
</script>

</head>
<body>


	

	<div id="headerLogin">
		<h1>
			<a href="/sportiishrana/headerFrontPageLink"> Sport & ishrana <span>Sve
					na jednom mestu</span>
			</a>
		</h1>

		<ul id="navbar">

			<li><a href="/sportiishrana/home">Home</a></li>



		</ul>
	</div>



	<div id="LoginForm" align="center">
		<form:form action="process-login" method="post">

               <c:if test="${param.error !=null }">
                <p>1.Proverite da li ste na mejlu koji smo vam poslali prilikom
				registracije kliknuli na link radi verifikacije vaseg naloga</p>
			<p>2.Ako je vas nalog verifikovan onda podaci koje ste uneli nisu
				tacni, pokusajte ponovo</p>
                 </c:if>


			<div class="form-group">
				<label for="un">Username :</label> 
				<input id="un" type="text" name="username" class="form-control">
              
                 
			</div>



			<label for="pa">Password :</label>
			<div class="buttonInside">
				<input id="pa" type="password" name="password" class="form-control">

				<button class="btn btn-outline-none" type="button"
					onclick="showPassword()">
					<i class="fa fa-eye"></i>
				</button>
			</div>


			<div class="form-group">

				<input type="submit" value="login" class="btn btn-success btn-lg"><br>
				<br> <a href="/sportiishrana/forgotpassword">Forgot password?</a>
			</div>

		</form:form>
	</div>

</body>
</html>