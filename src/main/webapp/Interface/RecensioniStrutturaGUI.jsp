<%@ page import="Storage.Recensione.Recensione" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 08/01/2025
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% if(callByServlet == 1) { %>
        <link rel="stylesheet" href="Interface/css/riepilogoStrutture.css">
        <link rel="stylesheet" href="Interface/css/footer.css">
        <link rel="stylesheet" href="Interface/css/header.css">
        <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
        <link rel="stylesheet" href="css/riepilogoStrutture.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/style.css">
    <% } %>
    <title>Recensioni Struttura</title>
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <%
        String nomeStruttura = (String) request.getAttribute("nomeStruttura");
        List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioniStruttura");
        Float media = (Float) request.getAttribute("mediaRecensioni");
    %>
    <div class="big-text" id="areaHost"> AREA HOST</div>

    <div class="sezione-aggiungi">
        <div class="mid-text" id="sezione" style="width: 83%;"><%= nomeStruttura %></div>
        <div class="button-sezione-aggiungi" id="mediaRecensioni">
            <div class="mediaRecensioni big-text">
                <% if(!media.isNaN()) { %>
                    <%= media %>
                <% } %>
            </div>
        </div>
    </div>

    <%  if(recensioni.isEmpty()) { %>
    <div class="nessuno normal-text">Non hai nessuna recensione :( </div>
    <%  }
    else { %>
    <div class="container" id="containerRecensioni">
        <% for(int i=0; i<recensioni.size(); i++) { %>
        <div class="rigaStruttura" id="rigaRecensioni">
            <div class="titoliRecensione">
                <div class="colonnaNormale normal-small-text"> <b>Numero Alloggio</b> </div>
                <div class="colonnaNormale normal-small-text"> <b>Data Recensione</b> </div>
                <div class="colonnaDescrizione normal-small-text"> <b>Descrizione</b> </div>
                <div class="colonnaNormale normal-small-text"> <b>E-mail recensore</b> </div>
                <div class="colonnaNormale normal-small-text"> <b>Voto Recensione</b> </div>
            </div>
            <div style="border: 1px solid var(--color-primary);"> </div>
            <div class="valoriRecensione">
                <div class="valoreNormale"> <span class="mid-text"> <%= recensioni.get(i).getAlloggio().getNumeroAlloggio() %> </span> </div>
                <div class="valoreNormale"> <span class="normal-small-text"> <%= recensioni.get(i).getDataRecensione() %> </span> </div>
                <div class="valoreDescrizione"> <span class="small-text"> <%= recensioni.get(i).getDescrizione() %> </span> </div>
                <div class="valoreNormale"> <span class="small-text"><%= recensioni.get(i).getUtente().getEmail() %> </span> </div>
                <div class="valoreNormale"> <span class="big-text" style="color: var(--color-primary);"> <%= recensioni.get(i).getVotoRecensione() %> </span> </div>
            </div>
        </div>
        <% } %>
    </div>
    <%  }  %>
</body>
</html>
