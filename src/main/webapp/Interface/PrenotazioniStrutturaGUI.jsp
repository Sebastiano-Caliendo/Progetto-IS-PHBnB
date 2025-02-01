<%@ page import="Storage.Prenotazione.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="Storage.Struttura.Struttura" %>
<%@ page import="Storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="Storage.Alloggio.AlloggioDAO" %>
<%@ page import="Application.GestioneStrutture.GestioneStrutturaFacade" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Storage.Occupa.Occupa" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 06/01/2025
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% if(callByServlet == 1) { %>
    <link rel="stylesheet" href="Interface/css/prenotazioniStruttura.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/prenotazioniStruttura.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
    <% } %>
    <title> Prenotazioni Struttura </title>
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>
    <%
        /* questo hasmap mi consente di ottenere sia le informazioni che ci sono nel bean prenotazione
           di un alloggio e sia le informazioni che ci sono nel bean occupa di un determinato alloggio
           connesso con quella prenotazione */
        HashMap<Prenotazione, Occupa> prenotazioneOccupa = (HashMap<Prenotazione, Occupa>) request.getAttribute("prenotazioneOccupa");
        Struttura struttura = (Struttura) request.getAttribute("struttura");
    %>

    <div class="title mid-text">
        <%= struttura.getNomeStruttura() %>
    </div>

    <div class="nomiCampi normal-small-text">
        <div class="campo"><b>Numero Alloggio</b></div>
        <div class="campo"><b>Check-In</b></div>
        <div class="campo"><b>Check-Out</b></div>
        <div class="campo"><b>Nome Registrato</b></div>
        <div class="campo"><b>Numero Persone</b></div>
        <div class="campo"><b>Prezzo</b></div>
    </div>

    <hr class="prenotazione">

    <% for (Map.Entry<Prenotazione, Occupa> entry : prenotazioneOccupa.entrySet()) {
        Prenotazione prenotazione = entry.getKey();
        Occupa occupa = entry.getValue(); %>
        <div class="rigaPrenotazione small-text">
            <div class="valore"> <%= occupa.getAlloggio().getNumeroAlloggio() %> </div>
            <div class="valore"> <%= prenotazione.getCheckIn() %> </div>
            <div class="valore"> <%= prenotazione.getCheckOut() %></div>
            <div class="valore"> <%= prenotazione.getUtente().getNome() + " " + prenotazione.getUtente().getCognome() %> </div>
            <div class="valore"> <%= prenotazione.getNumeroPersone() %></div>
            <div class="valore"> <%= occupa.getCostoPrenotazione() %> </div>
        </div>
        <hr class="prenotazione">
    <% } %>

    <%@ include file="../WEB-INF/moduli/footer.jsp"%>
</body>
</html>
