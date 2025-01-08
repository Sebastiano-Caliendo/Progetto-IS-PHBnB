<%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 07/01/2025
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/inserisciStruttura.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Inserisci Struttura</title>
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <div class="img-form">
        <div class="immagini">
            <div class="riga1 small-text">
                <div class="ele1"> <img src="img/struttura2.jpg">   </div>
                <div class="ele2" style="padding: 5%; display: flex; align-items: center;">
                    PHB&B è l'app ideale per chi cerca un'esperienza di viaggio senza stress, offrendo un sistema semplice, veloce e sicuro per prenotare alloggi in tutto il mondo. Che tu stia cercando un hotel esclusivo, una camera accogliente in un B&B o un appartamento per un soggiorno prolungato, PHB&B ti aiuta a trovare l'alloggio perfetto per ogni tua esigenza.
                </div>
            </div>
            <div class="riga2 small-text">
                <div class="ele1" style="padding: 5%; display: flex; align-items: center;"> Puoi visualizzare foto dettagliate, leggere descrizioni precise e consultare la disponibilità in tempo reale. PHB&B ti offre anche conferme immediate e la possibilità di gestire tutte le tue prenotazioni con pochi semplici clic.
                </div>
                <div class="ele2"> <img src="img/struttura3.jpg">   </div>
            </div>
        </div>
        <div class="formInserisci">
            <div class="title mid-text"> Inserisci la tua struttura! </div>
            <form action="../aggiungiStrutturaServlet" method="post">
                <div class="input">
                    <!-- <panel for="nomeStruttura"> Nome Struttura : </panel> -->
                    <input id="nomeStruttura" type="text" name="nomeStruttura" required placeholder="Nome struttura">
                </div>

                <div class="input">
                    <!-- <panel for="via"> Via : </panel> -->
                    <input id="via" type="text" name="via"  required placeholder="via..">
                </div>

                <div class="input">
                    <!-- <panel for="citta"> Citta : </panel> -->
                    <input id="citta" type="text" name="citta" required  placeholder="citta">
                </div>

                <div class="input">
                    <!-- <panel for="numAlloggi"> Numero alloggi : </panel> -->
                    <input id="numAlloggi" type="number" name="numAlloggi" required  placeholder="Numero alloggi">
                </div>

                <div class="input">
                    <!-- <panel for="numAlloggi"> Numero alloggi : </panel> -->
                    <input id="urlImmagine" type="text" name="urlImmagine" required  placeholder="Url immagine">
                </div>

                <div class="input">
                    <!-- <panel for="descrizione"> Descrizione : </panel> -->
                    <textarea id="descrizione" name="descrizione" rows="10" cols="50" placeholder="Scrivi qui la tua descrizione dettagliata..." required></textarea>
                </div>

                <input type="submit" value="Aggiungi Struttura" style="border : 1px solid black;">
            </form>
        </div>
    </div>
</body>
</html>
