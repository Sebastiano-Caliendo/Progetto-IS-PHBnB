<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Host</title>

    <link rel="stylesheet" href="css/loginRegistrazione.css">
</head>
<body>
    <%
        String error =  request.getParameter("error");
    %>

    <div id="mainContainer">
        <div id="divLogin">
            <p id="parBentonato" class="loginElements">BENTORNATO!<p/>
            <p id="parAccesso" class="loginElements">Accedi a PHB&B</p>

            <%if(error != null && error.equals("1")) {%>
                <p id="parErrore" class="loginElements">Credenziali errate</p>
            <%}%>

            <form id="formLogin" action="../login" method="POST">
                <label for="email" id="emailLabel" class="loginElements">Email</label><br>
                <input type="text" id="email" name="email" class="loginElements"><br>
                <label for="password" id="pwdLabel" class="loginElements">Password</label><br>
                <input type="password" id="password" name="password" class="loginElements"><br>
                <input type="hidden" value="host" name="tipo">
                <input type="submit" id="submitButtom" value="Accedi" class="loginElements">
                <br>
                <a href = "registrazioneHostGUI.jsp" style = "text-decoration: none; font-family: 'Montserrat', sans-serif; color: black;">Non hai un'account? Registrati qui!</a>
                <br>
                <br>
                <a href = "loginAdmin.jsp" style = "text-decoration: none; font-family: 'Montserrat', sans-serif; color: black;">Sei un admin? Accedi qui!</a>
            </form>
        </div>
    </div>
</body>
</html>
