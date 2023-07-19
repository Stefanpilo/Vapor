<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="ISO-8859-1">
		<title>Account</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
		<%
			if(session.getAttribute("username").equals("admin")){
				%>			
				<div style="display:flex">
					<button id="aggiungiVideogioco_button" style="margin:auto">
						Aggiungi videogioco
					</button>
					<button style="margin:auto">
						Visualizza tutti gli ordini
					</button>
					<button style="margin: auto">
						Visualizza ordini per data
					</button>
					<button style="margin: auto">
						Visualizza ordini per cliente
					</button>
				</div>
							
				<form id="aggiungiVideogioco_form" action="/Vapor/AdminServlet" method="post" style="display:none">
					<input type="text" id="immagine" name="immagine" placeholder="immagine" required><br>
					<input type="text" id="titolo" name="titolo" placeholder="titolo" required><br>
					<input type="number" id="prezzo" name="prezzo" placeholder="0" required><br>
					<input type="number" id="sconto" name="sconto" placeholder="0" required><br>
					<input type="text" id="descrizione" name="descrizione" placeholder="descrizione" required><br>
					<select id="categoria" name="categoria">
					<%
						Categorie categories = new Categorie();
						for (int i = 0; i < categories.getCategoryList().size(); i++) {
							String categoria = categories.getCategoryAtIndex(i);
					%>		<option value=<%= categoria %>><%= categoria %></option>
					<%	}
					%>
					</select>
					<input type="button" id="submit_button" name="submit" value="submit">
				</form>
			
				<p id="messageViewer" style="display:none"></p>
			
			
				<script src="/Vapor/scripts/accountPageAdmin_script.js"></script>
				<%
			}
		else {
			%>
			
			
			
			<%
		}
		%>
		</body>
</html>