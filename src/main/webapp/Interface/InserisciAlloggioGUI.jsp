<%@ page import="Storage.Struttura.StrutturaDAO" %>
<%@ page import="Storage.Struttura.Struttura" %><%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 08/01/2025
  Time: 09:58
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

    <title>Inserisci Alloggio</title>
</head>
<body>
<%@ include file="../WEB-INF/moduli/header.jsp"%>

<%
    String idStruttura = (String) request.getAttribute("idStruttura");
    /*StrutturaDAO strutturaDAO = new StrutturaDAO();
    Struttura struttura = strutturaDAO.doRetrieveById(1);
    int idStruttura = struttura.getIdStruttura();*/
%>

<div class="img-form">
    <div class="immagini">
        <!--<div class="riga1 small-text">
            <div class="ele1">
                <% if(callByServlet == 1) { %>
                    <img src="Interface/img/alloggio1.jpg">
                <% } else {%>
                    <img src="img/alloggi/alloggio1.jpg">
                <% } %>
            </div>
            <div class="ele2" style="padding: 5%; display: flex; align-items: center;">
                Con PHB&B, inserire il tuo alloggio è facile e veloce! Raggiungi milioni di potenziali ospiti,
            </div>
        </div> -->
        <div class="riga2 small-text">
            <!--<div class="ele1" style="padding: 5%; display: flex; align-items: center;"> aumenta la visibilità della tua struttura e inizia a ricevere prenotazioni in pochi semplici passaggi. Fai crescere la tua attività e sfrutta la potenza di una piattaforma globale.
            </div> -->
            <!--<div class="ele2">
                <% if(callByServlet == 1) { %>
                    <img src="Interface/img/alloggio2.jpg">
                <% } else {%>
                    <img src="img/alloggi/alloggio2.jpg">
                <% } %>
            </div> -->
            <div class="img" id="imgPreview">
                <!--<div class="title-img" style="padding: 5%; display: flex; align-items: center; justify-content: center;"> Immagine inserita : </div> -->
                Nessuna immagine alloggio inserita
            </div>
        </div>
    </div>
    <div class="formInserisci">
        <div class="title mid-text"> Inserisci il tuo alloggio! </div>
        <%
            String servlet = "";
            if(callByServlet == 0)
                servlet = "../";
        %>
        <form action="<%= servlet %>aggiungiAlloggioServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" value="<%= idStruttura %>" name="idStruttura">
            <div class="input">
                <!-- <panel for="numeroAlloggio"> Numero Alloggio : </panel> -->
                <input id="numeroAlloggio" type="number" name="numeroAlloggio" required placeholder="Numero Alloggio">
            </div>

            <div class="input">
                <!-- <panel for="prezzoNotte"> Prezzo Notte : </panel> -->
                <input id="prezzoNotte" type="number" step="0.01" name="prezzoNotte"  required placeholder="Prezzo Notte">
            </div>

            <div class="input">
                <!-- <panel for="numPostiLetto"> Numero posti letto : </panel> -->
                <input id="numPostiLetto" type="number" name="numPostiLetto" required  placeholder="Numero posti letto">
            </div>

            <div class="input">
                <!-- <panel for="tipoAlloggio"> Tipo alloggio : </panel> -->
                <input id="tipoAlloggio" type="text" name="tipoAlloggio" required  placeholder="Tipo alloggio">
            </div>

            <div class="input">
                <!--<label for="file-upload">Carica un'immagine:</label> -->
                <input type="file" id="urlImmagine" name="urlImmagine" accept="image/*" style="background-color: white; width : 60%; align-content: center;" required>
            </div>

            <div class="input">
                <!-- <panel for="descrizione"> Descrizione : </panel> -->
                <textarea id="descrizione" name="descrizione" rows="10" cols="50" placeholder="Scrivi qui la tua descrizione dettagliata..." required></textarea>
            </div>

            <input type="submit" value="Aggiungi" style="border : 2px solid var(--color-primary); width : 50%; height : 30px; color: black; background-color: var(--rosa-bianchissimo);">
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
                imgElement.style.borderRadius = '20px';

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
