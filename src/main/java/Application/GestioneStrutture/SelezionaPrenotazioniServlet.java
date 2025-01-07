package Application.GestioneStrutture;

import Storage.Prenotazione.Prenotazione;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/selezionaPrenotazioniServlet")
public class SelezionaPrenotazioniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // prendo l'id della struttura, in modo da poter prendere tutte le prenotazioni associate a quella struttura
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));

        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();

        // prendo la struttura
        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);

        // prendo la lista delle prenotazioni di quella struttura
        List<Prenotazione> prenotazioniStruttura = new ArrayList<>();
        prenotazioniStruttura = strutturaFacade.visualizzaPrenotazioni(struttura);

        // inserisco la lista delle prenotazioni nella richiesta in modo che la prossima JSP possa stampare
        // tutte le prenotazioni di questa struttura
        req.setAttribute("listaPrenotazioni", prenotazioniStruttura);

        // inserisco la struttura che servirà alla jsp : per avere il nome
        req.setAttribute("struttura", struttura);

        // passo il controllo alla jsp PrenotazioniStruttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/PrenotazioniStrutturaGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
