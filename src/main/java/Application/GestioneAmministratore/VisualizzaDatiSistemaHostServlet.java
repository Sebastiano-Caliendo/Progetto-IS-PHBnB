package Application.GestioneAmministratore;

import Storage.Host.Host;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaDatiSistemaHostServlet", value = "/visualizzaDatiSistemaHostServlet")
public class VisualizzaDatiSistemaHostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        List<Host> hosts = new ArrayList<>();

        hosts = gestioneAmministratoreFacade.visualizzaDatiSistemaHost(req.getSession());

        req.setAttribute("listaHost", hosts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleHost.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
