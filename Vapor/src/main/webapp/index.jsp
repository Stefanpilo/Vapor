<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, model.*" pageEncoding="UTF-8" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
    	Categories category = new Categories();
    	VideogiocoDAO vdao = new VideogiocoDAO();
    	ArrayList<Videogioco> arv = new ArrayList<Videogioco>();
    	
    	for (int i = 0; i < category.getCategoryList().size(); i++) {
    		arv = vdao.executeSelectByCategory(category.getCategoryAtIndex(i));
    		if (arv.isEmpty()) {
    			continue;
    		}
    		%>
    		<h1 style="text-align: center"><%= category.getCategoryAtIndex(i) %></h1>
    		<%
    		//inserire div
    		%><div style="display: flex"><%
    		for(int j = 0; j < arv.size(); j++){
    			%>
    			<div>
    				<span><%= arv.get(j).getTitolo() %></span><br>
    				<img alt =<%= arv.get(j).getImmagine() %>>
    			</div>
    			<%
    			
    		}
    		%>
    		</div>
    		<%
    	}
    %>
    
</body>
</html>