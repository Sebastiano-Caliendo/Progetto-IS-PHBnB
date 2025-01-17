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

        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numPostiLetto = req.getParameter("numPostiLetto");
        String numAlloggio = req.getParameter("numAlloggio");
        String codStruttura = req.getParameter("codStruttura");
        String costoPrenotazione = req.getParameter("costoPrenotazione");
        String numeroCarta = req.getParameter("numeroCarta");
        String dataScadenza = req.getParameter("dataScadenza");
        String cviCarta = req.getParameter("cviCarta");

        Utente u = (Utente) req.getSession().getAttribute("utente");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        prenotazioneAlloggioFacade.finalizzaPrenotazione(u, LocalDate.parse(dataCheckIn), LocalDate.parse(dataCheckOut), Integer.parseInt(numPostiLetto), u.getEmail(), Integer.parseInt(numAlloggio), Integer.parseInt(codStruttura), Double.parseDouble(costoPrenotazione), numeroCarta, LocalDate.parse(dataScadenza), cviCarta);

        String address = "";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
