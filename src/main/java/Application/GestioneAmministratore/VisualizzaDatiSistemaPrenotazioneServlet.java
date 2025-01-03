package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import Storage.Prenotazione.Prenotazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "visualizzaDatiSistemaPrenotazioneServlet", value = "/visualizzaDatiSistemaPrenotazioneServlet")
public class VisualizzaDatiSistemaPrenotazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        List<Prenotazione> prenotazioni = new ArrayList<>();

        prenotazioni = gestioneAmministratoreFacade.visualizzaDatiSistemaPrenotazione(req.getSession());

        req.setAttribute("listaPrenotazioni", prenotazioni);
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotalePrenotazioni.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
