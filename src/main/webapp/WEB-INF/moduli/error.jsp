<%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 05/02/2025
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="Interface/css/style.css">
    <title>errore</title>
</head>
<%
    /*int callByServlet = 0;
    String isCallByServlet = (String) request.getAttribute("callByServlet");
    if(isCallByServlet != null && isCallByServlet.equalsIgnoreCase("yes"))
        callByServlet = 1;

    String servlet = "";
    String jsp = "";
    if(callByServlet == 0) { // chiamata da jsp
        servlet = "../";
        jsp = "";
    }
    else  {  // chiamata da servlet
        servlet = "";
        jsp = "Interface/";
    }*/

    String urlImmagine = "Interface/img/logo.jpeg";
    /*String replace = "Interface/";
    if(callByServlet == 0) { // chiamata da jsp
        urlImmagine = urlImmagine.replace(replace, "");
    }
    else  {  // chiamata da servlet

    }*/
%>
<body style="background-color: var(--rosa-bianchissimo);">
    <div class="containerErrore">
        <div class="parteSinistra">
            <img src="<%= urlImmagine %>" class="imgErrore">
        </div>
        <div class="parteDestra">
            <div class="titleErrore big-text"> ERRORE DEL SERVER, ci scusiamo per il disagio, risolveremo al più presto !</div>
        </div>
    </div>
</body>
</html>
