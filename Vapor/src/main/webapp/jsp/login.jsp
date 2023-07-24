<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it"> 
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		
		<style>
			#loginForm > * {
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
				background-color: #ac505f;
				padding: 5px 15px 5px 15px;
				text-decoration: none;
				cursor: pointer;
				font-family: Arial;
			}
			
			#submit_button:hover {
				background-color: #FFEDF033;
			}
			
		</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		<div style="padding: 10px 7% 10px 7%"">
		<h1>Login</h1>
		<form id="loginForm" action="/Vapor/LoginHandlerServlet" method="get">
			<input type="text" id="username" name="username" placeholder="username" required>
			<input type="password" id="password" name="password" placeholder="password" required><br>
			<input type="button" id="submit_button" name="submit" value="submit" style="text-align: center">
		</form>
		
		<p id="messageViewer" style="display: none"></p>
		
		</div>
		
		<script src="/Vapor/scripts/LoginFormSubmissionsHandler.js"></script>
	<%@include file="footer.jsp" %>
	</body>
</html>