<%@ page import="java.util.List" %>
<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="Storage.Recensione.Recensione" %>
<%@ page import="Storage.Host.HostDAO" %>
<%@ page import="Storage.Host.Host" %>
<%@ page import="Storage.Prenotazione.Prenotazione" %>
<%@ page import="Storage.Struttura.Struttura" %>
<%@ page import="Storage.Utente.Utente" %>
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
    <link rel="stylesheet" href="Interface/css/riepilogoStrutture.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <link rel="stylesheet" href="Interface/css/visDatiSistema.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/riepilogoStrutture.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/visDatiSistema.css">
    <% } %>

    <title>Visualizza Dati Sistema</title>
</head>
<body style="overflow-x: auto;">
<%@ include file ="../WEB-INF/moduli/header.jsp"%>
<br>
    <div class = "big-text" id = "areaAdmin" style = "text-align: center">AREA AMMINISTRATORE</div>

    <%
        String servlet = "";
        String jsp = "";
        if(callByServlet == 0) { // chiamata da jsp
            servlet = "../";
            jsp = "";
        }
        else  {  // chiamata da servlet
            servlet = "";
            jsp = "Interface/";
        }
    %>
<div class = "rigaStruttura areaAdminRiga">
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
        List<Utente> utenti = (List<Utente>) request.getAttribute("utenti");

        for(Utente u: utenti){
    %>
        <form method = "post">
    <tr>
        <td><input type = "text" value = "<%=u.getEmail()%>" name = "emailUtente"></td>
        <td><input type = "text" value = "<%=u.getNome()%>" name = "nomeUtente"></td>
        <td><input type = "text" value = "<%=u.getCognome()%>" name = "cognomeUtente"></td>
        <td><input type = "text" value = "<%=u.getPassword()%>" name = "passwordUtente"></td>
        <td><input type = "text" value = "<%=u.getCitta()%>" name = "cittaUtente"></td>
        <td><input type = "text" value = "<%=u.getNumeroCivico()%>" name = "numeroCivicoUtente"></td>
        <td><input type = "text" value = "<%=u.getVia()%>" name = "viaUtente"></td>
        <td><%=u.getDataNascita()%></td>
        <td><input type = "text" value = "<%=u.getRecapitoTelefonico()%>" name = "recapitoTelefonicoUtente"></td>
        <input type = "hidden" value = "<%=u.isAdmin()%>" name = "isAdminUtente">
        <td><input type = "submit" value = "Modifica" class = "buttonDati" style = "width: 75px;" formaction="modificaDatiSistemaUtenteServlet"></form></td>
    </tr>
    <%}%>
</table>
    <!--<div style = "margin-left: auto; margin-right: auto;">
    <input type = "submit" value = "Modifica" class = "buttonDati" style = "width: 75px;" formaction="modificaDatiSistemaUtenteServlet"></form>
    </div>!-->
</div>
<br>
<br>
<div class = "rigaStruttura areaAdminRiga">
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
        List<Alloggio> alloggi = (List<Alloggio>) request.getAttribute("alloggi");

        for(Alloggio a: alloggi){
    %>
    <form method="post">
    <tr>
        <input type = "hidden" value = "<%=a.getNumeroAlloggio()%>" name = "oldNumAlloggio">
        <td><input type = "text" value = "<%=a.getNumeroAlloggio()%>" name = "numAlloggio"></td>
        <td><input type = "text" value = "<%=a.getStruttura().getIdStruttura()%>" name = "fkStruttura"></td>
        <td><input type = "text" value = "<%=a.getPrezzoNotte()%>" name = "prezzoNotte"></td>
        <td><input type = "text" value = "<%=a.getPostiletto()%>" name = "postiLetto"></td>
        <td><input type = "text" value = "<%=a.getTipoAlloggio()%>" name = "tipoAlloggio"></td>
        <td><input type = "text" value = "<%=a.getDescrizione()%>" name = "descAlloggio"></td>
        <td><input type = "submit" value = "Modifica" class = "buttonDati" style = "width: 75px;" formaction = "modificaDatiSistemaAlloggioServlet"></td>
        <td><input type = "submit" value = "Cancella" class = "buttonDati" style = "width: 75px;" formaction = "cancellazioneDatiSistemaAlloggioServlet"></form></td>
    </tr>
    <%}%>
