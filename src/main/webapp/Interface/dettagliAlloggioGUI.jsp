<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="Storage.Utente.Utente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PHB&B</title>

    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;
    %>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% if(callByServlet == 1) { %>
    <link rel="stylesheet" href="Interface/css/index.css">
    <link rel="stylesheet" href="Interface/css/dettagliAlloggio.css">
    <link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/dettagliAlloggio.css">
    <link rel="stylesheet" href="css/headerDopoAccesso.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <% } %>


</head>
<body>

    <div id="topContainer">
        <%
            Utente u = (Utente) request.getSession().getAttribute("utente");

            Alloggio alloggio = (Alloggio) request.getAttribute("alloggioSelezionato");

            if(request.getSession().getAttribute("utente") != null) { %>

        <%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>

        <%} else { %>

        <div id="divLogo">
            <p id="parLogo" class="mid-text">PHB&B</p>
        </div>
        <div id="divRegStruttura">
            <a id="linkRegStruttura" href="Interface/registrazioneHostGUI.jsp" class="normal-small-text">Registra la tua struttura</a>
        </div>
        <div id="divReg">
            <a id="linkReg" href="Interface/registrazioneUtenteGUI.jsp" class="small-text">Registrati</a>
        </div>
        <div id="divAccedi">
            <a id="linkAccedi" href="Interface/loginUtenteGUI.jsp" class="small-text">Accedi</a>
        </div>

        <%
            }
        %>
    </div>

    <div id="midContainer">
        <div id="containerFiltri">
            <div id="divFiltroLuogo">
                <p class="small-text"><b>Dove vuoi andare?</b></p>
                <input form="formRicerca" type="text" name="luogo" placeholder="Cerca destinazioni" class="small-text">
            </div>
            <div id="divFiltroDate">
                <div id="divCheckIn">
                    <p class="small-text"><b>Check-in</b></p>
                    <input form="formRicerca" type="date" name="dataCheckIn">
                </div>
                <div id="divCheckOut">
                    <p class="small-text"><b>Check-out</b></p>
                    <input form="formRicerca" type="date" name="dataCheckOut">
                </div>
            </div>
            <div id="divFiltroOspiti">
                <p class="small-text"><b>Chi?</b></p>
                <input form="formRicerca" type="text" name="numOspiti" placeholder="Aggiungi ospiti" class="small-text">
            </div>
            <div id="divCerca" class="normal-small-text">
                <form id="formRicerca" action="visualizzaAlloggi">
                    <input type="submit" value="Cerca" id="buttonCerca">
                </form>
            </div>
        </div>
    </div>


    <%
        if(alloggio != null) { %>

            <div id="alloggioSelezionatoContainer">
                <div id="leftContainer">
                    <div id="divNomeStruttura">
                        <p class="big-text"><b><%=alloggio.getStruttura().getNomeStruttura()%></b></p>
                    </div>
                    <div id="divImgAlloggio">
                        <img id="imgAlloggio" src="<%=alloggio.getUrlImmagine()%>">
                        <img id="imgStruttura" src="<%=alloggio.getStruttura().getUrlImmagine()%>">
                    </div>
                </div>
                <div id="rightContainer">
                    <div id="divDescrizione">
                        <p id="parDescrizione" class="normal-text"><b>DESCRIZIONE</b></p>
                        <p id="parTesto" class="normal-small-text"><%=alloggio.getDescrizione()%></p>
                    </div>
                    <div id="divPrenotazione">
                        <p class="normal-small-text parDatiPrenotazione"><b>Numero persone: <%=request.getParameter("numOspiti")%></b></p>
                        <p class="normal-small-text parDatiPrenotazione"><b>Check-in: <%=request.getParameter("check-in")%></b></p>
                        <p class="normal-small-text parDatiPrenotazione"><b>Check-out: <%=request.getParameter("check-out")%></b></p>
                        <p class="normal-text parDatiPrenotazione"><b><%=alloggio.getPrezzoNotte()%></b></p>
                        <div id="divTastoPrenota">
                            <a href="#" id="linkPrenota" class="normal-small-text" onclick="apriConferma()">Prenota</a>
                        </div>
                    </div>
                </div>
            </div>

            <div id="divConfermaPrenotazione" style="display: none">
                <form id="formConfermaPrenotazione" action="finalizzaPrenotazione" method="POST">
                    <div class="divDatiPrenotazione">
                        <p><b>Nome</b></p>
                        <input type="text" name="nome" class="datiPrenotazione">
                    </div>
                    <div class="divDatiPrenotazione">
                        <p><b>Cognome</b></p>
                        <input type="text" name="cognome" class="datiPrenotazione">
                    </div>
                    <div class="divDatiPrenotazione">
                        <p><b>Check-in</b></p>
                        <input type="date" name="dataCheckIn" class="datiPrenotazione">
                    </div>
                    <div class="divDatiPrenotazione">
                        <p><b>Check-out</b></p>
                        <input type="date" name="dataCheckOut" class="datiPrenotazione">
                    </div>
                    <div class="divDatiPrenotazione">
                        <p><b>Numero carta</b></p>
                        <input type="text" name="numeroCarta" class="datiPrenotazione">
                    </div>
                    <div class="divDatiPrenotazione">
                        <p><b>Data scadenza</b></p>
                        <input type="date" name="dataScadenzaCarta" class="datiPrenotazione">
                    </div>
                    <div class="divDatiPrenotazione">
                        <p><b>CVV</b></p>
                        <input type="text" name="cvvCarta" class="datiPrenotazione">
                    </div>
                    <input type="hidden" value="<%=alloggio.getNumeroAlloggio()%>" name="numAlloggio">
                    <input type="hidden" value="<%=alloggio.getStruttura().getIdStruttura()%>" name="codStruttura">
                    <input type="hidden" value="<%=request.getParameter("numOspiti")%>" name="numOspiti">
                </form>

                <div class="divDatiPrenotazione">
                    <input type="submit" form="formConfermaPrenotazione" class="buttons" value="Conferma">
                    <button onclick="chiudiConferma()" class="buttons">Annulla</button>
                </div>
            </div>

    <%}%>


    <script>
        function apriConferma() {

            let flag = false;

            <%
                if(u != null) { %>
                    flag = true;
            <%}%>

            if(flag) {
                document.getElementById("divConfermaPrenotazione").style.display = "block";
            } else {
                document.getElementById("linkPrenota").href = "Interface/loginUtenteGUI.jsp";
            }
        }

        function chiudiConferma() {
            document.getElementById("divConfermaPrenotazione").style.display = "none";
        }
    </script>
</body>
</html>
