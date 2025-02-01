package Application.PrenotazioneAlloggio;

import Application.InserimentoRecensione.InserimentoRecensioneFacade;
import Storage.Occupa.Occupa;
import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminaPrenotazione")
public class EliminaPrenotazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("callByServlet", "yes");

        String codPrenotazione = req.getParameter("codPrenotazione");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        boolean flag = prenotazioneAlloggioFacade.eliminaPrenotazione(codPrenotazione);

        List<Occupa> prenotazioni = prenotazioneAlloggioFacade.visualizzaPrenotazioni(u.getEmail());

        if(!prenotazioni.isEmpty()) {
            req.setAttribute("prenotazioni", prenotazioni);
        }

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        List<Integer> codiciPrenotazione = inserimentoRecensioneFacade.visualizzaRecensioniUtente(u);

        if(!codiciPrenotazione.isEmpty()) {
            req.setAttribute("codiciPrenotazioniRecensite", codiciPrenotazione);
        }

        String address;

        if(flag) {
            address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp";
        } else {
            address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp?error=2";
        }

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
