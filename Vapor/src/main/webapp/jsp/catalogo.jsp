<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
	<title>Catalogo</title>
	
	<style>
		.videogiocoContainer {
			cursor: pointer;
		}
		.videogiocoContainer > * {
			pointer-events: none;
		}
	</style>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="./header.jsp" %>
		
		<h1>Catalogo</h1>
		
		<div id="filterContainer">
			<input type="radio" name="categoria" value="nessuna Categoria">
			<label>Nessuna categoria</label>
			<%
			Categorie categorie = new Categorie();
			for(int i = 0; i < categorie.getCategoryList().size(); i++) {%>
				<input type="radio" name="categoria" value="<%= categorie.getCategoryAtIndex(i) %>">
				<label><%= categorie.getCategoryAtIndex(i) %></label>
				<%
			}
				%>
			
		</div>
		
		<div id="catalogoContainer" style="display: flex; flex-wrap: wrap">
			<%
				session = request.getSession(false);
				VideogiocoDAO vdao = new VideogiocoDAO();
				ArrayList<Videogioco> videogiocoAL = vdao.executeSelectAll();
			
				for(int i = 0; i < videogiocoAL.size(); i++) {
					if (!videogiocoAL.get(i).getDisponibile() && (session.getAttribute("cliente") == null || !session.getAttribute("username").toString().contains("admin")) )
						continue;
			%>
			<div class="videogiocoContainer <%= videogiocoAL.get(i).getCategoria() %>" data-IDVideogioco=<%= videogiocoAL.get(i).getID() %>>
				<h2 class="titolo"><%= videogiocoAL.get(i).getTitolo() %></h2>
				<img style="display: block" alt="immagine" src= <%= videogiocoAL.get(i).getImmagine() %>>
				<% if (videogiocoAL.get(i).getDisponibile() && videogiocoAL.get(i).getSconto() > 0) { %>
				<div style="display: flex">
					<div>
						<span class="prezzoDaScontare"><%= videogiocoAL.get(i).getPrezzo() %></span><br>
						<span class="prezzoScontato"><%= videogiocoAL.get(i).getPrezzo()/100*(100-videogiocoAL.get(i).getSconto()) %></span>
					</div>
					<span class="sconto"><%= videogiocoAL.get(i).getSconto() %></span>
				</div>
				<% }
				else {
					if (videogiocoAL.get(i).getDisponibile()) { %>
				<span class="prezzoOriginale"><%= videogiocoAL.get(i).getPrezzo() %></span>
				<%} else { %>
				<span>Non disponibile</span>
				<%	}
				}%>
			</div>
			
			<%
				}
			%>
		</div>
		
		<script src="/Vapor/scripts/catalogoPage_script.js"></script>
		
		
		
	<%@include file="footer.jsp" %>
	</body>
</html>