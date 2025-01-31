<%@ page import="Storage.Utente.Utente" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 31/01/2025
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci recensione</title>

    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;
    %>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% if(callByServlet == 1) { %>
    <link rel="stylesheet" href="Interface/css/modificaDatiUtente.css">
    <link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <!--<link rel="stylesheet" href="Interface/css/header.css">-->
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/modificaDatiUtente.css">
    <link rel="stylesheet" href="css/headerDopoAccesso.css">
    <link rel="stylesheet" href="css/footer.css">
    <!--<link rel="stylesheet" href="css/header.css">-->
    <link rel="stylesheet" href="css/style.css">
    <% } %>

</head>
<body>
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

    Utente utente = (Utente) request.getSession().getAttribute("utente");

    String error =  request.getParameter("error");
%>

<div id="mainContainer">
    <div id="divModificaDati">
        <p id="parModificaDati" class="normal-text">INSERISCI RECENSIONE<p/>

        <%if(error != null && error.equals("1")) {%>
        <p id="parErrore"> Dati non rispettano il giusto formato !!!!</p>
        <%}%>

        <%if(utente != null) {%>
        <form id="formModificaDati" action="<%= servlet %>inserisciRecensioneServlet" method="POST">
            <div id="divForm" class="normal-small-text">
                <div class="formElements">
                    <label for="descrizione" id="descrizioneLabel">Descrizione</label>
                    <input type="text" id="descrizione" name="descrizione">
                </div>

                <div class="formElements">
                    <label for="votoRecensione" id="votoRecensioneLabel">Voto Recensione</label>
                    <input type="number" id="votoRecensione" name="votoRecensione">
                </div>

                <input type="hidden" id="codicePrenotazione" name="codicePrenotazione" value="<%=request.getParameter("codicePrenotazione")%>">
                <input type="hidden" id="numeroAlloggio" name="numeroAlloggio" value="<%=request.getParameter("numeroAlloggio")%>">
                <input type="hidden" id="idStruttura" name="idStruttura" value="<%=request.getParameter("idStruttura")%>">
            </div>

            <!--<div class="buttonModifica">-->
            <input type="submit" id="submitButtom" value="Modifica">
            <!--</div>-->
        </form>
        <%}%>

    </div>
</div>
</body>
</html>
