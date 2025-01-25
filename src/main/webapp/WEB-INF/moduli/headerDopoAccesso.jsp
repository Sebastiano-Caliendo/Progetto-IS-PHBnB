<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

    <%
        String header = "Interface/";
        if(callByServlet == 0)
            header = "";
    %>

    <div id="header">
        <div id="divLogo">
            <p id="parLogo" class="mid-text">PHB&B</p>
        </div>
        <div id="container">
            <div id="divImg">
                <img src="<%=header%>img/user.png" alt="immagine utente" id="userImg">
            </div>
            <div id="dropdown">
                <a href="#" onclick="openWindow()" id="linkLogout" class="normal-small-text">Logout</a>
            </div>
        </div>
    </div>

    <script>
        function openWindow() {

            var logoutDiv = document.createElement("div");

            logoutDiv.id = "logoutDiv";
            logoutDiv.style.display = "flex";
            logoutDiv.style.flexDirection = "column";
            logoutDiv.style.position = "fixed";
            logoutDiv.style.height = "20%";
            logoutDiv.style.width = "20%";
            logoutDiv.style.background = "white";
            logoutDiv.style.top = "50%";
            logoutDiv.style.left = "50%";
            logoutDiv.style.transform = 'translate(-50%, -50%)';
            logoutDiv.style.border = "2px solid darkgoldenrod";

            var divTesto = document.createElement("div");
            var divBottoni = document.createElement("div");

            divTesto.style.height = "50%";
            divTesto.textContent = "Sei sicuro di volerti disconnettere?";
            divTesto.style.textAlign = "center";
            divTesto.style.alignContent = "center";

            divBottoni.style.height = "50%";
            divBottoni.style.display = "flex";
            divBottoni.style.flexDirection = "row";

            var buttonSi = document.createElement("button");
            var buttonNo = document.createElement("button");

            buttonSi.textContent = "Si";
            buttonSi.style.margin = "auto";
            buttonSi.style.background = "darkgoldenrod"
            buttonSi.style.color = "white"
            buttonSi.style.height = "40%";
            buttonSi.style.width = "20%";
            //associa al button il percorso della pagine jsp da raggiungere dopo il click
            buttonSi.addEventListener("click", function() {
                window.location.href = "logout";
            });

            buttonNo.textContent = "No";
            buttonNo.style.margin = "auto";
            buttonNo.style.background = "darkgoldenrod";
            buttonNo.style.color = "white";
            buttonNo.style.height = "40%";
            buttonNo.style.width = "20%";
            buttonNo.addEventListener("click", closeWindow);

            divBottoni.appendChild(buttonSi);
            divBottoni.appendChild(buttonNo);

            logoutDiv.appendChild(divTesto);
            logoutDiv.appendChild(divBottoni);

            document.body.appendChild(logoutDiv);
        }

        function closeWindow() {

            var logoutDiv = document.getElementById("logoutDiv");
            logoutDiv.style.display = "none";
        }
    </script>
</body>
</html>
