<%@ page import="Storage.Utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area utente</title>

    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;
    %>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% if(callByServlet == 1) { %>
    <link rel="stylesheet" href="Interface/css/areaUtente.css">
    <link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/areaUtente.css">
    <link rel="stylesheet" href="css/headerDopoAccesso.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
    <% } %>

</head>
<body>
    <%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>

    <%
        Utente u = (Utente) request.getSession().getAttribute("utente");
    %>

    <div id="mainContainer">
        <div id="leftContainer">
            <div id="divAreaAccount"><a href="" id="linkAreaAccount" class="normal-small-text">Area account</a></div>
            <div id="divPrenotazioni"><a href="../visualizzaPrenotazioni" id="linkPrenotazioni" class="normal-small-text">Storico prenotazioni</a></div>
        </div>
        <div id="rightContainer">
            <div id="topContainer">
                <p id="parAreaUtente" class="big-text">AREA UTENTE</p>
            </div>

            <div id="midContainer">
                <div id="divDatiUtente">
                    <%
                        if(u != null) {
                    %>

                            <div class="divDati">
                                <p class="parDati"><b>Email</b></p>
                                <p class="parDati"><%=u.getEmail()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati"><b>Nome</b></p>
                                <p class="parDati"><%=u.getNome()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati"><b>Cognome</b></p>
                                <p class="parDati"><%=u.getCognome()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati"><b>Città</b></p>
                                <p class="parDati"><%=u.getCitta()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati"><b>Numero civico</b></p>
                                <p class="parDati"><%=u.getNumeroCivico()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati"><b>Via</b></p>
                                <p class="parDati"><%=u.getVia()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati"><b>Data nascita</b></p>
                                <p class="parDati"><%=u.getDataNascita()%></p>
                            </div>
                            <div class="divDati" id="ultimo">
                                <p class="parDati"><b>Recapito telefonico</b></p>
                                <p class="parDati"><%=u.getRecapitoTelefonico()%></p>
                            </div>

                    <%
                        }
                    %>
                </div>
            </div>

            <div id="bottomContainer">
                <a href="modificaDatiUtenteGUI.jsp" id="linkModificaDati" class="normal-text"><b>Modifica dati account ></b></a>
            </div>
        </div>
    </div>
</body>
</html>
