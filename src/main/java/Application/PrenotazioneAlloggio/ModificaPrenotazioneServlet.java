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
import java.time.LocalDate;
import java.util.List;

@WebServlet("/modificaPrenotazione")
public class ModificaPrenotazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("callByServlet", "yes");

        String codPrenotazione = req.getParameter("codPrenotazione");
        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numPostiLetto = req.getParameter("numPostiLetto");
        String numAlloggio = req.getParameter("numAlloggio");
        String idStruttura = req.getParameter("idStruttura");


        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        boolean flag = prenotazioneAlloggioFacade.modificaPrenotazione(dataCheckIn, dataCheckOut, numPostiLetto, codPrenotazione, numAlloggio, idStruttura);

        req.getSession().setAttribute("prenotazioni", prenotazioneAlloggioFacade.visualizzaPrenotazioni(u.getEmail()));

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        List<Integer> codiciPrenotazione = inserimentoRecensioneFacade.visualizzaRecensioniUtente(u);
        req.setAttribute("codiciPrenotazioniRecensite", codiciPrenotazione);

        String address;

        if(flag) {
            address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp";
        } else {
            address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp?error=1";
        }

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
