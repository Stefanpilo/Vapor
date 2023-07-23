<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Account</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
	
		<%
			session = request.getSession(false);
		
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
					<input type="number" id="prezzo" name="prezzo" placeholder="prezzo" required><br>
					<input type="number" id="sconto" name="sconto" placeholder="sconto" required><br>
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
					<input id="startDate" type="date"></input>
					<input id="endDate" type="date"></input>
					<input id="searchByCliente" type="text" placeholder="Inserisci username"></input>
					<button id="ordiniByDataSubmit_button" value="submit">Cerca</button>
					<button id="ordiniByUsernameSubmit_button" value="submit">Cerca</button>
					<table border=1>
						<thead>
							<tr>
								<th>Prezzo Totale</th>
								<th>Metodo Pagamento</th>
								<th>Data</th>
								<th>Username Cliente</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<p class="messageViewer"></p>
					<table id="compostoDa_table" border=1>
						<thead>
							<tr>
								<th>Titolo videogioco</th>
								<th>Prezzo videogioco</th>
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
			<div style ="display:flex" class="infocliente-button-container">
				<form action="/submit_cliente" method="post">


       			 <label for="username">Username:</label>
       			 <input type="text" id="username" name="username" value="<%= session.getAttribute("username") %>" disabled>
        		 

		        <label for="password">Password:</label>
        		<input type="password" id="password" name="password" value="<%= session.getAttribute("Cliente_password") %>">
        		<button type="button" class="password-toggle-btn" onclick="togglePasswordVisibility()">Mostra/Nascondi</button>
        		
    
        		<label for="email">Email:</label>
        		<input type="email" id="email" name="email" value="<%= session.getAttribute("Cliente_email") %>">
        		<br>


        
        		<label for="nome">Nome:</label>
        		<input type="text" id="nome" name="nome" value="<%= session.getAttribute("Cliente_nome") %>">
        	
        
        		<label for="cognome">Cognome:</label>
        		<input type="text" id="cognome" name="cognome" value="<%= session.getAttribute("Cliente_cognome") %>">
        		
    
        		<label for="codiceFiscale">Codice Fiscale:</label>
        		<input type="text" id="codiceFiscale" name="codiceFiscale" value="<%= session.getAttribute("Cliente_codiceFiscale") %>">
        		<br>
    
       			 <input type="submit" value="Modifica Info">
    			</form>
    		</div>
    		
    		
    		<div style ="display:flex" class="paymentmethod-button-container">	
				<form action="/submit_payment" method="post">
				
            		<label for="numeroCarta">Numero Carta:</label>
            		<input type="text" id="numeroCarta" name="numeroCarta" required>
            		<br>
            		
            		<label for="cvvCarta">CVV Carta:</label>
        		    <input type="text" id="cvvCarta" name="cvvCarta" required>
        		    <br>
        		    
        		    <select id="circuitoCarta" name="circuitoCarta" required>
        		   		<option value="Visa">Visa</option>
        			    <option value="Mastercard">Mastercard</option>
                		<option value="American Express">American Express</option>
       			        <option value="Maestro">Maestro</option>
       				 </select>
           			 <br>
        		    
            		
            		<label for="titolareCarta">Titolare Carta:</label>
            		<input type="text" id="titolareCarta" name="titolareCarta" value="<%= session.getAttribute("Cliente_nome") %>" disabled>
            		<br>
            		
        		    <label for="scadenzaCarta">Scadenza Carta:</label>
            		<input type="text" id="scadenzaCarta" name="scadenzaCarta" required>
        		    <br>
        		    
        		    
        		    
         		   <input type="submit" value="Salva Metodo di Pagamento">
        		</form>
				 
				 <script>
				 function togglePasswordVisibility() {
					 const passwordInput = document.getElementById('password');
            		 if (passwordInput.type === 'password') {
            			 passwordInput.type = 'text';
            		 } else {
            			 passwordInput.type = 'password';
            			 }
            		 }
				 </script>
			</div >
			
			 <div style ="display:flex" class="order-button-container">
			 <button type="button" id="visualizzaOrdini_button">Visualizza Ordini</button>
        	
    		</div>
			<%
		}
		%>
		</body>
</html>