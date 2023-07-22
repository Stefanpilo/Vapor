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
		
		for (i = 0; i < prodottoCarrelloAL.size(); i++) {
			Videogioco currentVideogioco = prodottoCarrelloAL.get(i).getProduct();
			float prezzoProdotto = currentVideogioco.getPrezzo()/100*(100-currentVideogioco.getSconto());
			prezzoTotale += ( prezzoProdotto * prodottoCarrelloAL.get(i).getQuantity() );
		}
		%>
		<span>Utente: <%= session.getAttribute("username") %></span><br>
		<span>Prezzo totale: <%= String.format("%.2f", prezzoTotale) %></span><br>
		<span>Metodo di pagamento:
			<select>
				<%
				MetodoPagamentoDAO mpdao = new MetodoPagamentoDAO();
				ArrayList<MetodoPagamento> metodoPagamentoAL = mpdao.executeSelectByUsername((String)session.getAttribute("username"));
				
				for (i = 0; i < metodoPagamentoAL.size(); i++) {
					%><option value="<%= metodoPagamentoAL.get(i).getNumeroCarta() %>"><%= metodoPagamentoAL.get(i).getNumeroCarta()%></option>
				<%
				}
				if (i == 0) {
					%><option id="noCards" value="">Vai sul tuo profilo per aggiungere nuove carte</option>
					<%
				}
				%>
				<button>Conferma ordine</button>
			</select>
		</span>
		
		
		
	<%@include file="footer.jsp" %>
	</body>
</html>