package Application.InserimentoRecensione;

import Storage.Recensione.Recensione;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "inserisciRecensioneServlet", value = "/inserisciRecensioneServlet")
public class InserisciRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailRecensore = req.getParameter("emailRecensore"); // type = "hidden" nella jsp
        String descrizioneRecensione = req.getParameter("descrizioneRecensione");
        int votoRecensione = Integer.parseInt(req.getParameter("votoRecensione"));
        LocalDate dataRecensione = LocalDate.parse(req.getParameter("dataRecensione"));
        int codicePrenotazione = Integer.parseInt(req.getParameter("codicePrenotazione")); // type = "hidden" nella jsp
        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio")); // type = "hidden" nella jsp
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura")); // type = "hidden" nella jsp

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        inserimentoRecensioneFacade.inserisciRecensione(req.getSession(), emailRecensore, descrizioneRecensione, votoRecensione, dataRecensione, codicePrenotazione, numeroAlloggio, idStruttura);

        String nameAlloggio = req.getParameter("nameAlloggio");

        RequestDispatcher dispatcher = req.getRequestDispatcher("viewAlloggio.jsp?nameAlloggio=" + nameAlloggio);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
