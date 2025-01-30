package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import Storage.Host.Host;
import Storage.Prenotazione.Prenotazione;
import Storage.Recensione.Recensione;
import Storage.Struttura.Struttura;
import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "visualizzaDatiSistemaAmministratoreServlet", value = "/visualizzaDatiSistemaAmministratoreServlet")
public class VisualizzaDatiSistemaAmministratoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GestioneAmministratoreFacade facade = new GestioneAmministratoreFacade();

        req.setAttribute("callByServlet", "yes");
        req.getSession().setAttribute("admin", 1);
        List<Alloggio> alloggi = facade.visualizzaDatiSistemaAlloggio();
        List<Host> hosts = facade.visualizzaDatiSistemaHost();
        List<Prenotazione> prenotazioni = facade.visualizzaDatiSistemaPrenotazione();
        List<Recensione> recensioni = facade.visualizzaDatiSistemaRecensione();
        List<Struttura> strutture = facade.visualizzaDatiSistemaStruttura();
        List<Utente> utenti = facade.visualizzaDatiSistemaUtente();

        req.setAttribute("alloggi", alloggi);
        req.setAttribute("hosts", hosts);
        req.setAttribute("prenotazioni", prenotazioni);
        req.setAttribute("recensioni", recensioni);
        req.setAttribute("strutture", strutture);
        req.setAttribute("utenti", utenti);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Interface/VisDatiSistemaGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
