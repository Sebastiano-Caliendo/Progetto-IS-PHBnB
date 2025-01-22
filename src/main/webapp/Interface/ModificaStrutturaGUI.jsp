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
    <div class="formInserisci small-text">
        <div class="title mid-text"> Modifica la tua struttura! </div>
        <%
            String servlet = "";
            if(callByServlet == 0)
                servlet = "../";
        %>
        <form action="<%= servlet %>modificaStrutturaServlet" method="post" enctype="multipart/form-data">
            <% System.out.println("id struttura numero = " + struttura.getIdStruttura());%>
            <input type="hidden" value="<%= struttura.getIdStruttura() %>" name="idStruttura">
            <div class="input">
                <panel for="nomeStruttura"> Nome Struttura </panel>
                <input id="nomeStruttura" value="<%= struttura.getNomeStruttura() %>" type="text" name="nomeStruttura" required placeholder="Nome struttura">
            </div>

            <div class="input">
                <panel for="via"> Via </panel>
                <input id="via" value="<%= struttura.getVia() %>" type="text" name="via"  required placeholder="via..">
            </div>

            <div class="input">
                <panel for="citta"> Citta </panel>
                <input id="citta" value="<%= struttura.getCitta() %>" type="text" name="citta" required  placeholder="citta">
            </div>

            <div class="input">
                <panel for="numCivico"> Numero Civico </panel>
                <input id="numCivico" value="<%= struttura.getNumCivico() %>" type="text" name="numCivico" required  placeholder="numero civico">
            </div>

            <div class="input">
                <panel for="urlImmagine"> Carica un'immagine </panel>
                <input type="file" value="<%= struttura.getUrlImmagine() %>" id="urlImmagine" name="urlImmagine" style="background-color: white; width : 100%; align-content: center;" accept="image/*" required>
            </div>

            <div class="input">
                <panel for="descrizione"> Descrizione </b> </panel>
                <textarea id="descrizione" name="descrizione" rows="10" cols="50" placeholder="Scrivi qui la tua descrizione dettagliata..." required>
                    <%= struttura.getDescrizione() %>
                </textarea>
            </div>

            <input type="submit" value="Modifica Struttura" style="border : 2px solid var(--color-primary); width : 50%; height : 30px; color: black; background-color: var(--rosa-bianchissimo);">
        </form>
    </div>
    <div class="immagini">
        <!--<div class="riga1 small-text">
            <div class="ele2" style="padding: 5%; display: flex; align-items: center;">
                PHB&B è l'app ideale per prenotare alloggi in tutto il mondo, con una vasta selezione che include hotel, B&B e appartamenti. Grazie a una ricerca intuitiva e filtri avanzati, trovare l'alloggio perfetto è semplice e veloce.
            </div>
            <div class="ele1">
                <% if(callByServlet == 1) { %>
                <img src="Interface/img/struttura1.jpg">
                <% } else {%>
                <img src="img/strutture/struttura1.jpg">
                <% } %>
            </div>
        </div> -->
        <div class="riga2 small-text">
            <!-- <div class="ele2">
                <% if(callByServlet == 1) { %>
                <img src="Interface/img/struttura4.jpg">
                <% } else {%>
                <img src="img/strutture/struttura4.jpg">
                <% } %>
            </div>
            <div class="ele1" style="padding: 5%; display: flex; align-items: center;"> Un aspetto distintivo di PHB&B è la funzionalità "modifica struttura", che permette agli utenti di modificare facilmente le prenotazioni in caso di imprevisti, adattando la scelta all’ultimo minuto. Con conferme istantanee e recensioni verificate, PHB&B garantisce un'esperienza di viaggio senza stress, rendendo ogni soggiorno ancora più personalizzabile e conveniente.
            </div> -->
            <div class="img" id="imgPreview">
                <%
                    String urlImmagine = struttura.getUrlImmagine();
                    String replace = "Interface/";
                    if(callByServlet == 0) { // chiamata da jsp
                        urlImmagine = urlImmagine.replace(replace, "");
                    }
                    else  {  // chiamata da servlet

                    }
                %>
                <!--<div class="title-img" style="padding: 5%; display: flex; align-items: center; justify-content: center;"> Immagine inserita : </div> -->
                <img src="<%= urlImmagine %>">
            </div>
        </div>
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
