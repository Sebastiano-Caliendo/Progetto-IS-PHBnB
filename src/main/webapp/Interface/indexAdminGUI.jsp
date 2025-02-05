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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  </head>
  <body style="height: 100%;">

  <%
    String error = request.getParameter("error");
    String op = request.getParameter("op");

    if(error != null && error.equals("1")) {%>
      <script>
          window.onload = function popupErrore() {
          Swal.fire({
            title: 'Operazione non riuscita!',
            icon: 'error',
            confirmButtonText: 'OK',
            customClass:{
              confirmButton: 'btn-custom'
            }
          });
          }
      </script>
    <%} else if(op != null && op.equals("ok")) { %>
      <script>
          window.onload = function popupSuccesso() {
          Swal.fire({
            title: 'Operazione effettuata con successo!',
            icon: 'success',
            confirmButtonText: 'OK',
            customClass:{
              confirmButton: 'btn-custom'
            }
          });
          }
      </script>
    <%
    }
    %>

  <div id="topContainer">
    <div id="divLogo">
      <p id="parLogo" class="mid-text">PHB&B</p>
    </div>
  </div>
  <form method = "post">
    <div id = "bottomContainerConferma" style="align-content: center; height: 80%;">
      <p class = "small-text, parScrittaAdmin">Premi il pulsante per visualizzare i dati</p>
    <br>
    <input type = "submit" formaction="../visualizzaDatiSistemaAmministratoreServlet" value = "Conferma">
    </div>
  </form>
  </body>

</html>
