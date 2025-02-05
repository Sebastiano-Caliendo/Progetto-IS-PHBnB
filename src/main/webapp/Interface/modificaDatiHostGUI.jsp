<%@ page import="Storage.Utente.Utente" %>
<%@ page import="Storage.Host.Host" %>
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
    Host host = (Host) request.getSession().getAttribute("host");

    String error =  request.getParameter("error");

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

<div id="mainContainer">
    <div id="divModificaDati">
        <p id="parModificaDati" class="normal-text">MODIFICA DATI HOST<p/>

        <%if(error != null && error.equals("1")) {%>
            <p id="parErrore" class="loginElements">Credenziali errate</p>
        <%}%>

        <%if(host != null) {%>
        <form id="formModificaDati" action="<%= servlet %>modificaDatiPersonaliHost" method="POST">
            <div id="divForm" class="normal-small-text">
                <div class="formElements">
                    <label for="email" id="emailLabel">Email</label>
                    <input type="text" id="email" name="email" value="<%=host.getEmail()%>">
                </div>

                <div class="formElements">
                    <label for="nuovaPassword" id="nuovaPasswordLabel">Nuova password</label>
                    <input type="password" id="nuovaPassword" name="nuovaPassword">
                </div>

                <div class="formElements">
                    <label for="nome" id="nomeLabel">Nome</label>
                    <input type="text" id="nome" name="nome" value="<%=host.getNome()%>">
                </div>

                <div class="formElements">
                    <label for="cognome" id="cognomeLabel">Cognome</label>
                    <input type="text" id="cognome" name="cognome" value="<%=host.getCognome()%>">
                </div>

                <div class="formElements">
                    <label for="recapitoTelefonico" id="recapitoTelefonicoLabel">Recapito telefonico</label>
                    <input type="text" id="recapitoTelefonico" name="recapitoTelefonico" value="<%=host.getRecapitoTelefonico()%>">
                </div>

                <!--<div class="formElements">
                    <label for="via" id="viaLabel">Via</label>
                    <input type="text" id="via" name="via" value="">
                </div>-->
            </div>

            <!--<div class="buttonModifica">-->
                <input type="submit" id="submitButton" value="Modifica">
            <!--</div>-->
        </form>
        <%}%>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
    document.getElementById("submitButton").addEventListener("click", function(event){
        event.preventDefault();

        Swal.fire({
            title: 'Attendi...',
            text: 'I dati saranno modificati a breve',
            icon: 'success',
            timer: 2000, // Il popup dura 2 secondi
            showConfirmButton: false,
            allowOutsideClick: false
        });

        setTimeout(function () {
            document.querySelector("form").submit();
        }, 2000);
    });
</script>
</body>
</html>
