package Application.Autenticazione;

import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/registrazioneUtente")
public class RegistrazioneUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String password = req.getParameter("password");
        String citta = req.getParameter("citta");
        String numeroCivico = req.getParameter("numCivico");
        String via = req.getParameter("via");
        String dataNascita = req.getParameter("dataNascita");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");


        Utente u = new Utente(email, nome, cognome, password, citta, numeroCivico, via, LocalDate.parse(dataNascita), recapitoTelefonico, false);

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());

        String address;

        if(autenticazioneFacade.registrazioneUtente(u)) {
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
