<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<link rel="stylesheet" type="text/css" href="/Vapor/styles/genericStyle.css">
		<link rel="stylesheet" type="text/css" href="/Vapor/styles/header.css">
	</head>
	<body>    
		<%@page language="java" %>
		<%@page import="javax.servlet.http.HttpSession" %>
		<%
			session = request.getSession(false);
			
		%>
		
		<header>
			<div id="header-wrapper">
				<a id="logo" href="/Vapor/index.jsp">
    			<img src="/Vapor/images/Logo.png" alt="logo">
    			</a>
				<a class="autoMarginLeft" href="/Vapor/jsp/catalogo.jsp">Catalogo</a>
				<a id="carrello_button" class="autoMarginLeft" href="/Vapor/jsp/carrello.jsp">Carrello</a>
				<%
					if ((session.getAttribute("username") == null) && (session.getAttribute("cliente") == null)) { %>
						<a id="register_button" href="/Vapor/jsp/registration.jsp">Register</a>
						<a id="login_button" href="/Vapor/jsp/login.jsp">Login</a>
				<% 	} else { %>
						<button id="logout_button" onClick="logOut()">Logout</button>
						<%if ( ((Cliente)session.getAttribute("cliente")) != null ) { %>
							<a id="account_button" href="/Vapor/jsp/accountPage.jsp"><%= ((Cliente)session.getAttribute("cliente")).getUsername() %></a>
						<%}
						else {
						%>
							<a id="account_button" href="/Vapor/jsp/accountPage.jsp">admin</a>
						<%
						}
					}%>
			</div>
			
			<script>
				function logOut() {
					let xhr = new XMLHttpRequest();
					xhr.open('GET', '/Vapor/LoginHandlerServlet?logOut=true', true);
					xhr.onreadystatechange = function() {
						if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
							alert("Logout effettuato con successo!\nSarai redirezionato come guest!");
							location.assign("/Vapor");
						}
					}
					xhr.send();
				}
			</script>
			<script src="/Vapor/scripts/genericScripts.js"></script>
		</header>
	</body>
</html>