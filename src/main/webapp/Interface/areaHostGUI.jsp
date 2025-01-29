<%@ page import="Storage.Host.Host" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area host</title>

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
    Host h = (Host) request.getSession().getAttribute("host");
%>

<div id="mainContainer">
    <div id="leftContainer">
        <div id="divAreaAccount"><a href="" id="linkAreaAccount" class="normal-small-text">Area account</a></div>
        <div id="divPrenotazioni"><a href="../riepilogoStruttureServlet" id="linkPrenotazioni" class="normal-small-text">Riepilogo Strutture</a></div>
    </div>
    <div id="rightContainer">
        <div id="topContainer">
            <p id="parAreaUtente" class="big-text">AREA HOST</p>
        </div>

        <div id="midContainer">
            <div id="divDatiUtente">
                <%
                    if(h != null) {
                %>

                <div class="divDati">
                    <p class="parDati"><b>Email</b></p>
                    <p class="parDati"><%=h.getEmail()%></p>
                </div>
                <div class="divDati">
                    <p class="parDati"><b>Nome</b></p>
                    <p class="parDati"><%=h.getNome()%></p>
                </div>
                <div class="divDati">
                    <p class="parDati"><b>Cognome</b></p>
                    <p class="parDati"><%=h.getCognome()%></p>
                </div>
                <div class="divDati">
                    <p class="parDati"><b>Data nascita</b></p>
                    <p class="parDati"><%=h.getDataNascita()%></p>
                </div>
                <div class="divDati" id="ultimo">
                    <p class="parDati"><b>Recapito telefonico</b></p>
                    <p class="parDati"><%=h.getRecapitoTelefonico()%></p>
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
