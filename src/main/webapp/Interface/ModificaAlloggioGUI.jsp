<%@ page import="Storage.Alloggio.AlloggioDAO" %>
<%@ page import="Storage.Alloggio.Alloggio" %><%--
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
    <title>Modifica Alloggio</title>
</head>
<body>
<%@ include file="../WEB-INF/moduli/header.jsp"%>

<%
    Alloggio alloggio = (Alloggio) request.getAttribute("alloggio");
    /*AlloggioDAO alloggioDAO = new AlloggioDAO();
    Alloggio alloggio = alloggioDAO.doRetrieveById(101, 1);*/
%>

<div class="img-form">
    <div class="formInserisci small-text">
        <div class="title mid-text"> Modifica il tuo alloggio! </div>
        <%
            String servlet = "";
            if(callByServlet == 0)
                servlet = "../";
        %>
        <form action="<%= servlet %>modificaAlloggioServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" value="<%= alloggio.getNumeroAlloggio() %>" name="oldNumeroAlloggio">
            <input type="hidden" value="<%= alloggio.getStruttura().getIdStruttura() %>" name="idStruttura">
            <div class="input">
                <panel for="numeroAlloggio"> Numero Alloggio  </panel>
                <input id="numeroAlloggio" value="<%= alloggio.getNumeroAlloggio() %>" type="number" name="numeroAlloggio" required>
            </div>

            <div class="input">
                <panel for="prezzoNotte"> Prezzo notte  </panel>
                <input id="prezzoNotte" value="<%= alloggio.getPrezzoNotte() %>" type="number" step="0.01" name="prezzoNotte"  required>
            </div>

            <div class="input">
                <panel for="numPostiLetto"> Numero posti letto  </panel>
                <input id="numPostiLetto" value="<%= alloggio.getPostiletto() %>" type="number" name="numPostiLetto" required>
            </div>

            <div class="input">
                <panel for="tipoAlloggio"> Tipo alloggio </panel>
                <input id="tipoAlloggio" value="<%= alloggio.getTipoAlloggio() %>" type="text" name="tipoAlloggio" required>
            </div>

            <div class="input">
                <panel for="urlImmagine"> Carica un'immagine </panel>
                <input type="file" value="<%= alloggio.getUrlImmagine() %>" id="urlImmagine" name="urlImmagine" style="background-color: white; width : 100%; align-content: center;" accept="image/*" required>
            </div>

            <div class="input">
                <panel for="descrizione"> Descrizione  </panel>
                <textarea id="descrizione" name="descrizione" rows="10" cols="50" placeholder="Scrivi qui la tua descrizione dettagliata..." required>
                    <%= alloggio.getDescrizione() %>
                </textarea>
            </div>

            <input type="submit" value="Modifica" style="border : 2px solid var(--color-primary); width : 50%; height : 30px; color: black; background-color: var(--rosa-bianchissimo);">
        </form>
    </div>
    <div class="immagini">
        <!-- <div class="riga1 small-text">
            <div class="ele2" style="padding: 5%; display: flex; align-items: center;">
                PHB&B ti offre la flessibilità di modificare il tuo alloggio in qualsiasi momento. Aggiungi nuovi dettagli, aggiorna prezzi e disponibilità o cambia le immagini per attrarre ancora più clienti.
            </div>
            <div class="ele1">
                <% if(callByServlet == 1) { %>
                <img src="Interface/img/alloggio3.jpg">
                <% } else {%>
                <img src="img/alloggi/alloggio3.jpg">
                <% } %>
            </div>
        </div> -->
        <div class="riga2 small-text">
            <!-- <div class="ele2">
                <% if(callByServlet == 1) { %>
                <img src="Interface/img/alloggio4.jpg">
                <% } else {%>
                <img src="img/alloggio4.jpg">
                <% } %>
            </div>
            <div class="ele1" style="padding: 5%; display: flex; align-items: center;"> Gestisci facilmente la tua offerta in base alle tue necessità, mantenendo sempre il controllo totale.
            </div> -->
            <div class="img" id="imgPreview">
                <%
                    String urlImmagine = alloggio.getUrlImmagine();
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
