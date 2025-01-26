<%@ page import="java.util.List" %>
<%@ page import="Storage.Alloggio.Alloggio" %>
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
    <link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/headerDopoAccesso.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/style.css">
    <% } %>

</head>
<body>

    <div id="topContainer">
        <%
            List<Alloggio> alloggi = (List<Alloggio>) request.getAttribute("alloggiPrenotabili");

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
    <div id="alloggiContainer">
        <%
            if(alloggi != null) {
                for(Alloggio a : alloggi) { %>

                    <div class="divAlloggio">
                        <div class="divImg">
                            <img class="imgAlloggio" src="<%=a.getUrlImmagine()%>">
                        </div>
                        <div class="divInfoAlloggio">
                            <div class="divNomeStruttura">
                                <a href="" class="linkDettagliAlloggio"><b><%=a.getStruttura().getNomeStruttura()%> ></b></a>
                            </div>
                            <div class="divDescrizione">
                                <p><%=a.getDescrizione()%></p>
                            </div>
                        </div>
                    </div>
        <%}}%>
    </div>
</body>
</html>