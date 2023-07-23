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
				<form action="/submit_payment" method="post">
				
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
            		<input type="text" id="scadenzaCarta" name="scadenzaCarta" required>
        		    <br>
        		    
        		    
        		    
         		   <input type="submit" value="Salva Metodo di Pagamento">
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
                        <td><%= mp.getUsernameCliente() %></td>
                        <td><%= mp.getExpDate()%></td>
                        <td>
                            <form action="/removePaymentMethod" method="post">
                                <input type="hidden" name="cardNumber" value="<%= mp.getNumeroCarta() %>">
                                <input type="submit" value="Rimuovi">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    	</div>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>