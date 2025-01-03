<%@ page import="Storage.Struttura.Struttura" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 03/01/2025
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Riepilogo Strutture</title>
    <link rel="stylesheet" href="css/riepilogoStrutture.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <%
        List<Struttura> strutture = (List<Struttura>) request.getAttribute("listaStrutture");

    %>

    <div class="big-text" id="areaHost"> Area Host</div>

    <div class="sezione-aggiungi">
        <div class="normal-text" id="sezione"> Visualizza le tue strutture </div>
        <div class="aggiungiStruttura">
            <form action="login-servlet" method="post">
                <input type="submit" value="Aggiungi Struttura">
            </form>
        </div>
    </div>

    <div class ="griglia">
        <!-- ci vanno tutti gli alloggi -->
    </div>


    <%@ include file="../WEB-INF/moduli/footer.jsp"%>
</body>
</html>
