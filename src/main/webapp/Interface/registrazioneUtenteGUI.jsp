<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrazione utente</title>

    <link rel="stylesheet" href="css/loginRegistrazione.css">
</head>
<body>
    <%
        String error =  request.getParameter("error");
    %>

    <div id="mainContainer">
        <div id="divRegistrazione">
            <p id="parRegistrazione">REGISTRATI A PHB&B<p/>

            <%if(error != null && error.equals("1")) {%>
                <p id="parErrore">Utente già registrato</p>
            <%}%>

            <form id="formLogin" action="../registrazioneUtente" method="POST">
                <div id="divForm">
                    <div class="formElements">
                        <label for="email" id="emailLabel">Email</label>
                        <input type="text" id="email" name="email" required>
                    </div>

                    <div class="formElements">
                        <label for="password" id="pwdLabel">Password</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                    <div class="formElements">
                        <label for="nome" id="nomeLabel">Nome</label>
                        <input type="text" id="nome" name="nome" required>
                    </div>

                    <div class="formElements">
                        <label for="cognome" id="cognomeLabel">Cognome</label>
                        <input type="text" id="cognome" name="cognome" required>
                    </div>

                    <div class="formElements">
                        <label for="citta" id="cittaLabel">Città</label>
                        <input type="text" id="citta" name="citta" required>
                    </div>

                    <div class="formElements">
                        <label for="numCivico" id="numCivicoLabel">Numero civico</label>
                        <input type="text" id="numCivico" name="numCivico" required>
                    </div>

                    <div class="formElements">
                        <label for="via" id="viaLabel">Via</label>
                        <input type="text" id="via" name="via" required>
                    </div>

                    <div class="formElements">
                        <label for="dataNascita" id="dataNascitaLabel">Data nascita</label>
                        <input type="date" id="dataNascita" name="dataNascita" required>
                    </div>

                    <div class="formElements">
                        <label for="recapitoTelefonico" id="recapitoTelefonicoLabel">Recapito telefonico</label>
                        <input type="text" id="recapitoTelefonico" name="recapitoTelefonico" required>
                    </div>

                    <div class="formElements">
                        <a href="registrazioneHostGUI.jsp" id="linkRegHost">Possiedi una struttura? Registrati come host qui</a>
                    </div>
                </div>

                <input type="submit" id="submitButtom" value="Registrati">
            </form>
        </div>
    </div>
</body>
</html>
