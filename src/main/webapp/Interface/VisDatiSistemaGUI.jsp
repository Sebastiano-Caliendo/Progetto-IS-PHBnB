<%@ page import="Storage.Alloggio.AlloggioDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Storage.Recensione.RecensioneDAO" %>
<%@ page import="Storage.Recensione.Recensione" %>
<%@ page import="Storage.Host.HostDAO" %>
<%@ page import="Storage.Host.Host" %>
<%@ page import="Storage.Prenotazione.PrenotazioneDAO" %>
<%@ page import="Storage.Prenotazione.Prenotazione" %>
<%@ page import="Storage.Struttura.StrutturaDAO" %>
<%@ page import="Storage.Struttura.Struttura" %>
<%@ page import="Storage.Utente.UtenteDAO" %>
<%@ page import="Storage.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: NicoTanc
  Date: 09/01/2025
  Time: 16:47
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
    <title>Visualizza Dati Sistema</title>
</head>
<body>
    <%@ include file ="../WEB-INF/moduli/header.jsp" %>

<p>Dati del sistema</p>


    <h1 style = "margin-right: auto; margin-left: auto;">Users</h1>
<table style="margin-left: auto; margin-right: auto;">
<tr>
    <th>Email</th>
    <th>Nome</th>
    <th>Cognome</th>
    <th>Password</th>
    <th>Città</th>
    <th>Numero Civico</th>
    <th>Via</th>
    <th>Data di Nascita</th>
    <th>Recapito Telefonico</th>
</tr>
    <%
        UtenteDAO utenteDAO = new UtenteDAO();
        List<Utente> utenti = utenteDAO.doRetrieveAll();

        for(Utente r: utenti){
    %>
    <tr>
        <td><%=r.getEmail()%></td>
        <td><%=r.getNome()%></td>
        <td><%=r.getCognome()%></td>
        <td><%=r.getPassword()%></td>
        <td><%=r.getCitta()%></td>
        <td><%=r.getNumeroCivico()%></td>
        <td><%=r.getVia()%></td>
        <td><%=r.getDataNascita()%></td>
        <td><%=r.getRecapitoTelefonico()%></td>
    </tr>
    <%}%>
</table>
<br>
<br>
    <h1 style = "margin-right: auto; margin-left: auto;">Alloggi</h1>
<table style = "margin-right: auto; margin-left: auto;">
    <tr>
        <th>Numero Alloggio</th>
        <th>ID Struttura</th>
        <th>Prezzo notte</th>
        <th>Posti Letto</th>
        <th>Tipo Alloggio</th>
        <th>Descrizione</th>
    </tr>
    <%
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        List<Alloggio> alloggi = alloggioDAO.doRetrieveAll();

        for(Alloggio a: alloggi){
    %>
    <tr>
        <td><%=a.getNumeroAlloggio()%></td>
        <td><%=a.getFkStruttura()%></td>
        <td><%=a.getPrezzoNotte()%></td>
        <td><%=a.getPostiletto()%></td>
        <td><%=a.getTipoAlloggio()%></td>
        <td><%=a.getDescrizione()%></td>
    </tr>
    <%}%>
</table>
<br>
<br>
    <h1 style = "margin-right: auto; margin-left: auto;">Strutture</h1>
    <table style = "margin-right: auto; margin-left: auto;">
    <tr>
        <th>Email Host</th>
        <th>Nome Struttura</th>
        <th>Via</th>
        <th>Numero Civico</th>
        <th>Citta'</th>
        <th>Numero Alloggi</th>
        <th>Descrizione</th>
    </tr>
    <%
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        List<Struttura> strutture = strutturaDAO.doRetrieveAll();

        for(Struttura s: strutture){
    %>
    <tr>
        <td><%=s.getFkHost()%></td>
        <td><%=s.getNomeStruttura()%></td>
        <td><%=s.getVia()%></td>
        <td><%=s.getNumCivico()%></td>
        <td><%=s.getCitta()%></td>
        <td><%=s.getNumAlloggi()%></td>
        <td><%=s.getDescrizione()%></td>
    </tr>
    <%}%>
</table>
    <br>
    <br>
    <h1 style = "margin-right: auto; margin-left: auto;">Recensioni</h1>
    <table style = "margin-right: auto; margin-left: auto;">
        <tr>
            <th>Email Utente</th>
            <th>Descrizione</th>
            <th>Voto Recensione</th>
            <th>Data Recensione</th>
            <th>Codice Prenotazione</th>
            <th>Numero Alloggio</th>
            <th>Codice Struttura</th>
        </tr>
        <%
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            List<Recensione> recensioni = recensioneDAO.doRetrieveAll();

            for(Recensione r: recensioni){
        %>
        <tr>
            <td><%=r.getEmailRecensore()%></td>
            <td><%=r.getDescrizione()%></td>
            <td><%=r.getVotoRecensione()%></td>
            <td><%=r.getDataRecensione()%></td>
            <td><%=r.getCodicePrenotazione()%></td>
            <td><%=r.getNumeroAlloggio()%></td>
            <td><%=r.getIdStrutturaAlloggio()%></td>
        </tr>
        <%}%>
    </table>
<br>
<br>
    <h1 style = "margin-right: auto; margin-left: auto;">Hosts</h1>
    <table style = "margin-left: auto; margin-right: auto;">
    <tr>
        <th>Email</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Password</th>
        <th>Città</th>
        <th>Numero Civico</th>
        <th>Via</th>
        <th>Data di Nascita</th>
        <th>Recapito Telefonico</th>
    </tr>
    <%
        HostDAO hostDAO = new HostDAO();
        List<Host> hosts = hostDAO.doRetrieveAll();

        for(Host h: hosts){
    %>
    <tr>
        <td><%=h.getEmail()%></td>
        <td><%=h.getNome()%></td>
        <td><%=h.getCognome()%></td>
        <td><%=h.getPassword()%></td>
        <td><%=h.getCitta()%></td>
        <td><%=h.getNumCivico()%></td>
        <td><%=h.getVia()%></td>
        <td><%=h.getDataNascita()%></td>
        <td><%=h.getRecapitoTelefonico()%></td>
    </tr>
    <%}%>
</table>
<br>
<br>
    <h1 style = "margin-right: auto; margin-left: auto;">Prenotazioni</h1>
    <table style = "margin-left: auto; margin-right: auto;">
        <tr>
            <th>Check-In</th>
            <th>Check-Out</th>
            <th>Email Utente</th>
            <th>Numero Persone</th>
        </tr>
        <%
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            List<Prenotazione> prenotazioni = prenotazioneDAO.doRetrieveAll();

            for(Prenotazione p: prenotazioni){
        %>
        <tr>
            <td><%=p.getCheckIn()%></td>
            <td><%=p.getCheckOut()%></td>
            <td><%=p.getFkUtente()%></td>
            <td><%=p.getNumeroPersone()%></td>
        </tr>
        <%}%>
    </table>
</body>
</html>
