package Application.GestioneAmministratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "modificaDatiSistemaRecensioneServlet", value = "/modificaDatiSistemaRecensioneServlet")
public class ModificaDatiSistemaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idRecensione = Integer.parseInt(req.getParameter("idRecensione"));
        int codicePrenotazione = Integer.parseInt(req.getParameter("codicePrenotazione"));

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaRecensione(idRecensione, codicePrenotazione, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisDatiSistemaGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
