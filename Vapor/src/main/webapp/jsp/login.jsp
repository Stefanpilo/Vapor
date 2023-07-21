<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it"> 
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		<h1>Login</h1>
		<form id="loginForm" action="/Vapor/LoginHandlerServlet" method="get">
			<input type="text" id="username" name="username" placeholder="username" required>
			<input type="password" id="password" name="password" placeholder="password" required><br>
			<input type="button" id="submit_button" name="submit" value="submit" style="text-align: center">
		</form>
		
		<p id="messageViewer" style="display: none"></p>
		
		<script src="/Vapor/scripts/LoginFormSubmissionsHandler.js"></script>
	</body>
</html>