<!DOCTYPE html>
<html lang="it">
	<head>
		<link rel="stylesheet" type="text/css" href="styles/header.css">
	</head>
	<body>    
		<%@page language="java" %>
		<%@page import="javax.servlet.http.HttpSession" %>
		<%
			session = request.getSession(false);
			
		%>
		
		<header>
			<div id="header-wrapper">
				<img id="logo" src="" alt="logo">
				<span>CIAO</span>
				<button>RandomButton</button>
				<a id="carrello_button" href="jsp/carrello.jsp">Carrello</a>
				<%
					if (session.getAttribute("logged") == null) { %>
						<a id="register_button" href="jsp/registration.jsp">Register</a>
						<a id="login_button">Login</a>
				<% 	} else { %>
						<a id="account_button">Account</a>
				<% 	}%>
			</div>
		</header>		
	</body>
</html>