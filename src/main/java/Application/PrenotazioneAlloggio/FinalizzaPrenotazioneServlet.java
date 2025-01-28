package Application.PrenotazioneAlloggio;

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

        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numOspiti = req.getParameter("numOspiti");
        String numAlloggio = req.getParameter("numAlloggio");
        String codStruttura = req.getParameter("codStruttura");
        String costoPrenotazione = req.getParameter("costoPrenotazione");
        String numeroCarta = req.getParameter("numeroCarta");
        String dataScadenza = req.getParameter("dataScadenzaCarta");
        String cvvCarta = req.getParameter("cvvCarta");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        prenotazioneAlloggioFacade.finalizzaPrenotazione(u, LocalDate.parse(dataCheckIn), LocalDate.parse(dataCheckOut), Integer.parseInt(numOspiti), u.getEmail(), Integer.parseInt(numAlloggio), Integer.parseInt(codStruttura), 100, numeroCarta, LocalDate.parse(dataScadenza), cvvCarta);

        String address = "Interface/visualizzaStoricoPrenotazioniGUI.jsp";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
