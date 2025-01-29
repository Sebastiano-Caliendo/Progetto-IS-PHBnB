<%@ page import="Storage.Utente.Utente" %>
<%@ page import="Storage.Host.Host" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>PHB&B</title>

  <link rel="stylesheet" href="css/index.css">
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div id="topContainer">
      <div id="divLogo">
        <p id="parLogo" class="mid-text">PHB&B</p>
      </div>
      <div id="divRegStruttura">
        <a id="linkRegStruttura" href="registrazioneHostGUI.jsp" class="normal-small-text">Registra la tua struttura</a>
      </div>
      <div id="divReg">
        <button id="linkReg" onclick="window.location.href='registrazioneUtenteGUI.jsp';" class="small-text buttons">Registrati</button>
      </div>
      <div id="divAccedi">
        <button id="linkAccedi" onclick="apriSceltaLogin()" class="small-text buttons">Accedi</button>
      </div>
    </div>
    <div id="midContainer">
      <div id="containerFiltri">
        <div id="divFiltroLuogo">
          <p class="small-text"><b>Dove vuoi andare?</b></p>
          <input form="formRicerca" type="text" name="luogo" placeholder="Cerca destinazioni" class="small-text" style="width: 80%;">
        </div>
        <div id="divFiltroDate">
          <div id="divCheckIn">
            <p class="small-text"><b>Check-in</b></p>
            <input form="formRicerca" type="date" name="dataCheckIn" style="width: 98%;">
          </div>
          <div id="divCheckOut">
            <p class="small-text"><b>Check-out</b></p>
            <input form="formRicerca" type="date" name="dataCheckOut" style="width: 98%;">
          </div>
        </div>
        <div id="divFiltroOspiti">
          <p class="small-text"><b>Chi?</b></p>
          <input form="formRicerca" type="text" name="numOspiti" placeholder="Aggiungi ospiti" class="small-text" style="width: 90%;">
        </div>
        <div id="divCerca">
          <form id="formRicerca" action="../visualizzaAlloggi">
            <input type="submit" value="Cerca" id="buttonCerca" class="normal-small-text">
          </form>
        </div>
      </div>
    </div>
    <div id="bottomContainer">
      <!--<img class="imgHome" src="img/imgHome1.jpg">-->
      <div class="descrizioneIndex">
        <!--<div class="titleDescrizione mid-text"> PRENOTA L'ALLOGGIO PERFETTO !!!</div>-->
        <div class="mediaDescrizione normal-small-text"> <b>Con PHB&B cerca tra una marea di destinazioni !</b></div>
        <div class="piccolaDescrizione small-text"> PHB&B è un sito web intuitivo e facile da usare, progettato per prenotare alloggi in diverse destinazioni. Con una vasta gamma di opzioni, dai bed and breakfast alle case vacanza, permette agli utenti di trovare rapidamente la sistemazione ideale in base alle proprie preferenze e budget. La piattaforma offre una ricerca semplice, filtri avanzati e recensioni di altri viaggiatori per garantire una scelta informata. Inoltre,
          la prenotazione è sicura e veloce, rendendo il processo di organizzazione del viaggio molto più comodo.</div>
      </div>
      <img class="imgHome" src="img/imgHome2.jpg">
      <img class="imgHome" src="img/imgHome3.jpg">
    </div>

    <div id="divSceltaLogin" style="display: none">
      <div id = "formSceltaLogin">
        <div class="divSceltaLogin">
          <p><b>Seleziona la tipologia di accesso</b></p>
        </div>
        <div class="divSceltaLogin">
          <input type="submit" onclick="reindirizzaUtente()" class="buttons" value = "Utente">
          <input type="submit" onclick="reindirizzaHost()" class="buttons" value = "Host">
          <input type="submit" onclick="reindirizzaAdmin()" class="buttons" value = "Admin">
        </div>
        <!--<div class="divDatiPrenotazione">
          <p><b>Check-in</b></p>
          <input type="date" name="dataCheckIn" class="datiPrenotazione">
        </div>
        <div class="divDatiPrenotazione">
          <p><b>Check-out</b></p>
          <input type="date" name="dataCheckOut" class="datiPrenotazione">
        </div>
        <div class="divDatiPrenotazione">
          <p><b>Numero carta</b></p>
          <input type="text" name="numeroCarta" class="datiPrenotazione">
        </div>
        <div class="divDatiPrenotazione">
          <p><b>Data scadenza</b></p>
          <input type="date" name="dataScadenzaCarta" class="datiPrenotazione">
        </div>
        <div class="divDatiPrenotazione">
          <p><b>CVV</b></p>
          <input type="text" name="cvvCarta" class="datiPrenotazione">
        </div>-->
      </div>
    </div>

<!--index momentanea, dal riepilogo strutture possiamo accedere a tutta la parte relativa a : GESTIONE STRUTTURE e ALLOGGI
<a href="RiepilogoStruttureGUI.jsp">riepilogoStruttre</a>
 <a href="Interface/VisAlloggiStruttureGUI.jsp">riepilogoAlloggiStruttura</a>
 <a href="PrenotazioniStrutturaGUI.jsp">Prenotazioni Struttura</a>
<a href="InserisciStrutturaGUI.jsp">Inserisci Struttura</a>
<a href="ModificaStrutturaGUI.jsp">Modifica Struttura</a>
<a href="InserisciAlloggioGUI.jsp">Inserisci Alloggio</a>
<a href="ModificaAlloggioGUI.jsp">Modifica Alloggio</a>
<a href = "VisDatiSistemaGUI.jsp">Visualizza dati sistema</a>
<a href = "loginUtenteGUI.jsp">Login utente</a>
<a href = "loginHostGUI.jsp">Login host</a>
<a href = "registrazioneUtenteGUI.jsp">Registrazione utente</a>
<a href = "areaUtenteGUI.jsp">Area utente</a>-->

<script>
  function apriSceltaLogin(){

    let flag = false;

    <%
      Utente u = (Utente)session.getAttribute("utente");
      Host h = (Host) session.getAttribute("host");

      if(u == null && h == null){
    %>
    flag = true;
    <%}%>

    if(flag){
      document.getElementById("divSceltaLogin").style.display = "block";
    }
  }

  document.addEventListener("keydown", function (event){
    if(event.key === "Escape"){
      document.getElementById("divSceltaLogin").style.display = "none";
    }
  });

  function reindirizzaUtente(){
    window.location.href = 'loginUtenteGUI.jsp';
  }

  function reindirizzaHost(){
    window.location.href = 'loginHostGUI.jsp';
  }

  function reindirizzaAdmin(){
    window.location.href = 'loginAdmin.jsp';
  }
</script>
</body>
</html>