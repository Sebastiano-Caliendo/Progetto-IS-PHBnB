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
    <link rel="stylesheet" href="css/riepilogoStrutture.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <%
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        Struttura struttura = strutturaDAO.doRetrieveById(1);
        List<Alloggio> alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);
    %>
    <div class="big-text" id="areaHost"> AREA HOST</div>

    <div class="sezione-aggiungi">
        <div class="normal-text" id="sezione"><%= struttura.getNomeStruttura() %></div>
        <div class="aggiungiStruttura">
            <form action="login-servlet" method="post">
                <input type="submit" value="Aggiungi Alloggio">
            </form>
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
                <div class="nomeAlloggio">ALLOGGIO <%= alloggi.get(i).getNumeroAlloggio() %></div>
                <div class="tipoPostiLetto">
                    <div class="tipoStanza">
                        Tipo stanza : <%= alloggi.get(i).getTipoAlloggio() %>
                    </div>
                    <div class="postiLetto">
                        Posti letto : <%= alloggi.get(i).getPostiletto() %>
                    </div>
                </div>
            </div>
            <div class="secondaColonna">
                <div class="prezzoNotte">
                    Prezzo/notte : <%= alloggi.get(i).getPrezzoNotte() %>
                </div>
                <div class="descrizioneAlloggio">
                    <%= alloggi.get(i).getDescrizione() %>
                </div>
            </div>
            <div class="terzaColonna">
                <div class="button">
                    <!-- immagini della modifica e dell'elimina -->
                    <form action="login-servlet" method="post">
                        <input type="submit" value="Modifica Alloggio">
                    </form>
                    <form action="login-servlet" method="post">
                        <input type="submit" value="Elimina Alloggio">
                    </form>
                </div>
            </div>
        </div>
        <% } %>
    </div>
    <%  }  %>
</body>
</html>
