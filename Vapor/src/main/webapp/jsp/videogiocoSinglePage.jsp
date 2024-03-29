<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
	<head>
	<meta charset="UTF-8">
		<title>Videogioco Single Page</title>
		
		<style>
			#titolo {
				font-size: 60px;
				line-height: 70px;
				margin: 0;
			}
			
			#categoria {
				font-size: 16px;
				margin: 0;
			}
			
			#descrizione {
				font-size: 25px;
			}
			
			#prezzoContainer {
				font-size: 30px;
			}
			
			#rimuoviDalCatalogo_button {
				margin-bottom: 5px;
			}
			
			input {
				margin-top: 5px;
				background-color: #FFEDF0;
				border-radius: 8px;
				font-size: 18px;
				line-height: 23px;
			}
		</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
	
		<%
		boolean isAdmin = false;
		if ((session.getAttribute("username") != null) && ( ((String)session.getAttribute("username")).contains("admin") ))
			isAdmin = true;
		%>
		<div style="padding: 10px 7% 10px 7%"">
		<div id="videogiocoInfoContainer" data-isAdmin=<%= isAdmin %> style="display:flex">
			<img id="immagine" alt="immagine" style="width:500px; height:300px; margin-right: 10%">
			<div class="textContainer">
				<h1 id="titolo">Titolo</h1>
				<p id="categoria">Categoria</p>
				<h3 id="descrizione">Descrizione</h3>
			</div>			
		</div>
		<div>
			<div id="prezzoContainer">
				<span id="scontoSpan"></span>
				<span id="prezzoSpan"></span>
			</div>
			<button id="aggiungiAlCarrello">Aggiungi al carrello</button>
			<span id="aggiuntaAlCarrelloResult"></span>
				<div id="adminCommands" style="display: none; position: absolute; right: 0">
					<button id="rimuoviDalCatalogo_button" data-disponibile="true">Rimuovi dal catalogo</button>
					<button id="aggiungiAlCatalogo_button" style="display:none">Aggiungi al catalogo</button><br>
					<div iD="updateCommandsContainer">
						<button id="updatePrezzo">Aggiorna prezzo originale/sconto</button>
						<input id="prezzoInput" type="number" style="width: 50px"></input>
						<input id="scontoInput" type="number" style="width: 50px"></input>
					</div>
				</div>	
		</div>
		</div>
		
		<script src="/Vapor/scripts/videogiocoSinglePage_script.js"></script>
		
	<%@include file="footer.jsp" %>	
	</body>
</html>