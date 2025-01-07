<%@ page import="Storage.Struttura.StrutturaDAO" %>
<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="java.util.List" %>
<%@ page import="Storage.Struttura.Struttura" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 03/01/2025
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visita alloggi della struttura</title>
    <link rel="stylesheet" href="Interface/css/riepilogoStrutture.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <%
        List<Alloggio> alloggi = (List<Alloggio>) request.getAttribute("alloggi");
        Struttura struttura = (Struttura) request.getAttribute("struttura");
    %>
    <div class="big-text" id="areaHost"> AREA HOST</div>

    <div class="sezione-aggiungi">
        <div class="mid-text" id="sezione"><%= struttura.getNomeStruttura() %></div>
        <div class="button-sezione-aggiungi">
            <div class="visualizzaPrenotazioni">
                <form action="../selezionaPrenotazioniServlet" method="post">
                    <input type="hidden" name="idStruttura" value="<%= struttura.getIdStruttura() %>" >
                    <input type="submit" value="Visualizza Prenotazioni" class="button" style="width:160px;" >
                </form>
            </div>
            <div class="aggiungiStruttura">
                <form action="login-servlet" method="post">
                    <input type="submit" value="Aggiungi Alloggio" class="button">
                </form>
            </div>
        </div>
    </div>

    <%  if(alloggi.isEmpty()) { %>
    <div class="mid-text">Non hai nessun'alloggio :( </div>
    <%  }
    else { %>
    <div class="container" id="containerStrutture">
        <% for(int i=0; i<alloggi.size(); i++) { %>
        <div class="rigaStruttura" id="rigaAlloggio" style="flex-direction: row;
    justify-content: space-between;">
            <div class="primaColonna">
                <div class="nomeAlloggio normal-text">Alloggio <%= alloggi.get(i).getNumeroAlloggio() %></div>
                <div class="tipoPostiLetto small-text">
                    <div class="tipoStanza">
                        Tipo stanza : <%= alloggi.get(i).getTipoAlloggio() %>
                    </div>
                    <div class="postiLetto">
                        Posti letto : <%= alloggi.get(i).getPostiletto() %>
                    </div>
                </div>
            </div>
            <div class="secondaColonna">
                <div class="prezzoNotte"><b>
                    Prezzo/notte : <%= alloggi.get(i).getPrezzoNotte() %>
                </b>
                </div>
                <div class="descrizioneAlloggio">
                    <%= alloggi.get(i).getDescrizione() %>
                </div>
            </div>
            <div class="terzaColonna">
                <div class="button">
                    <form action="login-servlet" method="post">
                        <input type="submit" value="Modifica Alloggio" class="button">
                    </form>
                    <form action="login-servlet" method="post">
                        <input type="submit" value="Elimina Alloggio" class="button">
                    </form>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <%  }  %>
</body>
</html>
