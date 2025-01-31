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

        req.setAttribute("callByServlet", "yes");

        String descrizioneRecensione = req.getParameter("descrizione");
        String votoRecensione = req.getParameter("votoRecensione");
        String codicePrenotazione = req.getParameter("codicePrenotazione"); // type = "hidden" nella jsp
        String numeroAlloggio = req.getParameter("numeroAlloggio"); // type = "hidden" nella jsp
        String idStruttura = req.getParameter("idStruttura"); // type = "hidden" nella jsp

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        boolean verifica = inserimentoRecensioneFacade.inserisciRecensione(req.getSession(), descrizioneRecensione, votoRecensione, codicePrenotazione, numeroAlloggio, idStruttura);

        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/areaUtenteGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
