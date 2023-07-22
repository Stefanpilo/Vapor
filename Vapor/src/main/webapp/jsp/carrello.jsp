<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
		<meta charset="UTF-8">
		<title>Carrello</title>
		
		<style>
			table {
				border-collapse: collapse;
			}
			th:not(:last-child), td:not(:last-child) {
				border: 1px solid;
			}
		</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
		<h1> Dettagli Carrello </h1>
		<%
		session = request.getSession(false);
		if (session.getAttribute("carrello") == null || ((Carrello) session.getAttribute("carrello")).isEmpty()) {
			 %><h2>Il carrello è vuoto</h2>
			 <%
		}
		else {
		%>
		 <table>
		 	<thead>
		 		<tr>
		 			<th>Immagine</th>
		 			<th>Titolo</th>
		 			<th>Quantità</th>
		 			<th>Prezzo</th>
		 			<th></th>
		 		</tr>
		 	</thead>
		 	<tbody>
		 		<%
		 		ArrayList<ProdottoCarrello> prodottiAL = ((Carrello)session.getAttribute("carrello")).getProducts();
		 		for (int i = 0; i < prodottiAL.size(); i++) {
		 		%>
		 		<tr>
		 			<th><%= prodottiAL.get(i).getProduct().getImmagine() %></th>
		 			<th><%= prodottiAL.get(i).getProduct().getTitolo() %></th>
		 			<th><%= prodottiAL.get(i).getQuantity() %></th>
		 			<th><%= prodottiAL.get(i).getProduct().getPrezzo()/100*(prodottiAL.get(i).getProduct().getSconto()) %></th>
		 			<td><button class="deleteRow_button" data-productIndex=<%= i %>>RIMUOVI</button></td>
		 		</tr>
		 		<%
		 		}
		 		%>
		 	</tbody>
		 </table>
		 <%
		}
		%>
		
		<script src="/Vapor/scripts/carrelloPage_script.js"></script>
	</body>
</html>