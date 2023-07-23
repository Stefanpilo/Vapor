<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
	<head>
	<meta charset="UTF-8">
		<title>Videogioco Single Page</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
	
		<%
		boolean isAdmin = false;
		if ((session.getAttribute("username") != null) && ( ((String)session.getAttribute("username")).contains("admin") ))
			isAdmin = true;
		%>
		<div id="videogiocoInfoContainer" data-isAdmin=<%= isAdmin %> style="display:flex">
			<img id="immagine" alt="immagine" style="width:500px; height:300px">
			<div class="textContainer">
				<h1 id="titolo">Titolo</h1>
				<p id="categoria">Categoria</p>
				<h3 id="descrizione">Descrizione</h3>
			</div>			
		</div>
		<div>
			<div>
				<span id="sconto"></span>
				<span id="prezzo"></span>
			</div>
			<button id="aggiungiAlCarrello">Aggiungi al carrello</button>
			<span id="aggiuntaAlCarrelloResult"></span>
				<div id="adminCommands" style="display: none; position: absolute; right: 0">
					<button>Update gioco</button>
					<button id="rimuoviDalCatalogo_button" data-disponibile="true">Rimuovi dal catalogo</button>
					<button id="aggiungiAlCatalogo_button" style="display:none">Aggiungi al catalogo</button>
				</div>	
		</div>
		
		<script src="/Vapor/scripts/videogiocoSinglePage_script.js"></script>
		
	<%@include file="footer.jsp" %>	
	</body>
</html>