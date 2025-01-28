<%--
  Created by IntelliJ IDEA.
  User: NicoTanc
  Date: 23/01/2025
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Gestione Admin</title>

    <link rel = "stylesheet" href = "css/index.css">
    <link rel = "stylesheet" href = "css/style.css">
  </head>
  <body>
  <div id="topContainer">
    <div id="divLogo">
      <p id="parLogo" class="mid-text">PHB&B</p>
    </div>
  </div>
  <form method = "post">
    <div id = "bottomContainerConferma">
      <p class = "small-text, parScrittaAdmin">Premi il pulsante per visualizzare i dati</p>
    <br>
    <input type = "submit" formaction="../visualizzaDatiSistemaAmministratoreServlet" value = "Conferma">
    </div>
  </form>
  </body>
</html>
