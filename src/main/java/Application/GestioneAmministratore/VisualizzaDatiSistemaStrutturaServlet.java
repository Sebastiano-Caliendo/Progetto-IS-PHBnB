package Application.GestioneAmministratore;

import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaDatiSistemaStrutturaServlet", value = "/visualizzaDatiSistemaStrutturaServlet")
public class VisualizzaDatiSistemaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        List<Struttura> strutture = new ArrayList<>();
        strutture = gestioneAmministratoreFacade.visualizzaDatiSistemaStruttura(req.getSession());

        req.setAttribute("listaStrutture", strutture);
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleStrutture.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
