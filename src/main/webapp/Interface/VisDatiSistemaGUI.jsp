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
</body>
</html>
