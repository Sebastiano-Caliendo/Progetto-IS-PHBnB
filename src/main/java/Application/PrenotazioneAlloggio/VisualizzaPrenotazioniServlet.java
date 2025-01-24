package Application.PrenotazioneAlloggio;

import Storage.Occupa.Occupa;
import Storage.Prenotazione.Prenotazione;
import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/visualizzaPrenotazioni")
public class VisualizzaPrenotazioniServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("callByServlet", "yes");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();

        List<Occupa> prenotazioni = prenotazioneAlloggioFacade.visualizzaPrenotazioni(u.getEmail());

        if(!prenotazioni.isEmpty()) {
            req.setAttribute("prenotazioni", prenotazioni);
        }

        String address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
