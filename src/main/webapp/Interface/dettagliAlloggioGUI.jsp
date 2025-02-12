<%@ page import="Storage.Alloggio.Alloggio" %>
<%@ page import="Storage.Utente.Utente" %>
<%@ page import="Storage.Prenotazione.Prenotazione" %>
<%@ page import="Storage.Occupa.Occupa" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="Utility.Validator" %>
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
            String error = request.getParameter("error");

            Validator validator = new Validator();
            Utente utente = (Utente) session.getAttribute("utente");
            Host host = (Host) session.getAttribute("host");
            Utente admin = (Utente) session.getAttribute("admin");

            boolean loggato = false;
            if(utente == null && host == null && admin == null)
                loggato = false;
            else
                loggato = true;

            Alloggio alloggio = (Alloggio) request.getAttribute("alloggioSelezionato");

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

            if(loggato) { %>

                <%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>

        <%} else { %>

        <div id="divLogo">
            <p id="parLogo" class="mid-text">PHB&B</p>
        </div>
        <div id="divRegStruttura">
            <a id="linkRegStruttura" href="<%= jsp %>registrazioneHostGUI.jsp" class="normal-small-text">Registra la tua struttura</a>
        </div>
        <div id="divReg">
            <button id="linkReg" onclick="window.location.href='<%= jsp %>registrazioneUtenteGUI.jsp';" class="small-text buttons">Registrati</button>
        </div>
        <div id="divAccedi">
            <button id="linkAccedi" onclick="window.location.href='<%= jsp %>loginUtenteGUI.jsp';" class="small-text buttons">Accedi</button>
        </div>

        <%}%>

    </div>

    <p id="parErrore" style="display: none">Prenotazione non effettuata! Dati non inseriti correttamente</p>

    <div id="midContainer">
        <div id="containerFiltri">
            <div id="divFiltroLuogo">
                <p class="small-text"><b>Dove vuoi andare?</b></p>
                <input form="formRicerca" type="text" name="luogo" placeholder="Cerca destinazioni" class="small-text" style="width: 80%;" required>
            </div>
            <div id="divFiltroDate">
                <div id="divCheckIn">
                    <p class="small-text"><b>Check-in</b></p>
                    <input form="formRicerca" type="date" name="dataCheckIn" id="dataCheckIn" style="width: 98%;" required onchange="getNextDate()">
                </div>
                <div id="divCheckOut">
                    <p class="small-text"><b>Check-out</b></p>
                    <input form="formRicerca" type="date" name="dataCheckOut" id="dataCheckOut" style="width: 98%;" required>
                </div>
            </div>
            <div id="divFiltroOspiti">
                <p class="small-text"><b>Chi?</b></p>
                <input form="formRicerca" type="text" name="numOspiti" placeholder="Aggiungi ospiti" class="small-text" style="width: 90%;" required>
            </div>
            <div id="divCerca" class="normal-small-text">
                <form id="formRicerca" action="<%= servlet %>visualizzaAlloggi">
                    <input type="submit" value="Cerca" id="buttonCerca">
                </form>
            </div>
        </div>
    </div>


    <%
        if(alloggio != null) {
            LocalDate checkIn = validator.validateData(request.getParameter("check-in"));
            LocalDate checkOut = validator.validateData(request.getParameter("check-out"));


            int differenzaGiorni = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    %>

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
                        <div class="testoPrenotazione">
                            <!--<div class="parteUno">-->
                                <div class="checkIn">
                                    <p class="small-text parDatiPrenotazione titleTestoPrenotazione"><b>Check-in: </b></p>
                                    <p class="small-text parDatiPrenotazione"><b><%=request.getParameter("check-in")%></b></p>
                                </div>
                                <div class="checkOut">
                                    <p class="small-text parDatiPrenotazione titleTestoPrenotazione"><b>Check-out: </b></p>
                                    <p class="small-text parDatiPrenotazione"><b><%=request.getParameter("check-out")%></b></p>
                                </div>
                            <!--</div>-->
                            <!--<div class="parteDue">-->
                                <div class="numeroPersone">
                                    <p class="small-text parDatiPrenotazione titleTestoPrenotazione"><b>Numero persone: </b></p>
                                    <p class="small-text parDatiPrenotazione"><b><%=request.getParameter("numOspiti")%></b></p>
                                </div>
                                <div class="prezzo">
                                    <!--<p class="normal-text parDatiPrenotazione"></p>-->
                                    <p><b><%=alloggio.getPrezzoNotte() * differenzaGiorni%></b></p>
                                </div>
                            <!--</div>-->
                        </div>
                        <div id="divTastoPrenota">
                            <button id="linkPrenota" class="normal-small-text buttons" onclick="apriConferma();">Prenota</button>
                        </div>
                    </div>
                </div>
            </div>

            <div id="divConfermaPrenotazione" class="normal-small-text" style="display: none">
                <form id="formConfermaPrenotazione" action="<%= servlet %>finalizzaPrenotazione" method="POST">
                    <div class="divDatiPrenotazione">
                        <p><b>Nome</b></p>
                        <input type="text" name="nome" id="nome" class="datiPrenotazione" required>
                    </div>
                    <hr>
                    <div class="divDatiPrenotazione">
                        <p><b>Cognome</b></p>
                        <input type="text" name="cognome" id="cognome" class="datiPrenotazione" required>
                    </div>
                    <hr>
                    <div class="divDatiPrenotazione">
                        <p><b>Check-in</b></p>
                        <input type="date" name="dataCheckIn" id="checkInData" class="datiPrenotazione" value = "<%=request.getParameter("check-in")%>" required>
                    </div>
                    <hr>
                    <div class="divDatiPrenotazione">
                        <p><b>Check-out</b></p>
                        <input type="date" name="dataCheckOut" id="checkOutData" class="datiPrenotazione" value = "<%=request.getParameter("check-out")%>" required>
                    </div>
                    <hr>
                    <div class="divDatiPrenotazione">
                        <p><b>Numero carta</b></p>
                        <input type="text" name="numeroCarta" id="numeroCarta" class="datiPrenotazione" minlength = "16" maxlength="16" required>
                    </div>
                    <hr>
                    <div class="divDatiPrenotazione">
                        <p><b>Data scadenza</b></p>
                        <input type="date" name="dataScadenzaCarta" id="dataScadenzaCarta" class="datiPrenotazione" required>
                    </div>
                    <hr>
                    <div class="divDatiPrenotazione">
                        <p><b>CVV</b></p>
                        <input type="text" name="cvvCarta" class="datiPrenotazione" minlength="3" maxlength="3" required>
                    </div>
                    <br>
                    <div class="divDatiPrenotazione">
                        <input type="submit" class="buttons" id = "buttonConferma" value="Conferma">
                        <button onclick="chiudiConferma()" class="buttons">Annulla</button>
                    </div>
                    <input type = "hidden" value = "<%=alloggio.getPrezzoNotte() * differenzaGiorni%>" id = "prezzoNotteCalcolato">
                    <input type="hidden" value="<%=alloggio.getNumeroAlloggio()%>" name="numAlloggio">
                    <input type="hidden" value="<%=alloggio.getStruttura().getIdStruttura()%>" name="codStruttura">
                    <input type="hidden" value="<%=request.getParameter("numOspiti")%>" name="numOspiti">

                </form>
            </div>

    <%}%>


    <script>
        function apriConferma() {

            let flag = false;

            <%
                if(utente != null) { %>
                    flag = true;
            <%}%>

            if(flag) {
                document.getElementById("divConfermaPrenotazione").style.display = "block";
            } else {
                document.getElementById("linkPrenota").onclick = window.location.href = '<%= jsp %>loginUtenteGUI.jsp';
            }
        }

        function chiudiConferma() {
            document.getElementById("divConfermaPrenotazione").style.display = "none";
        }

        document.getElementById("dataCheckIn").setAttribute("min", getTodayDate());

        function getNextDate() {
            let date = document.getElementById("dataCheckIn").value;

            let minDate = new Date(date);
            minDate.setDate(minDate.getDate() + 1); // Aggiunge 1 giorno
            document.getElementById("dataCheckOut").setAttribute("min", minDate.toISOString().split("T")[0]); // Restituisce la data in formato YYYY-MM-DD
        }

        function getTodayDate() {
            let today = new Date();
            let year = today.getFullYear();
            let month = String(today.getMonth() + 1).padStart(2, '0'); // Mese da 1 a 12
            let day = String(today.getDate()).padStart(2, '0'); // Giorno con due cifre
            return year + "-" + month + "-" + day;
        }
    </script>
</body>
</html>
