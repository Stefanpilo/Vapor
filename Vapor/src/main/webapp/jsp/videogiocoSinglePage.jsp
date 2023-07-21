<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
	<head>
	<meta charset="ISO-8859-1">
		<title>Videogioco Single Page</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
	
		<div class="videogiocoInfoContainer" style="display:flex">
			<img id="immagine" alt="immagine" style="width:500px; height:300px">
			<div class="textContainer">
				<h1 id="titolo">Titolo</h1>
				<p id="categoria">Categoria</p>
				<h3 id="descrizione">Descrizione</h3>
			</div>			
		</div>
		<span id="prezzo">Prezzo</span>
		<span id="sconto">Sconto</span>
		<div style="display: inline; position: absolute; right: 0">
			<button>Update gioco</button>
			<button>Elimina gioco</button>
		</div>
		
		
		<script src="/Vapor/scripts/videogiocoSinglePage_script.js"></script>
	</body>
</html>