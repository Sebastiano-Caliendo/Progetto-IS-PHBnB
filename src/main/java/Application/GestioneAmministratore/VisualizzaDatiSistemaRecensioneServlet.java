package Application.GestioneAmministratore;

import Storage.Recensione.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaDatiSistemaRecensioneServlet", value = "/visualizzaDatiSistemaRecensioneServlet")
public class VisualizzaDatiSistemaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        List<Recensione> recensioni = new ArrayList<>();

        recensioni = gestioneAmministratoreFacade.visualizzaDatiSistemaRecensione(req.getSession());

        req.setAttribute("listaRecensioni", recensioni);
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleRecensioni.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
