<%@ page import="Storage.Prenotazione.Prenotazione" %>
<%@ page import="java.util.List" %>
<%@ page import="Storage.Struttura.Struttura" %>
<%@ page import="Storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="Storage.Alloggio.AlloggioDAO" %>
<%@ page import="Application.GestioneStrutture.gestioneStrutturaFacade" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <link rel="stylesheet" href="Interface/css/prenotazioniStruttura.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>
    <%
        List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute("listaPrenotazioni");
        Struttura struttura = (Struttura) request.getAttribute("struttura");
        /*PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        Struttura struttura = strutturaFacade.returnStruttura(1);
        List<Prenotazione> prenotazioni = new ArrayList<>();
        prenotazioni = strutturaFacade.visualizzaPrenotazioni(struttura);*/
    %>

    <div class="title mid-text">
        Nome della struttura
    </div>

    <div class="nomiCampi normal-small-text">
        <div class="campo"><b>Numero Alloggio</b></div>
        <div class="campo"><b>Check-In</b></div>
        <div class="campo"><b>Check-Out</b></div>
        <div class="campo"><b>Nome Registrato</b></div>
        <div class="campo"><b>Prezzo</b></div>
    </div>

    <hr class="prenotazione">

    <% for(int i=0; i < prenotazioni.size(); i++) {
        Prenotazione prenotazione = prenotazioni.get(i); %>
        <div class="rigaPrenotazione normal-small-text">
            <div class="valore"> 111 </div> <!-- creare una query in prenotazione che mi prende l'alloggio di riferimento -->
            <div class="valore"> 20-12-2009 </div> <!-- modo per ricevere una stringa da un date -->
            <div class="valore"> 20-12-2009 </div>
            <div class="valore"> Domenico Cirillo </div> <!-- creare una query in prenotazione che mi prende il nome di chi ha fatto una prenotazione -->
            <div class="valore"> 345.00 </div> <!-- creare una query che mi permette di ricevere il prezzo di una prenotazione -->
        </div>
        <hr class="prenotazione">
    <% } %>

    <%@ include file="../WEB-INF/moduli/footer.jsp"%>
</body>
</html>
