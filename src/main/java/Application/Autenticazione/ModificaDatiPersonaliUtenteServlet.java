package Application.Autenticazione;

import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/modificaDatiPersonaliUtente")
public class ModificaDatiPersonaliUtenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String vecchiaPassword = req.getParameter("vecchiaPassword");
        String nuovaPassword = req.getParameter("nuovaPassword");
        String confermaPassword = req.getParameter("confermaPassword");
        String citta = req.getParameter("citta");
        String numeroCivico = req.getParameter("numCivico");
        String via = req.getParameter("via");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        String address = "Interface/areaUtenteGUI.jsp";

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());
        autenticazioneFacade.modificaDatiPersonaliUtente(u, email, nome, cognome, nuovaPassword, citta, numeroCivico, via, recapitoTelefonico);

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
