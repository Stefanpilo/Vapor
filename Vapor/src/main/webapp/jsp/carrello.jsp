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
			
			img {
				width: 250px;
			}
			
			.proceed {
				margin-top: 30px;
				display: inline-block;
				width: auto;
				font-size: 20px;
			}
			
			.changeQuantity {
				margin: 0 20px 0 20px;
				background-color: #FFEDF0;
				border-radius: 8px;
				font-size: 18px;
				line-height: 23px;
				width: 40px;
			}
			
			.titolo, .prezzo {
				font-size: 18px;
				margin: 0 20px 0 20px;
				width: 20%;
			}
			
		</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
		<div style="padding: 10px 7% 10px 7%">
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
				 			float prezzo = prodottiAL.get(i).getProduct().getPrezzo()/100*(100 - prodottiAL.get(i).getProduct().getSconto());
				 		%>
				 		<tr id=<%= i %> class="tableRow">
				 			<th><img src=<%= prodottiAL.get(i).getProduct().getImmagine() %> alt="immagine"></th>
				 			<th class="titolo"><%= prodottiAL.get(i).getProduct().getTitolo() %></th>
				 			<th><input class="changeQuantity" type="number" name="quantity" value="<%= prodottiAL.get(i).getQuantity() %>" placeholder="quantità" data-idvideogioco=<%= prodottiAL.get(i).getProduct().getID() %>></input></th>
				 			<th class="prezzo" data-prezzo=<%= prezzo %>><%= String.format("%.2f", (prezzo * prodottiAL.get(i).getQuantity()) ) %></th>
				 			<td><button class="deleteRow_button" data-idvideogioco=<%= prodottiAL.get(i).getProduct().getID() %>>RIMUOVI</button></td>
				 		</tr>
				 		<%
				 		}
				 		%>
				 	</tbody>
				 </table>
			 </div>
				 <%if ((session.getAttribute("cliente") == null) && (session.getAttribute("username") == null)) {%>
				 	<a class="proceed" href="/Vapor/jsp/login.jsp">Accedi per procedere al checkout</a>
				 <%
				 }
				 else if ( session.getAttribute("cliente") != null ) {%>
				 	<a class="proceed" href="/Vapor/jsp/checkout.jsp">Checkout</a>
				 <%
				 }
				 else {%>
				 	<span class="proceed">L'admin non può effettuare acquisti</span>
				 <%} %>
		 <%
		 }
		 %>
		
		</div>
		<script src="/Vapor/scripts/carrelloPage_script.js"></script>
		
		
		
	<%@include file="footer.jsp" %>
	</body>
</html>