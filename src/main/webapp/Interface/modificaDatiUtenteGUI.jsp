<%@ page import="Storage.Utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica dati utente</title>

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
            <p id="parModificaDati" class="normal-text">MODIFICA DATI UTENTE<p/>

            <%if(error != null && error.equals("1")) {%>
                <p id="parErrore"></p>
            <%}%>

            <%if(utente != null) {%>
                <form id="formModificaDati" action="<%= servlet %>modificaDatiPersonaliUtente" method="POST">
                    <div id="divForm" class="normal-small-text">
                        <div class="formElements">
                            <label for="email" id="emailLabel">Email</label>
                            <input type="text" id="email" name="email" value="<%=utente.getEmail()%>">
                        </div>

                        <div class="formElements">
                            <label for="nuovaPassword" id="nuovaPasswordLabel">Nuova password</label>
                            <input type="password" id="nuovaPassword" name="nuovaPassword">
                        </div>

                        <div class="formElements">
                            <label for="nome" id="nomeLabel">Nome</label>
                            <input type="text" id="nome" name="nome" value="<%=utente.getNome()%>">
                        </div>

                        <div class="formElements">
                            <label for="cognome" id="cognomeLabel">Cognome</label>
                            <input type="text" id="cognome" name="cognome" value="<%=utente.getCognome()%>">
                        </div>

                        <div class="formElements">
                            <label for="citta" id="cittaLabel">Città</label>
                            <input type="text" id="citta" name="citta" value="<%=utente.getCitta()%>">
                        </div>

                        <div class="formElements">
                            <label for="numCivico" id="numCivicoLabel">Numero civico</label>
                            <input type="text" id="numCivico" name="numCivico" value="<%=utente.getNumeroCivico()%>">
                        </div>

                        <div class="formElements">
                            <label for="via" id="viaLabel">Via</label>
                            <input type="text" id="via" name="via" value="<%=utente.getVia()%>">
                        </div>

                        <div class="formElements">
                            <label for="recapitoTelefonico" id="recapitoTelefonicoLabel">Recapito telefonico</label>
                            <input type="text" id="recapitoTelefonico" name="recapitoTelefonico" value="<%=utente.getRecapitoTelefonico()%>">
                        </div>
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
