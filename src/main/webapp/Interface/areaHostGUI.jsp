<%@ page import="Storage.Host.Host" %>
<%@ page import="Storage.Utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area host</title>

    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;

        //Utente utente = (Utente) session.getAttribute("utente");
        Host host = (Host) session.getAttribute("host");
        //Utente admin = (Utente) session.getAttribute("admin");

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


<div id="mainContainer">
    <div id="leftContainer">
        <div id="divAreaAccount"><a href="<%= jsp %>modificaDatiHost.jsp" id="linkAreaAccount" class="normal-small-text">Area Host</a></div>
        <div id="divPrenotazioni"><a href="<%= servlet %>riepilogoStruttureServlet" id="linkPrenotazioni" class="normal-small-text">Riepilogo Strutture</a></div>
    </div>
    <div id="rightContainer">
        <div id="topContainer">
            <p id="parAreaUtente" class="big-text">AREA HOST</p>
        </div>

        <div id="midContainer">
            <div id="divDatiUtente" class="normal-small-text">
                <%
                    if(h != null) {
                %>

                <div class="divDati">
                    <p class="parDati"><b>Email</b></p>
                    <p class="parDati"><%=host.getEmail()%></p>
                </div>
                <div class="divDati">
                    <p class="parDati"><b>Nome</b></p>
                    <p class="parDati"><%=host.getNome()%></p>
                </div>
                <div class="divDati">
                    <p class="parDati"><b>Cognome</b></p>
                    <p class="parDati"><%=host.getCognome()%></p>
                </div>
                <div class="divDati">
                    <p class="parDati"><b>Data nascita</b></p>
                    <p class="parDati"><%=host.getDataNascita()%></p>
                </div>
                <div class="divDati" id="ultimo">
                    <p class="parDati"><b>Recapito telefonico</b></p>
                    <p class="parDati"><%=host.getRecapitoTelefonico()%></p>
                </div>

                <%
                    }
                %>
            </div>
        </div>

        <div id="bottomContainer">
            <a href="<%= jsp %>modificaDatiHostGUI.jsp" id="linkModificaDati" class="normal-text"><b>Modifica dati account ></b></a>
        </div>
    </div>
</div>
</body>
</html>
