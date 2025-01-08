<%@ page import="Storage.Struttura.StrutturaDAO" %>
<%@ page import="Storage.Struttura.Struttura" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 07/01/2025
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Interface/css/inserisciStruttura.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <title>Modifica Struttura</title>
</head>
<body>
<%@ include file="../WEB-INF/moduli/header.jsp"%>

<%
    Struttura struttura = (Struttura) request.getAttribute("struttura");
    /*StrutturaDAO strutturaDAO = new StrutturaDAO();
    Struttura struttura = strutturaDAO.doRetrieveById(1);*/
%>

<div class="img-form">
    <div class="formInserisci">
        <div class="title mid-text"> Modifica la tua struttura! </div>
        <form action="../Progetto_IS_PHBnB_war_exploded/modificaStrutturaServlet" method="post">
            <input type="hidden" value="<%= struttura.getIdStruttura() %>" name="oldIdStruttura">
            <div class="input">
                <panel for="nomeStruttura"> <b>Nome Struttura :</b> </panel>
                <input id="nomeStruttura" value="<%= struttura.getNomeStruttura() %>" type="text" name="nomeStruttura" required placeholder="Nome struttura">
            </div>

            <div class="input">
                <panel for="via"> <b>Via :</b> </panel>
                <input id="via" value="<%= struttura.getVia() %>" type="text" name="via"  required placeholder="via..">
            </div>

            <div class="input">
                <panel for="citta"> <b>Citta :</b> </panel>
                <input id="citta" value="<%= struttura.getCitta() %>" type="text" name="citta" required  placeholder="citta">
            </div>

            <div class="input">
                <panel for="numAlloggi"> <b>Numero alloggi : </b></panel>
                <input id="numAlloggi" value="<%= struttura.getNumAlloggi() %>" type="number" name="numAlloggi" required  placeholder="Numero alloggi">
            </div>

            <div class="input">
                <panel for="urlImmagine"> <b>urlImmagine : </b></panel>
                <input id="urlImmagine" value="<%= struttura.getUrlImmagine() %>" type="text" name="urlImmagine" required  placeholder="Url immagine">
            </div>

            <div class="input">
                <panel for="descrizione"> <b>Descrizione :</b> </panel>
                <textarea id="descrizione" name="descrizione" rows="10" cols="50" placeholder="Scrivi qui la tua descrizione dettagliata..." required>
                    <%= struttura.getDescrizione() %>
                </textarea>
            </div>

            <input type="submit" value="Modifica Struttura" style="border : 1px solid black;">
        </form>
    </div>
    <div class="immagini">
        <div class="riga1 small-text">
            <div class="ele2" style="padding: 5%; display: flex; align-items: center;">
                PHB&B è l'app ideale per prenotare alloggi in tutto il mondo, con una vasta selezione che include hotel, B&B e appartamenti. Grazie a una ricerca intuitiva e filtri avanzati, trovare l'alloggio perfetto è semplice e veloce.
            </div>
            <div class="ele1"> <img src="img/struttura1.jpg">   </div>
        </div>
        <div class="riga2 small-text">
            <div class="ele2"> <img src="img/struttura4.jpg">   </div>
            <div class="ele1" style="padding: 5%; display: flex; align-items: center;"> Un aspetto distintivo di PHB&B è la funzionalità "modifica struttura", che permette agli utenti di modificare facilmente le prenotazioni in caso di imprevisti, adattando la scelta all’ultimo minuto. Con conferme istantanee e recensioni verificate, PHB&B garantisce un'esperienza di viaggio senza stress, rendendo ogni soggiorno ancora più personalizzabile e conveniente.
            </div>
        </div>
    </div>
</div>
</body>
</html>
