package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "visualizzaDatiSistemaAlloggioServlet", value = "/visualizzaDatiSistemaAlloggioServlet")
public class VisualizzaDatiSistemaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        List<Alloggio> alloggi = new ArrayList<>();

        alloggi = gestioneAmministratoreFacade.visualizzaDatiSistemaAlloggio(req.getSession());

        req.setAttribute("listaAlloggi", alloggi);
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleAlloggi.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
