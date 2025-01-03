package Application.GestioneAmministratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "cancellazioneDatiSistemaRecensioneServlet", value = "/cancellazioneDatiSistemaRecensioneServlet")
public class CancellazioneDatiSistemaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailRecensione");
        String codicePrenotazione = req.getParameter("codicePrenotazione");
        int numeroAlloggio = Integer.parseInt(req.getParameter("numAlloggio"));

        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        gestioneAmministratoreFacade.cancellazioneDatiSitemaRecensione(email, codicePrenotazione, numeroAlloggio, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleRecensioni.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
