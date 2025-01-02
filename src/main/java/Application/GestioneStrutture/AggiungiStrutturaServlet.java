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
        // prendiamo l'host dalla sessione (il campo fk_host non verrà inserito)
        Host host = (Host) req.getSession().getAttribute("host");

        // prendiamo tutti i campi insriti dall'host
        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("via");
        String citta = req.getParameter("citta");
        int numAlloggi = Integer.parseInt(req.getParameter("numAlloggi"));
        String descrizione = req.getParameter("descrizione");

        // creiamo la struttura
        Struttura struttura = new Struttura();
        struttura.setFkHost(host.getEmail());
        struttura.setDescrizione(descrizione);
        struttura.setNumAlloggi(numAlloggi);
        struttura.setNomeStruttura(nomeStruttura);
        struttura.setCitta(citta);
        struttura.setVia(via);

        // inseriamo la struttura nel DB
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.aggiungiStruttura(struttura);

        // passo il controllo alla jsp che mostrerà il riepilogo delle strutture
        RequestDispatcher dispatcher = req.getRequestDispatcher("riepilogoStrutture.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
