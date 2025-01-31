package Application.Autenticazione;

import Storage.Utente.Utente;
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

@WebServlet("/registrazioneUtente")
public class RegistrazioneUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        req.setAttribute("callByServlet", "yes");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String password = req.getParameter("password");
        String citta = req.getParameter("citta");
        String numeroCivico = req.getParameter("numCivico");
        String via = req.getParameter("via");
        String dataNascita = req.getParameter("dataNascita");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");


        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());

        String address;

        if(autenticazioneFacade.registrazioneUtente(email, nome, cognome, password, citta, numeroCivico, via, dataNascita, recapitoTelefonico)) {
            address = "Interface/index.jsp";
        } else {
            address = "Interface/registrazioneUtenteGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
