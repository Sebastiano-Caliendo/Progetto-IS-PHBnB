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

        req.setAttribute("callByServlet", "yes");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String nuovaPassword = req.getParameter("nuovaPassword");
        String citta = req.getParameter("citta");
        String numeroCivico = req.getParameter("numCivico");
        String via = req.getParameter("via");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        String address;

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());

        if(autenticazioneFacade.modificaDatiPersonaliUtente(u, email, nome, cognome, nuovaPassword, citta, numeroCivico, via, recapitoTelefonico)) {
            address = "Interface/areaUtenteGUI.jsp";
        } else {
            address = "Interface/modificaDatiUtenteGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
