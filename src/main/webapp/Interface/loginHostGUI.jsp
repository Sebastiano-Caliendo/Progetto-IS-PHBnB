<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Host</title>

    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="Interface/css/login.css">
</head>
<body>
    <%
        String error =  request.getParameter("error");
    %>

    <div id="mainContainer">
        <div id="divLogin">
            <p id="parBentonato" class="formElements">BENTORNATO!<p/>
            <p id="parAccesso" class="formElements">Accedi a PHB&B</p>

            <%if(error != null && error.equals("1")) {%>
                <p id="parErrore">Credenziali errate</p>
            <%}%>

            <form id="formLogin" action="../login" method="POST">
                <label for="email" id="emailLabel" class="formElements">Email</label><br>
                <input type="text" id="email" name="email" placeholder="email" class="formElements"><br>
                <label for="password" id="pwdLabel" class="formElements">Password</label><br>
                <input type="password" id="password" name="password" placeholder="password" class="formElements"><br>
                <input type="hidden" value="host" name="tipo">
                <input type="submit" id="submitButtom" value="Accedi" class="formElements">
            </form>
        </div>
    </div>
</body>
</html>
