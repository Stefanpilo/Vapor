<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<% Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
if (carrello == null) {
    carrello = new Carrello();
    request.getSession().setAttribute("carrello", carrello);  
}
%>

<!DOCTYPE html>
<html lang="it">
<head>
		<meta charset="UTF-8">
		<title>Carrello</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
		 <h1> Dettagli Carrello </h1>

</body>
</html>