<%--
  Created by IntelliJ IDEA.
  User: ciril
  Date: 03/01/2025
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <header>
        <!-- <div class="containerHeader"> -->
            <div class="logo mid-text">
                <%
                    String header = "Interface/";
                    if(callByServlet == 0)
                        header = "";
                %>
                <a href="<%= header %>index.jsp" title="home">PHB<span class="normal-text">&</span>B</a>
            </div>
            <!-- <div class="buttonHeader">
                <div class="buttonRegistrazione">
                    <input type="submit" action="" value="Registrazione">
                </div>
                <div class="buttonLogin">
                    <input type="submit" action="" value="Login">
                </div>
            </div> -->
            <hr class="rigaHeader">
        <!-- </div> -->
    </header>
</body>
</html>
