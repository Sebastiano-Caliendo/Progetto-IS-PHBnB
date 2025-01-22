<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/headerDopoAccesso.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="javascript/logoutWindow.js" defer></script>
</head>
<body>
    <div id="header">
        <div id="divLogo">
            <p id="parLogo" class="mid-text">PHB&B</p>
        </div>
        <div id="container">
            <div id="divImg">
                <img src="img/user.png" alt="immagine utente" id="userImg">
            </div>
            <div id="dropdown">
                <a href="#" onclick="openWindow()" id="linkLogout">Logout</a>
            </div>
        </div>
    </div>
</body>
</html>
