package Application.GestioneAmministratore;

import Storage.Prenotazione.Prenotazione;
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
        int codicePrenotazione = Integer.parseInt(req.getParameter("codicePrenotazione"));
        String checkIn = req.getParameter("checkInPrenotazione");
        String checkOut = req.getParameter("checkOutPrenotazione");
        String fkUtente = req.getParameter("idUtenteFk");
        int numeroPersone = Integer.parseInt(req.getParameter("numeroPersonePrenotazione"));
        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggioPrenotazione"));
        int fkStruttura = Integer.parseInt(req.getParameter("fkStrutturaPrenotazione"));

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate checkInDate;
        LocalDate checkOutDate;

        try{
            checkInDate = (Date) formatter.parse(checkIn);
            checkOutDate = (Date) formatter.parse(checkOut);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();

        Prenotazione p = new Prenotazione();
        p.setFkUtente(fkUtente);
        p.setCodicePrenotazione(codicePrenotazione);
        gestioneAmministratoreFacade.modificaDatiSistemaPrenotazione(p, LocalDate.parse(checkIn), LocalDate.parse(checkOut), numeroPersone, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisDatiSistemaGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
