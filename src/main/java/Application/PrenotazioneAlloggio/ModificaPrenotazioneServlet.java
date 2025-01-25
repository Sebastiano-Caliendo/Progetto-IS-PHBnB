package Application.PrenotazioneAlloggio;

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


        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        prenotazioneAlloggioFacade.modificaPrenotazione(LocalDate.parse(dataCheckIn), LocalDate.parse(dataCheckOut), Integer.parseInt(numPostiLetto), Integer.parseInt(codPrenotazione));

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
