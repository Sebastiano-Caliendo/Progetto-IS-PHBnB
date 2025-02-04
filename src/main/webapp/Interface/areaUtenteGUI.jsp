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
        <div id="leftContainer">
            <div id="divAreaAccount"><a href="" id="linkAreaAccount" class="normal-small-text">Area account</a></div>
            <div id="divPrenotazioni"><a href="<%= servlet %>visualizzaPrenotazioni" id="linkPrenotazioni" class="normal-small-text">Storico prenotazioni</a></div>
            <div id = "divElimina"><a href ="#" onclick="apriForm()" id = "linkEliminaAccount" class = "normal-small-text">Elimina Account</a></div>
        </div>
        <div id="rightContainer">
            <div id="topContainer">
                <p id="parAreaUtente" class="big-text">AREA UTENTE</p>
            </div>

            <div id="midContainer">
                <div id="divDatiUtente" class="normal-small-text">
                    <%
                        boolean loggato = false;
                        if(u != null) {
                            loggato = true;
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
                <a href="<%= jsp %>modificaDatiUtenteGUI.jsp" id="linkModificaDati" class="normal-text"><b>Modifica dati account ></b></a>
            </div>
        </div>
    </div>

    <div id="divEliminaAccount" style="display: none">
        <div id = "formEliminaAccount">
            <div class="divEliminaAccount">
                <p><b>Sei sicuro di voler eliminare il tuo account?</b></p>
            </div>
            <div class="divEliminaAccount">
                <input type="submit" id = "premiElimina" class="buttons" value = "Si">
                <input type="submit" onclick="chiudiForm()" class="buttons" value = "No">
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>

        document.getElementById("premiElimina").addEventListener("click", function (event) {
            event.preventDefault();

            Swal.fire({
                title: 'Attendi...',
                text: 'L\'account verrà eliminato a breve',
                icon: 'success',
                timer: 4000, // Il popup dura 4 secondi
                showConfirmButton: false,
                allowOutsideClick: false
            });

            setTimeout(function () {
                reindirizzaServlet();
            }, 4000);

        });

        function apriForm(){
            document.getElementById("divEliminaAccount").style.display = "block";
        }

        function reindirizzaServlet(){
            window.location.href = "<%=servlet%>eliminaAccount";
        }

        function chiudiForm(){
            document.getElementById("divEliminaAccount").style.display = "none";
        }

        let loggedIn = <%=loggato%>
            if(loggedIn) {
                Swal.fire({
                    title: 'Benvenuto <%=u.getNome()%>',
                    icon: 'success',
                    confirmButtonText: 'OK',
                    customClass:{
                        confirmButton: 'btn-custom'
                    }
                });
            }
    </script>
</body>
</html>
