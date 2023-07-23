<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Metodi di Pagamento</title>
</head>
<body>

   	<%@include file="./header.jsp" %>

    <div class="content">
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