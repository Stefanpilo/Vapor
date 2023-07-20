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
					<button id="visualizzaOrdini_button" style="margin:auto">
						Visualizza tutti gli ordini
					</button>
					<button id="visualizzaOrdiniPerData_button" style="margin: auto">
						Visualizza ordini per data
					</button>
					<button id="visualizzaOrdiniPerCliente_button" style="margin: auto">
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
				
				<div id="ordiniContainer" style="display: none">
					<input id="searchByDate" type="date"></input>
					<input id="searchByCliente" type="text" placeholder="Inserisci username"></input>
					<button id="ordiniByDataSubmit_button" value="submit">Cerca</button>
					<button id="ordiniByUsernameSubmit_button" value="submit">Cerca</button>
					<p>CIAO</p>
					<table border=1>
						<thead>
							<tr>
								<th>ID</th>
								<th>Prezzo Totale</th>
								<th>Metodo Pagamento</th>
								<th>Data</th>
								<th>Username Cliente</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
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