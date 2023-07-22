<%@ page language="java" contentType="text/html; charset=UTF-8"
import = "java.util.*, model.*" pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="it">
	<head>
	    <meta charset="ISO-8859-1">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Vapor</title>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="jsp/header.jsp" %>
		
		<!-- PROMO -->
	    <img width="100%" alt="QUI VA IL MESSAGGIO PROMO">
	    
	    <!-- CATEGORIES + GAMES -->
	    <%
	    Categorie category = new Categorie();
	    VideogiocoDAO vdao = new VideogiocoDAO();
	    ArrayList<Videogioco> videogiocoAL = new ArrayList<Videogioco>();
	        	
	    for (int i = 0; i < category.getCategoryList().size(); i++) {
	    	videogiocoAL = vdao.executeSelectByCategory(category.getCategoryAtIndex(i));
	        if (videogiocoAL.isEmpty()) {
	        	continue;
	        }
	    %>
	    <h1 style="text-align: center"><%= category.getCategoryAtIndex(i) %></h1>
	    <%
	    //inserire div
	    %><div style="display: flex"><%
	    for(int j = 0; j < videogiocoAL.size(); j++){
	    %>
	    	<div class="gameContainer" data-IDVideogioco=<%=videogiocoAL.get(j).getID()%> style="cursor:pointer">
	    	<span style="pointer-events: none"><%= videogiocoAL.get(j).getTitolo() %></span><br>
	    	<img src = <%= videogiocoAL.get(j).getImmagine() %> style="pointer-events: none">
	    	</div>
	    	<%
	    			
	    }
	    		%>
	    </div>
	    		<%
	    }
	    %>
	    
	    <script src="/Vapor/scripts/homePage_script.js"></script>
	</body>
</html>