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
        // ha delle strutture..
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        List<Struttura> strutture = strutturaDAO.doRetrieveByCriteria("fk_host", "pintocarlo09@gmail.com");
    %>

    <div class="big-text" id="areaHost"> AREA HOST</div>

    <div class="sezione-aggiungi">
        <div class="mid-text" id="sezione"> Visualizza le tue strutture </div>
        <div class="aggiungiStruttura">
            <form action="login-servlet" method="post">
                <input type="submit" value="Aggiungi Struttura" class="button" style="width: 140px;">
            </form>
        </div>
    </div>

    <%  if(strutture.isEmpty()) { %>
            <div class="mid-text">Non hai nessuna struttura :( </div>
    <%  }
        else { %>
            <div class="container" id="containerStrutture">
                <% for(int i=0; i<strutture.size(); i++) { %>
                    <form id="form_<%= i %>" action="../selezionaStrutturaServlet" method="post">
                        <input type="hidden" value="<%= strutture.get(i).getIdStruttura() %>" name="idStruttura">

                        <div class="containerRigaStruttura" onclick="submitForm(<%= i %>)">
                            <div class="rigaStruttura">
                                <div class="elementiStruttura">
                                    <div class="title normal-text"><%= strutture.get(i).getNomeStruttura() %></div>
                                    <div class="numAlloggi small-text"> <b>Numero Alloggi</b> : <%= strutture.get(i).getNumAlloggi() %></div>
                                    <div class="immagine">immagine</div>
                                    <div class="descrizione small-text"><%= strutture.get(i).getDescrizione() %></div>
                                </div>
                            </div>
                            <div class="buttonRigaStruttura">
                                <form action="login-servlet" method="post">
                                    <input type="submit" value="Modifica" class="button" style="width: 80px;">
                                </form>
                                <form action="login-servlet" method="post">
                                    <input type="submit" value="Elimina" class="button" style="width: 80px;">
                                </form>
                            </div>
                        </div>
                    </form>
                <% } %>
            </div>
    <%  }  %>

    <%@ include file="../WEB-INF/moduli/footer.jsp"%>

    <!-- Script utilizzato per inviare il form nascosto quando clicchi su una struttura -->
    <script>
        function submitForm(index) {
            // Invia il form corrispondente alla riga cliccata
            document.getElementById('form_' + index).submit();
        }
    </script>
</body>
</html>
