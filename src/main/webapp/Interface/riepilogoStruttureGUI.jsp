<%@ page import="Storage.Struttura.Struttura" %>
<%@ page import="java.util.List" %>
<%@ page import="Storage.Struttura.StrutturaDAO" %>
<%@ page import="Storage.Alloggio.AlloggioDAO" %><%--
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
        //List<Struttura> strutture = (List<Struttura>) request.getAttribute("listaStrutture");
        // questo codice andrà omesso, ma lo utilizzo per vedere come la pagina si comporta quando l'host
        // ha delle strutture
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        List<Struttura> strutture = strutturaDAO.doRetrieveByCriteria("fk_host", "pintocarlo09@gmail.com");
    %>

    <div class="big-text" id="areaHost"> AREA HOST</div>

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

    <%  if(strutture.isEmpty()) { %>
            <div class="mid-text">Non hai nessuna struttura :( </div>
    <%  }
        else { %>
            <div class="container" id="containerStrutture">
                <% for(int i=0; i<strutture.size(); i++) { %>
                    <div class="rigaStruttura">
                        <div class="elementiStruttura">
                            <div class="title normal-text"><%= strutture.get(i).getNomeStruttura() %></div>
                            <div class="numAlloggi small-text"> <b>Numero Alloggi</b> : <%= strutture.get(i).getNumAlloggi() %></div>
                            <div class="immagine">immagine</div>
                            <div class="descrizione small-text"><%= strutture.get(i).getDescrizione() %></div>
                        </div>
                    </div>
                <% } %>
            </div>
    <%  }  %>

    <%@ include file="../WEB-INF/moduli/footer.jsp"%>
</body>
</html>
