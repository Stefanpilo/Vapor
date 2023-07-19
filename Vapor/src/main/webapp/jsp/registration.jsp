<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		<h1>registrati</h1>
		<form id="registrationForm" action="/Vapor/LoginHandlerServlet" method="post">
			<input type="text" id="username" name="username" placeholder="username" required><br>
			<input type="password" id="password" name="password" placeholder="password" required><br>
			<input type="text" id="nome" name="nome" placeholder="nome" required><br>
			<input type="text" id="cognome" name="cognome" placeholder="cognome" required><br>
			<input type="email" id="email" name="email" placeholder="email" required><br>
			<input type="text" id="codiceFiscale" name="codiceFiscale" placeholder="codiceFiscale" required><br>
			<input type="button" id="submit_button" name="submit" value="submit">
		</form>
		<p id="formSubmitResultMessage" style="display: none"></p>
		
		
		<script src="/Vapor/scripts/LoginFormSubmissionsHandler.js"></script>
	</body>
</html>