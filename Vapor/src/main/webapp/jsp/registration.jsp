<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Registration</title>
		
		<style>
			#registrationForm > * {
				margin-top: 5px;
				background-color: #FFEDF0;
				border-radius: 10px;
				font-size: 20px;
				line-height: 25px;
			}
			
			#submit_button {
				color: #2F1F33;
				border: 1px solid #2F1F33;
				border-radius: 15px;
				background-color: #AC505F;
				padding: 5px 15px 5px 15px;
				text-decoration: none;
				cursor: pointer;
				font-family: Arial;
			}
			
			#submit_button:hover {
				background-color: #AC505F8033;
			}
			
		</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		<div style="padding: 10px 7% 10px 7%">
		<h1>Registrati</h1>
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
		
		
		</div>
		<script src="/Vapor/scripts/LoginFormSubmissionsHandler.js"></script>
		
	<%@include file="footer.jsp" %>
	</body>
</html>