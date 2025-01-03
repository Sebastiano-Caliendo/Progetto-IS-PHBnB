package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaDatiSistemaUtenteServlet", value = "/visualizzaDatiSistemaUtenteServlet")
public class VisualizzaDatiSistemaUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        List<Utente> utenti = new ArrayList<>();

        utenti = gestioneAmministratoreFacade.visualizzaDatiSistemaUtente(req.getSession());

        req.setAttribute("listaUtenti", utenti);
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleUtenti.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
