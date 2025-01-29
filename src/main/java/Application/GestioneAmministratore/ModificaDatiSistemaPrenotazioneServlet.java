package Application.GestioneAmministratore;

import Storage.Prenotazione.Prenotazione;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@WebServlet(name = "modificaDatiSistemaPrenotazioneSerlvet", value = "/modificaDatiSistemaPrenotazioneServlet")
public class ModificaDatiSistemaPrenotazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codicePrenotazione = req.getParameter("codicePrenotazione");
        String checkIn = req.getParameter("checkInPrenotazione");
        String checkOut = req.getParameter("checkOutPrenotazione");
        String numeroPersone = req.getParameter("numeroPersonePrenotazione");

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();

        /*Prenotazione p = new Prenotazione();
        p.setCodicePrenotazione(codicePrenotazione);
        p.setCheckIn(LocalDate.parse(checkIn));
        p.setCheckOut(LocalDate.parse(checkOut));
        p.setNumeroPersone(numeroPersone);
        p.setCviCarta(cviCarta);
        p.setDataScadenzaCarta(dataScadenzaCarta);

        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveById(fkUtente);

        p.setUtente(utente);
        p.setNumeroCartaCredito(numeroCarta);*/

        gestioneAmministratoreFacade.modificaDatiSistemaPrenotazione(codicePrenotazione, checkIn, checkOut, numeroPersone);

        resp.sendRedirect(req.getContextPath() + "/Interface/indexAdmin.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
