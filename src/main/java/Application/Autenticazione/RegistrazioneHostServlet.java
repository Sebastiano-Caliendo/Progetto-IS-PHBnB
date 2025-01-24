package Application.Autenticazione;

import Storage.Host.Host;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/registrazioneHost")
public class RegistrazioneHostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String password = req.getParameter("password");
        String dataNascita = req.getParameter("dataNascita");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");

        Host h = new Host(email, nome, cognome, password, LocalDate.parse(dataNascita), recapitoTelefonico);

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());

        String address;

        if(autenticazioneFacade.registrazioneHost(h)) {
            address = "Interface/index.jsp";
        } else {
            address = "Interface/registrazioneHostGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
