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
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/finalizzaPrenotazione")
public class FinalizzaPrenotazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("callByServlet", "yes");

        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numOspiti = req.getParameter("numOspiti");
        String numAlloggio = req.getParameter("numAlloggio");
        String codStruttura = req.getParameter("codStruttura");
        String numeroCarta = req.getParameter("numeroCarta");
        String dataScadenza = req.getParameter("dataScadenzaCarta");
        String cvvCarta = req.getParameter("cvvCarta");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        boolean flag = prenotazioneAlloggioFacade.finalizzaPrenotazione(u, nome, cognome, dataCheckIn, dataCheckOut, numOspiti, numAlloggio, codStruttura, numeroCarta, dataScadenza, cvvCarta);
        System.out.println(flag);
        String address;

        if(flag) {
            address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp?scontrino=true";
            req.getSession().setAttribute("prenotazioni", prenotazioneAlloggioFacade.visualizzaPrenotazioni(u.getEmail()));
        } else {
            address = "Interface/dettagliAlloggioGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
