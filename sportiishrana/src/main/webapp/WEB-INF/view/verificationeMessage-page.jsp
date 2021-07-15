<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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


<link rel="stylesheet" type="text/css" href="/sportiishrana/URLForCSS/verificationMessagePage.css">

</head>
<body>


<div id="headerVerification">
		<h1>
			<a href="/sportiishrana/headerFrontPageLink"> Sport & ishrana <span>Sve
					na jednom mestu</span>
			</a>
		</h1>

		<ul id="navbar">

			<li><a href="/sportiishrana/home">Home</a></li>



		</ul>
	</div>



 
  <div id="displayMessage"  align="center" class="container">
			<c:if  test="${param.confirmregistration !=null }">


				<p>Idite na Vas  mail koji ste uneli prilikom registracije gde ce vam u poruci stici link za
					verifikaciju vaseg naloga</p>


			</c:if>
		</div>

  
  

</body>
</html>