<%@ page import="Storage.Occupa.Occupa" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Storage.Utente.Utente" %>
<%@ page import="Storage.Prenotazione.Prenotazione" %>
<html>
<head>
    <title>Storico prenotazioni</title>

    <%
        String error = request.getParameter("error");

        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;

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
    } else { %>
        <link rel="stylesheet" href="css/areaUtente.css">
        <link rel="stylesheet" href="css/headerDopoAccesso.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/style.css">
    <% } %>

</head>
<body style="overflow-y: auto; height: 100%;">

<%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>

<%
    List<Occupa> prenotazioni = (List<Occupa>) session.getAttribute("prenotazioni");
    List<Integer> prenotazioniRecensite = (List<Integer>) request.getAttribute("codiciPrenotazioniRecensite");

    //if(error != null && error.equals("1")) {%>
        <!--<script>alert("Prenotazione non modificata!")</script>
    <%//} else if(error != null && error.equals("2")) {%>
        <script>alert("Prenotazione non eliminata!")</script> -->
    <%//}

    String scontrino = request.getParameter("scontrino");


    if(scontrino != null && scontrino.equals("true")) {%>
        <div id="divVisualizzaScontrino">
            <p class="normal-small-text" style="text-align: center; margin-top: 10%;">Prenotazione effettuata con successo!</p>
            <button class="buttons" onclick="generatePDF()">Visualizza</button>
            <button class="buttons" onclick="window.location.href = '<%= jsp %>visualizzaStoricoPrenotazioniGUI.jsp'">Chiudi</button>
        </div>
    <%}%>

<div id="mainContainer">
    <div id="leftContainer">
        <div id="divAreaAccount"><a href="<%= jsp %>areaUtenteGUI.jsp" id="linkAreaAccount" class="normal-small-text">Area account</a></div>
        <div id="divPrenotazioni"><a href="" id="linkPrenotazioni" class="normal-small-text">Storico prenotazioni</a></div>
    </div>
    <div id="rightContainer">

        <div id="prContainer">


            <%
                int i = 0;
                if(prenotazioni != null) {

                    for(Occupa pr : prenotazioni) {%>

                        <div class="divPrenotazione">
                            <div class="primaRiga">
                            <div class="divInfoPrenotazione">
                                    <p class="parDatiPr normal-small-text"><b><%=pr.getAlloggio().getStruttura().getNomeStruttura()%></b></p>
                                    <p class="parDatiPr small-text"><b>Numero alloggio: </b><%=pr.getAlloggio().getNumeroAlloggio()%></p>
                                    <p class="parDatiPr small-text"><b>Check-in: </b><%=pr.getPrenotazione().getCheckIn()%></p>
                                    <p class="parDatiPr small-text"><b>Check-out: </b><%=pr.getPrenotazione().getCheckOut()%></p>
                                    <p class="parDatiPr small-text"><b>Numero persone: </b><%=pr.getPrenotazione().getNumeroPersone()%></p>
                                    <p class="parDatiPr small-text"><b>Costo: </b><%=pr.getCostoPrenotazione()%> €</p>
                                </div>

                                <%
                                    String urlImmagine = pr.getAlloggio().getStruttura().getUrlImmagine();
                                    String replace = "Interface/";
                                    if(callByServlet == 0) { // chiamata da jsp
                                        urlImmagine = urlImmagine.replace(replace, "");
                                    }
                                    else  {  // chiamata da servlet

                                    }
                                %>

                                <div class="divImg"><img src="<%=urlImmagine%>" alt="img struttura" class="imgStruttura"></div>
                            </div>
                            <div class="divButtons small-text">
                                <%

                                    LocalDate dataAttuale = LocalDate.now();

                                    if(dataAttuale.plusDays(7).isBefore(pr.getPrenotazione().getCheckIn())) { %>
                                        <a href="#" class="buttons" onclick="abilitaModifica(<%=i%>, <%=pr.getPrenotazione().getCodicePrenotazione()%>, <%=pr.getAlloggio().getNumeroAlloggio()%>, <%=pr.getAlloggio().getStruttura().getIdStruttura()%>)">Modifica</a>
                                        <a href="#" class="buttons" onclick="confermaCancellazione(<%=pr.getPrenotazione().getCodicePrenotazione()%>)">Elimina</a>
                                    <%}%>

                                    <%if(dataAttuale.isAfter(pr.getPrenotazione().getCheckOut()) && prenotazioniRecensite != null && !prenotazioniRecensite.contains(pr.getPrenotazione().getCodicePrenotazione())) {%>
                                        <a href="<%= jsp %>inserisciRecensioneGUI.jsp?codicePrenotazione=<%=pr.getPrenotazione().getCodicePrenotazione()%>&idStruttura=<%=pr.getAlloggio().getStruttura().getIdStruttura()%>&numeroAlloggio=<%=pr.getAlloggio().getNumeroAlloggio()%>" class="buttons">Recensisci</a>
                                    <%}
                                        i++;
                                    %>
                            </div>
                        </div>
            <%}}%>
        </div>
    </div>
