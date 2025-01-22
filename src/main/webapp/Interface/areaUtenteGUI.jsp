<%@ page import="Storage.Utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area utente</title>

    <link rel="stylesheet" href="css/areaUtente.css">
</head>
<body>
    <%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>

    <%
        Utente u = (Utente) request.getSession().getAttribute("utente");
    %>

    <div id="mainContainer">
        <div id="leftContainer">
            <div id="divAreaAccount"><a href="" id="linkAreaAccount">Area account</a></div>
            <div id="divPrenotazioni"><a href="" id="linkPrenotazioni">Storico prenotazioni</a></div>
        </div>
        <div id="rightContainer">
            <div id="topContainer">
                <p id="parAreaUtente" class="big-text">AREA UTENTE</p>
            </div>

            <div id="midContainer">
                <div id="divDatiUtente">
                    <%
                        if(u != null) {
                    %>

                            <div class="divDati">
                                <p class="parDati">Email</p>
                                <p><%=u.getEmail()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Nome</p>
                                <p><%=u.getNome()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Cognome</p>
                                <p><%=u.getCognome()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Città</p>
                                <p><%=u.getCitta()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Numero civico</p>
                                <p><%=u.getNumeroCivico()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Via</p>
                                <p><%=u.getVia()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Data nascita</p>
                                <p><%=u.getDataNascita()%></p>
                            </div>
                            <div class="divDati">
                                <p class="parDati">Recapito telefonico</p>
                                <p><%=u.getRecapitoTelefonico()%></p>
                            </div>

                    <%
                        }
                    %>
                </div>
            </div>

            <div id="bottomContainer">
                <a href="modificaDatiUtenteGUI.jsp" id="linkModificaDati">Modifica dati account ></a>
            </div>
        </div>
    </div>
</body>
</html>
