package Application.InserimentoRecensione;

import Storage.Host.Host;
import Storage.Recensione.Recensione;
import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaRecensioniPubblicateServlet", value = "/visualizzaRecensioniPubblicateServlet")
public class VisualizzaRecensioniPubblicateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        String emailRecensore = utente.getEmail();

        req.setAttribute("callByServlet", "yes");

        List<Recensione> recensioniUtente;

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        recensioniUtente = inserimentoRecensioneFacade.visualizzaRecensioniPubblicate(emailRecensore);

        req.setAttribute("recensioniUtente", recensioniUtente);

        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/listRecensioniUtente.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
