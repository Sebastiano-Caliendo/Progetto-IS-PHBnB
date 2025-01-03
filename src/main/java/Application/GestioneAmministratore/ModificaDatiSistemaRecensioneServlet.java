package Application.GestioneAmministratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "modificaDatiSistemaRecensioneServlet", value = "/modificaDatiSistemaRecensioneServlet")
public class ModificaDatiSistemaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailRecensore = req.getParameter("emailRecensore");
        String codicePrenotazione = req.getParameter("codicePrenotazione");
        int numAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));

        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaRecensione(emailRecensore, codicePrenotazione, numAlloggio, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleRecensioni.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
