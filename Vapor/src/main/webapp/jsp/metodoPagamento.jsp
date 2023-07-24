<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Metodi di Pagamento</title>
    
    <style>
    	input, select {
    		margin-top: 5px;
			background-color: #FFEDF0;
			border-radius: 10px;
			font-size: 20px;
			line-height: 25px;
			padding-left: 10px;
    	}
    	
    	#submit_button, .rimuovi_button {
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
    	
    	#submit_button:hover, .rimuovi_button:hover {
    		background-color: #AC505F80;
    	}
    </style>
</head>
<body>

   	<%@include file="./header.jsp" %>

    <div class="content" style="padding: 10px 7% 10px 7%">
        <h1>Metodi di Pagamento</h1>
       
        <div style ="display:flex" class="paymentmethod-button-container">	
				<form id="form" action="/Vapor/ClienteServlet" method="post" data-username="<%= ((Cliente)session.getAttribute("cliente")).getUsername() %>">
				
            		<label for="numeroCarta">Numero Carta:</label>
            		<input type="text" id="numeroCarta" name="numeroCarta" required>
            		<br>
            		
            		<label for="cvvCarta">CVV Carta:</label>
        		    <input type="text" id="cvvCarta" name="cvvCarta" required>
        		    <br>
        		    
        		    
        		    <label for="circuitoCarta">Circuito Carta:</label>
        		    <select id="circuitoCarta" name="circuitoCarta" required>
        		   		<option value="Visa">Visa</option>
        			    <option value="Mastercard">Mastercard</option>
                		<option value="American Express">American Express</option>
       			        <option value="Maestro">Maestro</option>
       				 </select>
           			 <br>
           			 
           			 
        		    <label for="scadenzaCarta">Scadenza Carta:</label>
            		<input type="date" id="scadenzaCarta" name="scadenzaCarta" required>
        		    <br>
        		    
        		    
        		    
         		   <input id="submit_button" type="button" value="Salva Metodo di Pagamento">
        		</form>

        <table border="1">
            <thead>
                <tr>
                    <th>Numero Carta</th>
                    <th>CVV Carta</th>
                    <th>Circuito Carta</th>
                    <th>Data Scadenza</th>
                    <th>Azione</th>
                </tr>
            </thead>
            <tbody>
            
            <% 
            MetodoPagamentoDAO metodoPagamento =  new MetodoPagamentoDAO(); 
            ArrayList<MetodoPagamento> metodoPagamentoList = metodoPagamento.executeSelectByUsername(((Cliente)session.getAttribute("cliente")).getUsername());
            
            %>
            
               <% for (MetodoPagamento mp : metodoPagamentoList) { %>
                    <tr>
                        <td><%= mp.getNumeroCarta() %></td>
                        <td><%= mp.getCvv() %></td>
                        <td><%= mp.getCircuito() %></td>
                        <td><%= mp.getExpDate()%></td>
                        <td>
                        	<input class="rimuovi_button" type="submit" value="Rimuovi" data-numeroCarta="<%= mp.getNumeroCarta() %>">
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    	</div>
    </div>

    <%@include file="footer.jsp" %>
    <script src="/Vapor/scripts/metodoPagamento_script.js"></script>
</body>
</html>