<%@ page import="Storage.Prenotazione.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="Storage.Struttura.Struttura" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 06/01/2025
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prenotazioni Struttura</title>
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    stampa
    <%@ include file="../WEB-INF/moduli/header.jsp"%>
    <%
        List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute("listaPrenotazioni");
        Struttura struttura = (Struttura) request.getAttribute("struttura");
    %>

    <% for(int i=0; i<prenotazioni.size(); i++) { %>
        <%= struttura.getNomeStruttura() %>
        <%= prenotazioni.get(i).getCodicePrenotazione()%>

    <% } %>

    <%@ include file="../WEB-INF/moduli/footer.jsp"%>
</body>
</html>
