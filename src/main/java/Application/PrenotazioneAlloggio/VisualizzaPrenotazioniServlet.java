package Application.PrenotazioneAlloggio;

import Application.InserimentoRecensione.InserimentoRecensioneFacade;
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
        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();

        List<Occupa> prenotazioni = prenotazioneAlloggioFacade.visualizzaPrenotazioni(u.getEmail());
        List<Integer> codiciPrenotazione = inserimentoRecensioneFacade.visualizzaRecensioniUtente(u);

        if(!prenotazioni.isEmpty()) {
            req.setAttribute("prenotazioni", prenotazioni);
        }

        if(!codiciPrenotazione.isEmpty()) {
            req.setAttribute("codiciPrenotazioniRecensite", codiciPrenotazione);
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
