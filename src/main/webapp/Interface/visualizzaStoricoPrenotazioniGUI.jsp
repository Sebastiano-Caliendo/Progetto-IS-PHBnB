<%@ page import="Storage.Occupa.Occupa" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Storage.Utente.Utente" %>
<html>
<head>
    <title>Storico prenotazioni</title>

    <%
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
    }
    else { %>
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
    List<Occupa> prenotazioni = (List<Occupa>) request.getAttribute("prenotazioni");
    List<Integer> prenotazioniRecensite = (List<Integer>) request.getAttribute("codiciPrenotazioniRecensite");
%>

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
                            <div class="divInfoPrenotazione">
                                <p class="parDatiPr normal-small-text"><b><%=pr.getAlloggio().getStruttura().getNomeStruttura()%></b></p>
                                <p class="parDatiPr small-text"><b>Numero alloggio: </b><%=pr.getAlloggio().getNumeroAlloggio()%></p>
                                <p class="parDatiPr small-text"><b>Check-in: </b><%=pr.getPrenotazione().getCheckIn()%></p>
                                <p class="parDatiPr small-text"><b>Check-out: </b><%=pr.getPrenotazione().getCheckOut()%></p>
                                <p class="parDatiPr small-text"><b>Numero persone: </b><%=pr.getPrenotazione().getNumeroPersone()%></p>
                                <p class="parDatiPr small-text"><b>Costo: </b><%=pr.getCostoPrenotazione()%> €</p>
                            </div>
                            <div class="divImg"><img src="<%=pr.getAlloggio().getStruttura().getUrlImmagine()%>" alt="img struttura" class="imgStruttura"></div>
                            <div class="divButtons ">
                                <%

                                    LocalDate dataAttuale = LocalDate.now();

                                    if(dataAttuale.plusDays(7).isBefore(pr.getPrenotazione().getCheckIn())) { %>
                                        <a href="#" class="buttons" onclick="abilitaModifica(<%=i%>, <%=pr.getPrenotazione().getCodicePrenotazione()%>)">Modifica</a>
                                        <a href="#" class="buttons" onclick="confermaCancellazione(<%=pr.getPrenotazione().getCodicePrenotazione()%>)">Elimina</a>
                                    <%}%>

                                    <%if(dataAttuale.isAfter(pr.getPrenotazione().getCheckOut()) && !prenotazioniRecensite.contains(pr.getPrenotazione().getCodicePrenotazione())) {%>
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
    </form>
</div>


<script>
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
            window.location.href = "eliminaPrenotazione?codPrenotazione=" + codPrenotazione;
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

    function abilitaModifica(indice, codPrenotazione) {

        //oscuro i tasti modifica e elimina
        document.getElementsByClassName("divButtons")[indice].style.display = "none";

        //rendo visibile il form di modifica
        var divModifica = document.getElementById("divModificaPrenotazione");
        divModifica.style.display = "flex";
        divModifica.style.flexDirection = "column";

        //setto il codice prenotazione come attributo del form da inviare alla servlet
        var inputCodPrenotazione = document.createElement("input");
        inputCodPrenotazione.type = "text";
        inputCodPrenotazione.hidden = true;
        inputCodPrenotazione.value = codPrenotazione;
        inputCodPrenotazione.name = "codPrenotazione";

        document.getElementById("formModificaPrenotazione").appendChild(inputCodPrenotazione);


        //lo appendo alla prenotazione da modificare
        var divPrenotazione = document.getElementsByClassName("divPrenotazione")[indice];
        divPrenotazione.appendChild(divModifica);
    }
</script>
</body>
</html>