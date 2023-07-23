<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
	<title>Checkout</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
		<%
		session = request.getSession(false);
		ArrayList<ProdottoCarrello> prodottoCarrelloAL = ((Carrello)session.getAttribute("carrello")).getProducts();		
		float prezzoTotale = 0;
		int i;
		boolean canPurchase = true;
		
		for (i = 0; i < prodottoCarrelloAL.size(); i++) {
			Videogioco currentVideogioco = prodottoCarrelloAL.get(i).getProduct();
			float prezzoProdotto = currentVideogioco.getPrezzo()/100*(100-currentVideogioco.getSconto());
			prezzoTotale += ( prezzoProdotto * prodottoCarrelloAL.get(i).getQuantity() );
		}
		%>
		<span>Utente: <%= ((Cliente)session.getAttribute("cliente")).getUsername() %></span><br>
		<span>Prezzo totale: <%= String.format("%.2f", prezzoTotale) %></span><br>
		<span>Metodo di pagamento:
			<select id="metodoPagamentoSelezionato">
				<%
				MetodoPagamentoDAO mpdao = new MetodoPagamentoDAO();
				ArrayList<MetodoPagamento> metodoPagamentoAL = mpdao.executeSelectByUsername( ((Cliente)session.getAttribute("cliente")).getUsername() );
				
				for (i = 0; i < metodoPagamentoAL.size(); i++) {
					%><option value="<%= metodoPagamentoAL.get(i).getNumeroCarta() %>"><%= metodoPagamentoAL.get(i).getNumeroCarta()%></option>
				<%
				}
				if (i == 0) {
					canPurchase = false;
					%><option id="noCards">Vai sul tuo profilo per aggiungere nuove carte</option>
					<%
				}
				%>
			</select>
			
		</span>
		<button id="confirmPurchase" data-canPurchase=<%= canPurchase %> data-prezzoTotale="<%= prezzoTotale %>">Conferma ordine</button>
		<span id="purchaseErrorViewer" style="display: none">Devi selezionare un metodo di pagamento</span>
		
		
		
	<%@include file="footer.jsp" %>
	<script src="/Vapor/scripts/checkout_script.js"></script>
	</body>
</html>