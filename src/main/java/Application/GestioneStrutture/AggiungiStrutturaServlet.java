package Application.GestioneStrutture;

import Storage.Host.Host;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AggiungiStrutturaServlet", value = "/aggiungiStrutturaServlet")
public class AggiungiStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo l'host dalla sessione (il campo fk_host non verrà inserito)
        Host host = (Host) req.getSession().getAttribute("host");

        // prendiamo tutti i campi insriti dall'host
        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("via");
        String citta = req.getParameter("citta");
        String numCivico = req.getParameter("numCivico");
        String descrizione = req.getParameter("descrizione");
        String urlImmagine = req.getParameter("urlImmagine");

        // inseriamo la struttura nel DB
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.aggiungiStruttura(host, nomeStruttura, via, citta, numCivico, descrizione, urlImmagine);

        // passo il controllo alla jsp che mostrerà il riepilogo delle strutture
        RequestDispatcher dispatcher = req.getRequestDispatcher("RiepilogoStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
