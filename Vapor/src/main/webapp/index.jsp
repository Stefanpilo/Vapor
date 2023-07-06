<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, model.*" pageEncoding="UTF-8" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
	<%@include file="/WEB-INF/header.jsp" %>
    <h1 style="color: red">Ciao sono il primo h1 del sito!!!</h1>
    <%
    Prova c = new Prova();
        c.print();
    %>
    
</body>
</html>