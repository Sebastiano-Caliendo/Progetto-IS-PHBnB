<%@ page import="java.util.List" %>
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
    <link rel="stylesheet" href="Interface/css/alloggiPrenotabili.css">
    <link rel="stylesheet" href="Interface/css/index.css">
    <link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/alloggiPrenotabili.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/headerDopoAccesso.css">
    <link rel="stylesheet" href="css/footer.css">
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
    %>

    <div id="topContainer">
        <%
            Utente utente = (Utente) session.getAttribute("utente");
            Host host = (Host) session.getAttribute("host");
            Utente admin = (Utente) session.getAttribute("admin");

            boolean loggato = false;
            if(utente == null && host == null && admin == null)
                loggato = false;
            else
                loggato = true;

            List<Alloggio> alloggi = (List<Alloggio>) request.getAttribute("alloggiPrenotabili");

            if(loggato) { %>

                <%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>

        <%} else { %>

        <div id="divLogo">
            <p id="parLogo" class="mid-text"><a href="<%= jsp %>index.jsp" class="logoHref">PHB&B</a></p>
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

        <%
            }
        %>
    </div>
    <div id="midContainer">
        <div id="containerFiltri">
            <div id="divFiltroLuogo">
                <p class="small-text"><b>Dove vuoi andare?</b></p>
                <input form="formRicerca" type="text" name="luogo" placeholder="Cerca destinazioni" class="small-text" style="width: 80%;" required>
            </div>
            <div id="divFiltroDate">
                <div id="divCheckIn">
                    <p class="small-text"><b>Check-in</b></p>
                    <input form="formRicerca" type="date" name="dataCheckIn" id="dataCheckIn" style="width:98%;" required onchange="getNextDate()">
                </div>
                <div id="divCheckOut">
                    <p class="small-text"><b>Check-out</b></p>
                    <input form="formRicerca" type="date" name="dataCheckOut" id="dataCheckOut" style="width:98%;" required>
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
    <div id="alloggiContainer">
        <%
            if(alloggi != null) {
                for(Alloggio alloggio : alloggi) { %>

                    <div class="divAlloggio">
                        <div class="divImg">
                            <img class="imgAlloggio" src="<%=alloggio.getUrlImmagine()%>">
                        </div>
                        <div class="divInfoAlloggio">
                            <div class="divNomeStruttura">
                                <a href="<%= servlet %>visualizzaDettagliAlloggio?numAlloggio=<%=alloggio.getNumeroAlloggio()%>&codStruttura=<%=alloggio.getStruttura().getIdStruttura()%>&check-in=<%=request.getAttribute("dataCheckIn")%>&check-out=<%=request.getAttribute("dataCheckOut")%>&numOspiti=<%=request.getAttribute("numOspiti")%>" class="linkDettagliAlloggio"><b><%=alloggio.getStruttura().getNomeStruttura()%> ></b></a>
                            </div>
                            <div class="divDescrizione">
                                <p class="small-text"><%=alloggio.getDescrizione()%></p>
                            </div>
                        </div>
                    </div>
        <%}}%>
    </div>

    <script>
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