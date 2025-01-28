<%@ page import="Storage.Utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica dati utente</title>

    <link rel="stylesheet" href="css/modificaDatiUtente.css">
</head>
<body>
    <%
        Utente u = (Utente) request.getSession().getAttribute("utente");

        String error =  request.getParameter("error");
    %>

    <div id="mainContainer">
        <div id="divModificaDati">
            <p id="parModificaDati">MODIFICA DATI UTENTE<p/>

            <%if(error != null && error.equals("1")) {%>
                <p id="parErrore"></p>
            <%}%>

            <%if(u != null) {%>
                <form id="formModificaDati" action="../modificaDatiPersonaliUtente" method="POST">
                    <div id="divForm">
                        <div class="formElements">
                            <label for="email" id="emailLabel">Email</label>
                            <input type="text" id="email" name="email" value="<%=u.getEmail()%>">
                        </div>

                        <div class="formElements">
                            <label for="nuovaPassword" id="nuovaPasswordLabel">Nuova password</label>
                            <input type="password" id="nuovaPassword" name="nuovaPassword">
                        </div>

                        <div class="formElements">
                            <label for="nome" id="nomeLabel">Nome</label>
                            <input type="text" id="nome" name="nome" value="<%=u.getNome()%>">
                        </div>

                        <div class="formElements">
                            <label for="cognome" id="cognomeLabel">Cognome</label>
                            <input type="text" id="cognome" name="cognome" value="<%=u.getCognome()%>">
                        </div>

                        <div class="formElements">
                            <label for="citta" id="cittaLabel">Città</label>
                            <input type="text" id="citta" name="citta" value="<%=u.getCitta()%>">
                        </div>

                        <div class="formElements">
                            <label for="numCivico" id="numCivicoLabel">Numero civico</label>
                            <input type="text" id="numCivico" name="numCivico" value="<%=u.getNumeroCivico()%>">
                        </div>

                        <div class="formElements">
                            <label for="via" id="viaLabel">Via</label>
                            <input type="text" id="via" name="via" value="<%=u.getVia()%>">
                        </div>

                        <div class="formElements">
                            <label for="recapitoTelefonico" id="recapitoTelefonicoLabel">Recapito telefonico</label>
                            <input type="text" id="recapitoTelefonico" name="recapitoTelefonico" value="<%=u.getRecapitoTelefonico()%>">
                        </div>
                    </div>

                    <input type="submit" id="submitButtom" value="Modifica">
                </form>
            <%}%>

        </div>
</div>
</body>
</html>