</div>

<div id="divModificaPrenotazione" style="display: none">
    <form id="formModificaPrenotazione" action="<%= servlet %>modificaPrenotazione">
        <div class="divModPrenotazione small-text">
            <b>Check-in</b>
        </div>
        <div class="divModPrenotazione">
            <input type="date" name="dataCheckIn" required>
        </div>
        <div class="divModPrenotazione small-text">
            <b>Check-out</b>
        </div>
        <div class="divModPrenotazione">
            <input type="date" name="dataCheckOut" required>
        </div>
        <div class="divModPrenotazione small-text">
            <b>Numero posti</b>
        </div>
        <div class="divModPrenotazione">
            <input type="text" name="numPostiLetto" required>
        </div>
        <div class="divModPrenotazione">
            <input class="buttons" type="submit" value="Conferma">
        </div>
        <div class = "divModPrenotazione">
            <input class = "buttons" type = "submit" value = "Chiudi">
        </div>
    </form>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.3.1/jspdf.umd.min.js"></script>

<script>
    async function generatePDF() {

        <%
            if (prenotazioni != null && !prenotazioni.isEmpty()) {
                Occupa pr = prenotazioni.getLast();
        %>

        const { jsPDF } = window.jspdf;
        const pdf = new jsPDF();

        // Centrare il titolo
        const title = "Scontrino elettronico";
        const pageWidth = pdf.internal.pageSize.getWidth();
        const titleWidth = pdf.getTextWidth(title);
        const centerX = (pageWidth - titleWidth) / 2;

        pdf.setFont("helvetica", "bold");
        pdf.setFontSize(16);
        pdf.text(title, centerX, 10);

        pdf.setFont("arial", "normal");
        pdf.setFontSize(12);

        pdf.setFillColor(255, 244, 246);
        pdf.setDrawColor(184, 134, 11);
        pdf.setLineWidth(0.5);
        pdf.roundedRect(15, 25, 180, 60, 5, 5);

        pdf.setFont("helvetica", "bold");
        pdf.setTextColor(184, 134, 11);
        pdf.text(20, 35, `Dettagli anagrifici`);
        pdf.setFontSize(12);
        pdf.setTextColor(0, 0, 0);

        pdf.text(20, 45, `Nome: ` + "<%=pr.getPrenotazione().getNomeIntestatario()%>");
        pdf.text(20, 55, `Cognome: ` + "<%=pr.getPrenotazione().getCognomeIntestatario()%>");
        pdf.text(20, 65, `Numero Carta: ` + "<%=pr.getPrenotazione().getNumeroCartaCredito()%>");
        pdf.text(20, 75, `Data di Scadenza: ` + "<%=pr.getPrenotazione().getDataScadenzaCarta()%>");

        pdf.setFont("helvetica", "bold");
        pdf.setTextColor(184, 134, 11);
        pdf.text(20, 100, `Dettagli prenotazione`);
        pdf.roundedRect(15, 90, 180, 90, 5, 5);
        pdf.setTextColor(0, 0, 0);

        pdf.text(20, 110, `Data Check In: ` + "<%=pr.getPrenotazione().getCheckIn()%>");
        pdf.text(20, 120, `Data Check Out: ` + "<%=pr.getPrenotazione().getCheckOut()%>");
        pdf.text(20, 130, `Nome struttura: ` + "<%=pr.getAlloggio().getStruttura().getNomeStruttura()%>");
        pdf.text(20, 140, `Indirizzo: ` + "<%=pr.getAlloggio().getStruttura().getVia()%> " + "<%=pr.getAlloggio().getStruttura().getNumCivico()%> " + "<%=pr.getAlloggio().getStruttura().getCitta()%>");
        pdf.text(20, 150, `Numero alloggio: ` + "<%=pr.getAlloggio().getNumeroAlloggio()%>");
        pdf.text(20, 160, `Prezzo totale prenotazione: ` + "<%=pr.getCostoPrenotazione()%>");

        pdf.save('scontrino.pdf');
    }




    <% } %>

    function confermaCancellazione(codPrenotazione) {

        var cancellazioneDiv = document.createElement("div");

        cancellazioneDiv.id = "cancellazioneDiv";
        cancellazioneDiv.style.display = "flex";
        cancellazioneDiv.style.flexDirection = "column";
        cancellazioneDiv.style.position = "fixed";
        cancellazioneDiv.style.height = "20%";
        cancellazioneDiv.style.width = "20%";
        cancellazioneDiv.style.background = "white";
        cancellazioneDiv.style.top = "50%";
        cancellazioneDiv.style.left = "50%";
        cancellazioneDiv.style.transform = 'translate(-50%, -50%)';
        cancellazioneDiv.style.border = "2px solid darkgoldenrod";

        var divTesto = document.createElement("div");
        var divBottoni = document.createElement("div");

        divTesto.style.height = "50%";
        divTesto.textContent = "Intendi cancellare questa prenotazione?";
        divTesto.style.textAlign = "center";
        divTesto.style.alignContent = "center";

        divBottoni.style.height = "50%";
        divBottoni.style.display = "flex";
        divBottoni.style.flexDirection = "row";

        var buttonSi = document.createElement("button");
        var buttonNo = document.createElement("button");

        buttonSi.textContent = "Si";
        buttonSi.style.margin = "auto";
        buttonSi.style.background = "darkgoldenrod"
        buttonSi.style.color = "white"
        buttonSi.style.height = "40%";
        buttonSi.style.width = "20%";
        //associa al button il percorso della pagine jsp da raggiungere dopo il click
        buttonSi.addEventListener("click", function() {
            window.location.href = "<%= servlet %>eliminaPrenotazione?codPrenotazione=" + codPrenotazione;
        });

        buttonNo.textContent = "No";
        buttonNo.style.margin = "auto";
        buttonNo.style.background = "darkgoldenrod";
        buttonNo.style.color = "white";
        buttonNo.style.height = "40%";
        buttonNo.style.width = "20%";
        buttonNo.addEventListener("click", chiudiFinestra);

        divBottoni.appendChild(buttonSi);
        divBottoni.appendChild(buttonNo);

        cancellazioneDiv.appendChild(divTesto);
        cancellazioneDiv.appendChild(divBottoni);

        document.body.appendChild(cancellazioneDiv);
    }

    function chiudiFinestra() {
        var cancellazioneDiv = document.getElementById("cancellazioneDiv");
        cancellazioneDiv.style.display = "none";
    }

    function abilitaModifica(indice, codPrenotazione, numAlloggio, idStruttura) {

        //oscuro i tasti modifica e elimina
        //document.getElementsByClassName("divButtons")[indice].style.display = "none";

        //rendo visibile il form di modifica
        var divModifica = document.getElementById("divModificaPrenotazione");
        divModifica.style.display = "flex";
        divModifica.style.flexDirection = "column";

        //setto il codice prenotazione come attributo del form da inviare alla servlet
        var inputCodPrenotazione = document.createElement("input");
        var inputNumAlloggio = document.createElement("input");
        var inputIdStruttura = document.createElement("input");

        inputCodPrenotazione.type = "text";
        inputCodPrenotazione.hidden = true;
        inputCodPrenotazione.value = codPrenotazione;
        inputCodPrenotazione.name = "codPrenotazione";

        inputNumAlloggio.type = "text";
        inputNumAlloggio.hidden = true;
        inputNumAlloggio.value = numAlloggio;
        inputNumAlloggio.name = "numAlloggio";

        inputIdStruttura.type = "text";
        inputIdStruttura.hidden = true;
        inputIdStruttura.value = idStruttura;
        inputIdStruttura.name = "idStruttura";

        document.getElementById("formModificaPrenotazione").appendChild(inputCodPrenotazione);
        document.getElementById("formModificaPrenotazione").appendChild(inputNumAlloggio);
        document.getElementById("formModificaPrenotazione").appendChild(inputIdStruttura);


        //lo appendo alla prenotazione da modificare
        var divPrenotazione = document.getElementsByClassName("divPrenotazione")[indice];
        divPrenotazione.appendChild(divModifica);
    }

    document.addEventListener("keydown", function (event){
        if(event.key === "Escape"){
            document.getElementById("divModificaPrenotazione").style.display = "none";
        }
    });
</script>
</body>
</html>