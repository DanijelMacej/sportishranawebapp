<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous"> -->


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>


<link rel="stylesheet" type="text/css"
	href="/sportiishrana/URLForCSS/registration.css">

<!--    -->



<style type="text/css">
.classError {
	color: red;
	font-size: 15px;
}

/* input:required {
	border-color: red;
} */
</style>

<script type="text/javascript">
  
  function showPassword() {
  var pass = document.getElementById("pass");
  
  if (pass.type==="password") {
	pass.type = "text";
}else{
	
	pass.type = "password";
}
}
  
  
  function showConfirmPassword() {
var conf=document.getElementById("conf");
if (conf.type==="password") {
	conf.type = "text";
}else {
	conf.type = "password";
}
}
  
  
  
 

  
  
 
  
  </script>



</head>
<body>


	




	<div id="headerRegistration">
		<h1>
			<a href="/sportiishrana/headerFrontPageLink"> Sport & ishrana <span>Sve
					na jednom mestu</span>
			</a>
		</h1>

		<ul id="navbar">

			<li><a href="/sportiishrana/home">Naslovna</a></li>



		</ul>
	</div>


	<!--  class="was-validated" -->
   
	<div id="forma" align="center" >
		<img alt="" src="/sportiishrana/URLForImages/sport.jpg"
			class="trening" >

		<h1>Registracija</h1>

		<form:form name="myForm"  action="process" modelAttribute="userDto" method="post">


			<div class="form-group">
				<form:label path="firstName">First name:</form:label>
				<form:input path="firstName" class="form-control" 
					placeholder="Unesite ime" cssErrorClass="errorInput" />

				<form:errors path="firstName" cssClass="classError" />
            
               </div>


			<div class="form-group">
				<form:label path="lastName" >Last name:</form:label>
				<form:input path="lastName" class="form-control"
					placeholder="Unesite prezime" cssErrorClass="errorInput"  />
				<form:errors path="lastName" cssClass="classError" />
				
			</div>





			<div class="form-group">
				<form:label path="username">Username:</form:label>
				<form:input path="username" class="form-control "
					placeholder=" Unesite korisnicko ime" cssErrorClass="errorInput" />
				<form:errors path="username" cssClass="classError" />


			
				</div>

			
			
			<div class="form-group">
			<form:input path="mailDto.email" class="form-control " placeholder=" Unesite vas email"
			cssErrorClass="errorInput"/>
            <form:errors path="mailDto.email" cssClass="classError" />
            
				</div>
           	

			<div class="form-group">
				<form:label for="pass" path="password">Password : </form:label>

				<form:password id="pass" path="password" class="form-control"
					placeholder="Unesite sifru" cssErrorClass="errorInput"/>

				<form:errors path="password" cssClass="classError" /><br>

				
				<label for="che">Show password:</label> <input id="che" align="left"
					type="checkbox" onclick="showPassword()" />

			</div>

				<div class="form-group">
				<form:label path="confirmpassword">Confirm the password:</form:label>
				<form:password id="conf" path="confirmpassword" class="form-control"
					placeholder="potvrdi sifru" cssErrorClass="errorInput"/>
				<form:errors path="confirmpassword" cssClass="classError" /><br>

				
				<label for="che">Show password:</label> <input align="left"
					type="checkbox" onclick="showConfirmPassword()" />
			</div>
<!--            < input type="image" src="/sportiishrana/URLForImages/eye.png" width="15px" height="15px" onclick="showPassword()" />
 -->

			<div class="form-group">
				<form:label path="date">Birth year:</form:label>
				<form:input path="date" type="date"  class="form-control"
					placeholder=" Dan Mesec Godina" cssErrorClass="errorInput" />


				<form:errors path="date" cssClass="classError" />
				
              </div>

		


			<div class="form-group">
				<form:label path="city">City:</form:label>
				<form:select path="city" class="form-control">
					<form:option value="Beograd" />
					<form:option value="Novi Sad" />
					<form:option value="Nis" />
					<form:option value="Uzice" />
					<form:option value="Zajecar" />
					<form:option value="Zrenjanin" />
					<form:option value="Leskovac" />
					<form:option value="Bor" />
					<form:option value="Jagodina" />
					<form:option value="Paracin" />
					<form:option value="ostalo" />
				</form:select>
			</div>


			<div class=" form-group has-error   form-check-inline">
       
				<label for="ma" class="form-check-label">Male </label>
				<form:radiobutton id="ma" path="gender" value="male"
					class="form-check-input" /> &ensp;
					<label for="fe" class="form-check-label">Female </label>
				<form:radiobutton id="ma" path="gender" value="female"
					class="form-check-input"  />&emsp; 
					
					 <form:errors path="gender" cssClass="classError"/>
			</div>
			
						  
			<div class="form-group form-check">
				<label for="ch" class="form-check-label" >I confirm that all entered data are correct :</label>
				<form:checkbox path="check"    /><br>
				<form:errors path="check" cssClass="classError"/>
				 
				

			</div>


			<br>
			<br>
			<input  type="submit" value="confirm" class="btn btn-success btn-lg" >

		</form:form>

	</div>

                                  

	<div id="footerRegistration">


		<p>Sve na jednom mestu potpuno besplatno!| Treninzi | Recepti | </p>





	</div>















</body>
</html>