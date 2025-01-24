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
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/riepilogoStrutture.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
    <% } %>
    <title> Alloggi Struttura </title>
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <%
        List<Alloggio> alloggi = (List<Alloggio>) request.getAttribute("alloggi");
        Struttura struttura = (Struttura) request.getAttribute("struttura");
    %>
    <div class="big-text" id="areaHost"> AREA HOST</div>

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

    <div class="sezione-aggiungi" id="sezione-aggiungi-alloggi">
        <div class="mid-text" id="sezioneAlloggio"><%= struttura.getNomeStruttura() %></div>
        <div class="button-sezione-aggiungi" id="button-sezione-aggiungi-alloggi">
            <div class="visualizzaPrenotazioni">
                <form action="<%= servlet %>selezionaPrenotazioniServlet" method="post">
                    <input type="hidden" name="idStruttura" value="<%= struttura.getIdStruttura() %>" >
                    <input type="submit" value="Prenotazioni" class="button" style="width:160px;" >
                </form>
            </div>
            <div class="visualizzaRecensioni">
                <form action="<%= servlet %>visualizzaRecensioneRicevuteServlet" method="post">
                    <input type="hidden" name="idStruttura" value="<%= struttura.getIdStruttura() %>" >
                    <input type="submit" value="Recensioni" class="button" style="width:160px;" >
                </form>
            </div>
            <div class="aggiungiStruttura">
                <form action="<%= servlet %>redirectToAggiungiAlloggioServlet" method="post">
                    <input type="hidden" value="<%= struttura.getIdStruttura() %>" name="idStruttura">
                    <input type="submit" value="Aggiungi" class="button">
                </form>
            </div>
        </div>
    </div>

    <%  if(alloggi.isEmpty()) { %>
    <div class="nessuno normal-text">Non hai nessun'alloggio :( </div>
    <%  }
    else { %>
    <div class="container" id="containerAlloggi">
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
            <div class="secondaColonna small-text">
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
                    <form action="<%= servlet %>redirectToModificaAlloggioServlet" method="post">
                        <input type="hidden" value="<%= alloggi.get(i).getNumeroAlloggio() %>" name="numeroAlloggio">
                        <input type="hidden" value="<%= struttura.getIdStruttura() %>" name="idStruttura">
                        <input type="submit" value="Modifica" class="button">
                    </form>
                    <form action="<%= servlet %>eliminaAlloggioServlet" method="post">
                        <input type="hidden" value="<%= alloggi.get(i).getNumeroAlloggio() %>" name="numeroAlloggio">
                        <input type="hidden" value="<%= struttura.getIdStruttura() %>" name="idStruttura">
                        <input type="submit" value="Elimina" class="button">
                    </form>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <%  }  %>
</body>
</html>
