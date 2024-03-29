<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Account</title>
		
		<style>
			input, select {
				margin-top: 5px;
				background-color: #FFEDF0;
				border-radius: 10px;
				font-size: 20px;
				line-height: 25px;
				padding-left: 10px;
			}
			
			label {
				font-size: 20px;
			}
			
			.button {
				color: #2F1F33;
				border: 1px solid #2F1F33;
				border-radius: 15px;
				background-color: #AC505F;
				padding: 5px 15px 5px 15px;
				text-decoration: none;
				cursor: pointer;
				font-family: Arial;
				font-size: 20px;
			}
			
			.button:hover {
				background-color: #AC505F80;
			}
			
			.pagamento_button {
				margin-top: 5px;
				margin-bottom: 5px;
			}
		</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
	<div class="content" style="padding: 10px 7% 10px 7%">
	
		<%
			session = request.getSession(false);
			Cliente cliente = (Cliente)session.getAttribute("cliente");
		
			if( (session.getAttribute("username") != null) && (session.getAttribute("username").equals("admin"))){
				%>			
				<div style="display:flex; margin-bottom: 10px">
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
					<input class="button" type="button" id="submit_button" name="submit" value="submit">
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
				<form id="form" action="/Vapor/ClienteServlet" method="post">

				<div>
       			 <label for="username">Username:</label>
       			 <input type="text" id="username" name="username" value="<%= cliente.getUsername() %>" disabled>
        		</div>
        		 
				<div>
		        <label for="password">Password:</label>
        		<input type="password" id="password" name="password" placeholder="<%= cliente.getPassword() %>">
        		<button type="button" class="password-toggle-btn" onclick="togglePasswordVisibility()">Mostra/Nascondi</button>
        		</div>
        		
    			<div>
        		<label for="email">Email:</label>
        		<input type="email" id="email" name="email" placeholder="<%= cliente.getEmail() %>">
        		</div>


        		<div>
        		<label for="nome">Nome:</label>
        		<input type="text" id="nome" name="nome" placeholder="<%= cliente.getNome() %>">
        		</div>
        		
        		<div>
        		<label for="cognome">Cognome:</label>
        		<input type="text" id="cognome" name="cognome" placeholder="<%= cliente.getCognome() %>">
        		</div>
        		
        		
    			<div>
        		<label for="codiceFiscale">Codice Fiscale:</label>
        		<input type="text" id="codiceFiscale" name="codiceFiscale" placeholder="<%= cliente.getCodiceFiscale() %>">
        		</div>
    
       			 <input id="submit_button" class="button" type="button" value="Modifica Info">
       			 <span id="submitSuccess" style="display: none"></span>
    			</form>
    		</div>
    		
    		
    		<div style ="display:flex" class="paymentmethod-button">
			 <button class="pagamento_button" type="button" id="paymentmethodhref_button">Metodi di pagamento</button>	
			</div>
				 
				 <script>
				 
				 document.getElementById('paymentmethodhref_button').addEventListener('click', function () {
				        window.location.href = 'metodoPagamento.jsp';
				    });
				 
				 function togglePasswordVisibility() {
					 const passwordInput = document.getElementById('password');
            		 if (passwordInput.type === 'password') {
            			 passwordInput.type = 'text';
            		 } else {
            			 passwordInput.type = 'password';
            			 }
            		 }
				 </script>
		
			<div>
			 <button style="display: inline-block" type="button" id="visualizzaOrdini_button" data-username="<%= ((Cliente)session.getAttribute("cliente")).getUsername() %>">Visualizza Ordini</button>
			 </div>
			 <div style ="display:flex" class="order-button-container">
        	
        	<div id="ordiniContainer">
        	 <table style='display:none' border=1>
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
					<table style='display:none' id="compostoDa_table" border=1>
						<thead>
							<tr>
								<th>Titolo videogioco</th>
								<th>Prezzo videogioco</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<p></p>
				</div>
        	
    		</div>
    		<script src="/Vapor/scripts/accountPageCliente_script.js"></script>
			<%
		}
		%>
		</div>
		</body>
</html>