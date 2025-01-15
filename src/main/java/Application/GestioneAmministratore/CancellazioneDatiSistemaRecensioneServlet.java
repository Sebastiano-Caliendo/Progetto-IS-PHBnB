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

        int idRecensione = Integer.parseInt(req.getParameter("idRecensione"));

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.cancellazioneDatiSitemaRecensione(idRecensione, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisDatiSistemaGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