</table>
</div>
<br>
<br>
<div class = "rigaStruttura areaAdminRiga">
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
        List<Struttura> strutture = (List<Struttura>) request.getAttribute("strutture");

        for(Struttura s: strutture){
    %>
        <form method="post">
    <tr>
        <input type = "hidden" value = "<%=s.getIdStruttura()%>" name = "idStruttura">
        <td><%=s.getHost().getEmail()%></td>
        <td><input type = "text" value = "<%=s.getNomeStruttura()%>" name = "nomeStruttura"></td>
        <td><input type = "text" value = "<%=s.getVia()%>" name = "viaStruttura"></td>
        <td><input type = "text" value = "<%=s.getNumCivico()%>" name = "numCivicoStruttura"></td>
        <td><input type = "text" value = "<%=s.getCitta()%>" name = "cittaStruttura"></td>
        <td><input type = "text" value = "<%=s.getNumAlloggi()%>" name = "numAlloggiStruttura"></td>
        <td><input type = "text" value = "<%=s.getDescrizione()%>" name = "descrizioneStruttura"></td>
        <td><input type = "submit" value = "Modifica" class = "buttonDati" style = "width: 75px;" formaction = "modificaDatiSistemaStrutturaServlet"></td>
        <td><input type = "submit" value = "Cancella" class = "buttonDati" style = "width: 75px" formaction = "cancellazioneDatiSistemaStrutturaServlet"></form></td>
    </tr>
    <%
        }%>
</table>
</div>
    <br>
    <br>
<div class = "rigaStruttura areaAdminRiga">
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
            List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioni");

            for(Recensione r: recensioni){
        %>
        <form method = "post">
        <tr>
            <input type = "hidden" value = "<%=r.getIdRecensione()%>" name = "idRecensione">
            <td><%=r.getUtente().getEmail()%></td>
            <td><%=r.getDescrizione()%></td>
            <td><%=r.getVotoRecensione()%></td>
            <td><%=r.getDataRecensione()%></td>
            <td><%=r.getPrenotazione().getCodicePrenotazione()%></td>
            <td><%=r.getAlloggio().getNumeroAlloggio()%></td>
            <td><%=r.getAlloggio().getStruttura().getIdStruttura()%></td>
            <td><input type = "submit" value = "Cancella" class = "buttonDati" style = "width: 75px;" formaction = "cancellazioneDatiSistemaRecensioneServlet"></form></td>
        </tr>
        <%}%>
    </table>
</div>
<br>
<br>
<div class = "rigaStruttura areaAdminRiga">
    <h1 style = "margin-right: auto; margin-left: auto;">Hosts</h1>
    <table style = "margin-left: auto; margin-right: auto;">
    <tr>
        <th>Email</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Password</th>
        <th>Data di Nascita</th>
        <th>Recapito Telefonico</th>
    </tr>
    <%
        List<Host> hosts = (List<Host>) request.getAttribute("proprietari");

        for(Host h: hosts){
    %>
        <form method = "post">
    <tr>
        <td><input type = "text" value = "<%=h.getEmail()%>" name = "emailHost"></td>
        <td><input type = "text" value = "<%=h.getNome()%>" name = "nomeHost"></td>
        <td><input type = "text" value = "<%=h.getCognome()%>" name = "cognomeHost"></td>
        <td><input type = "text" value = "<%=h.getPassword()%>" name = "passwordHost"></td>
        <td><%=h.getDataNascita()%></td>
        <td><input type = "text" value = "<%=h.getRecapitoTelefonico()%>" name = "recapitoTelHost"></td>
        <td><input type = "submit" value = "Modifica" class = "buttonDati" style = "width: 75px;" formaction = "modificaDatiSistemaHostServlet"></form></td>
    </tr>
    <%}%>
</table>
</div>
<br>
<br>
<div class = "rigaStruttura areaAdminRiga">
    <h1 style = "margin-right: auto; margin-left: auto;">Prenotazioni</h1>
    <table style = "margin-left: auto; margin-right: auto;">
        <tr>
            <th>Check-In</th>
            <th>Check-Out</th>
            <th>Email Utente</th>
            <th>Numero Persone</th>
        </tr>
        <%
            List<Prenotazione> prenotazioni = (List<Prenotazione>) request.getAttribute("prenotazioni");

            for(Prenotazione p: prenotazioni){
        %>
        <form method = "post">
        <tr>
            <input type = "hidden" value = "<%=p.getCodicePrenotazione()%>" name = "codicePrenotazione">
            <td><input type = "date" value = "<%=p.getCheckIn()%>" name = "checkInPrenotazione"></td>
            <td><input type = "date" value = "<%=p.getCheckOut()%>" name = "checkOutPrenotazione"></td>
            <td><input type = "text" value = "<%=p.getUtente().getEmail()%>" name = "idUtenteFk" readonly></td>
            <td><input type = "text" value = "<%=p.getNumeroPersone()%>" name = "numeroPersonePrenotazione"></td>
            <input type = "hidden" value = "<%=p.getNumeroCartaCredito()%>" name = "numeroCartaPrenotazione">
            <input type = "hidden" value = "<%=p.getDataScadenzaCarta()%>" name = "dataScadenzaCartaPrenotazione">
            <input type = "hidden" value = "<%=p.getCviCarta()%>" name = "cviCartaPrenotazione">
            <td><input type = "submit" value = "Modifica" class = "buttonDati" style = "width: 75px" formaction = "modificaDatiSistemaPrenotazioneServlet"></td>
        </tr>
        <%}%>
    </table>
    </form>
</div>
    <%@ include file="../WEB-INF/moduli/footer.jsp"%>

</body>
</html>
