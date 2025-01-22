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
    <%
        int callByServlet = 0;
        String isCallByServlet = (String) request.getAttribute("callByServlet");
        if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
            callByServlet = 1;
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <% if(callByServlet == 1) { %>
    <link rel="stylesheet" href="Interface/css/inserisciStruttura.css">
    <link rel="stylesheet" href="Interface/css/footer.css">
    <link rel="stylesheet" href="Interface/css/header.css">
    <link rel="stylesheet" href="Interface/css/style.css">
    <%
    }
    else { %>
    <link rel="stylesheet" href="css/inserisciStruttura.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/style.css">
    <% } %>
    <title>Inserisci Struttura</title>
</head>
<body>
    <%@ include file="../WEB-INF/moduli/header.jsp"%>

    <div class="img-form">
        <div class="immagini">
            <!-- <div class="riga1 small-text">
                <div class="ele1">
                    <% if(callByServlet == 1) { %>
                    <img src="Interface/img/struttura2.jpg">
                    <% } else {%>
                    <img src="img/strutture/struttura2.jpg">
                    <% } %>
                </div>
                <div class="ele2" style="padding: 5%; display: flex; align-items: center;">
                    PHB&B è l'app ideale per chi cerca un'esperienza di viaggio senza stress, offrendo un sistema semplice, veloce e sicuro per prenotare alloggi in tutto il mondo. Che tu stia cercando un hotel esclusivo, una camera accogliente in un B&B o un appartamento per un soggiorno prolungato, PHB&B ti aiuta a trovare l'alloggio perfetto per ogni tua esigenza.
                </div>
            </div> -->
            <div class="riga2 small-text">
                <!--<div class="ele1" style="padding: 5%; display: flex; align-items: center;"> Puoi visualizzare foto dettagliate, leggere descrizioni precise e consultare la disponibilità in tempo reale. PHB&B ti offre anche conferme immediate e la possibilità di gestire tutte le tue prenotazioni con pochi semplici clic.
                </div> -->
                <!-- <div class="title-img" style="padding: 5%; display: flex; align-items: center; justify-content: center;"> Immagine inserita : </div> -->
                <!--<div class="ele2">
                    <% if(callByServlet == 1) { %>
                    <img src="Interface/img/struttura3.jpg">
                    <% } else {%>
                    <img src="img/strutture/struttura3.jpg">
                    <% } %>
                </div> -->
                <div class="img" id="imgPreview">
                    <!--<div class="title-img" style="padding: 5%; display: flex; align-items: center; justify-content: center;"> Immagine inserita : </div> -->
                    Nessuna immagine struttura inserita
                </div>
            </div>
        </div>
        <div class="formInserisci">
            <div class="title mid-text"> Inserisci la tua struttura! </div>
            <%
                String servlet = "";
                if(callByServlet == 0)
                    servlet = "../";
            %>
            <form action="<%= servlet %>aggiungiStrutturaServlet" method="post" enctype="multipart/form-data">
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
                    <!-- <panel for="citta"> Citta : </panel> -->
                    <input id="numCivico" type="text" name="numCivico" required  placeholder="numero civico">
                </div>

                <div class="input">
                    <!-- <panel for="numAlloggi"> Numero alloggi : </panel> -->
                    <!--<label for="file-upload">Carica un'immagine:</label> -->
                    <input type="file" id="urlImmagine" name="urlImmagine" accept="image/*" style="background-color: white; width : 60%; align-content: center;" required>
                </div>

                <div class="input">
                    <!-- <panel for="descrizione"> Descrizione : </panel> -->
                    <textarea id="descrizione" name="descrizione" rows="10" cols="50" placeholder="Scrivi qui la tua descrizione dettagliata..." required></textarea>
                </div>

                <input type="submit" value="Aggiungi Struttura" style="border : 2px solid var(--color-primary); width : 50%; height : 30px; color: black; background-color: var(--rosa-bianchissimo);">
            </form>
        </div>
    </div>

    <script>
        // Funzione per aggiornare l'anteprima dell'immagine selezionata
        document.getElementById('urlImmagine').addEventListener('change', function(event) {
            var reader = new FileReader();  // FileReader per leggere il file selezionato

            // Quando il file è stato letto, aggiorniamo l'elemento con l'immagine
            reader.onload = function(e) {
                var imgElement = document.createElement('img');  // Creiamo un nuovo tag <img>
                imgElement.src = e.target.result;  // Impostiamo la sorgente dell'immagine
                imgElement.style.maxWidth = '100%';  // Impostiamo una larghezza massima per evitare il ridimensionamento eccessivo
                imgElement.style.height = 'auto';  // Altezza automatica per mantenere le proporzioni

                // Sostituiamo il contenuto di #imgPreview con la nuova immagine
                var imgPreviewDiv = document.getElementById('imgPreview');
                imgPreviewDiv.innerHTML = '';  // Rimuoviamo il testo "immagine struttura inserita"
                imgPreviewDiv.appendChild(imgElement);  // Aggiungiamo la nuova immagine
            };

            // Leggiamo il file selezionato come URL di dati
            reader.readAsDataURL(event.target.files[0]);
        });
    </script>


</body>
</html>
