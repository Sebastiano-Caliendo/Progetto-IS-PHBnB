<%@ page import="Storage.Utente.Utente" %>
<%@ page import="Storage.Host.Host" %>
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

    Utente utente = (Utente) session.getAttribute("utente");
    Host host = (Host) session.getAttribute("host");
    Utente admin = (Utente) session.getAttribute("admin");

    boolean loggato = false;
    if(utente == null && host == null && admin == null)
      loggato = false;
    else
      loggato = true;
  %>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <% if(callByServlet == 1) { %>
  <link rel="stylesheet" href="Interface/css/index.css">
  <link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
  <!--<link rel="stylesheet" href="Interface/css/headerDopoAccesso.css">
  <link rel="stylesheet" href="Interface/css/footer.css">-->
  <link rel="stylesheet" href="Interface/css/style.css">
  <%
  }
  else { %>
  <link rel="stylesheet" href="css/index.css">
  <link rel="stylesheet" href="css/headerDopoAccesso.css">
  <!--<link rel="stylesheet" href="css/headerDopoAccesso.css">
  <link rel="stylesheet" href="css/footer.css"> -->
  <link rel="stylesheet" href="css/style.css">
  <% } %>

</head>
<body>

  <%
    String servlet = "";
    String jsp = "";
    if(callByServlet == 0) { // chiamata da jsp
      servlet = "../";
      jsp = "";
    }
    else  {  // chiamata da servlet
      servlet = "";
      jsp = "Interface/";
    }
  %>

  <% if(!loggato) {%>
    <div id="topContainer">
      <div id="divLogo">
        <% if(callByServlet == 0) { %>
        <p id="parLogo" class="mid-text"><a href="<%= jsp %>index.jsp" class="logoHref">PHB&B</a></p>
        <% } else { %>
        <p id="parLogo" class="mid-text"><a href="<%= jsp %>index.jsp" class="logoHref">PHB&B</a></p>
        <% } %>
      </div>
      <div id="divRegStruttura">
        <% if(callByServlet == 0) { %>
        <a id="linkRegStruttura" href="<%= jsp %>registrazioneHostGUI.jsp" class="normal-small-text">Registra la tua struttura</a>
        <% } else { %>
        <a id="linkRegStruttura" href="<%= jsp %>registrazioneHostGUI.jsp" class="normal-small-text">Registra la tua struttura</a>
        <% } %>
      </div>
      <div id="divReg">
        <% if(callByServlet == 0) { %>
        <button id="linkReg" onclick="window.location.href='<%= jsp %>registrazioneUtenteGUI.jsp';" class="small-text buttons">Registrati</button>
        <% } else { %>
        <button id="linkReg" onclick="window.location.href='<%= jsp %>registrazioneUtenteGUI.jsp';" class="small-text buttons">Registrati</button>
        <% } %>
      </div>
      <div id="divAccedi">
        <% if(callByServlet == 0) { %>
        <button id="linkAccedi" onclick="apriSceltaLogin()" class="small-text buttons">Accedi</button>
        <% } else { %>
        <button id="linkAccedi" onclick="apriSceltaLogin()" class="small-text buttons">Accedi</button>
        <% } %>
      </div>
    </div>
    <% } else { %>
            <%@ include file="../WEB-INF/moduli/headerDopoAccesso.jsp"%>
      <% } %>
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
          <form id="formRicerca" action="<%= servlet %>visualizzaAlloggi">
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
      <div class="imgIndex">
        <img class="imgHome" src="img/imgHome2.jpg">
        <img class="imgHome" src="img/imgHome3.jpg">
      </div>
    </div>

  <!-- scelta login -->
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
        if(utente == null && host == null){
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