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
		String displayType[] = {"block", "none"};
		int displayTypeIndex = 1;
		if (session.getAttribute("carrello") == null || ((Carrello) session.getAttribute("carrello")).isEmpty())
			displayTypeIndex = 0;
		
		%><h2 id="cartEmptyMessage" style="display: <%= displayType[displayTypeIndex] %>">Il carrello è vuoto</h2>
		<%if(displayTypeIndex == 1) {%>
			<div id="tableWrapper" style="display: <%= displayType[(displayTypeIndex + 1) % 2] %>">
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
				 			<th><img src=<%= prodottiAL.get(i).getProduct().getImmagine() %> alt="immagine"></th>
				 			<th><%= prodottiAL.get(i).getProduct().getTitolo() %></th>
				 			<th><input class="changeQuantity" type="number" name="quantity" value="<%= prodottiAL.get(i).getQuantity() %>" placeholder="quantità" data-idvideogioco=<%= prodottiAL.get(i).getProduct().getID() %>></input></th>
				 			<th><%= String.format("%.2f", prodottiAL.get(i).getProduct().getPrezzo()/100*(100 - prodottiAL.get(i).getProduct().getSconto())) %></th>
				 			<td><button class="deleteRow_button" data-idvideogioco=<%= prodottiAL.get(i).getProduct().getID() %>>RIMUOVI</button></td>
				 		</tr>
				 		<%
				 		}
				 		%>
				 	</tbody>
				 </table>
				 <%if ((session.getAttribute("cliente") == null) && (session.getAttribute("username") == null)) {%>
				 	<a href="/Vapor/jsp/login.jsp">Accedi per procedere al checkout</a>
				 <%
				 }
				 else if ( session.getAttribute("cliente") != null ) {%>
				 	<a href="/Vapor/jsp/checkout.jsp">Checkout</a>
				 <%
				 }
				 else {%>
				 	<span>L'admin non può effettuare acquisti</span>
				 <%} %>
			 </div>
		 <%
		 }
		 %>
		
		<script src="/Vapor/scripts/carrelloPage_script.js"></script>
		
		
		
	<%@include file="footer.jsp" %>
	</body>
</html>