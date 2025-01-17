package Application.InserimentoRecensione;

import Application.GestioneStrutture.gestioneStrutturaFacade;
import Storage.Recensione.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// utilizza un servizio di struttura
@WebServlet(name = "visualizzaRecensioniRicevuteServlet", value = "/visualizzaRecensioneRicevuteServlet")
public class VisualizzaRecensioniRicevuteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendo l'id della struttura
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));

        // ottengo il nome della struttura
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        String nomeStruttura = strutturaFacade.returnStruttura(idStruttura).getNomeStruttura();

        List<Recensione> recensioniStruttura = new ArrayList<>();

        // prendo tutte le recensioni effettuate su alloggi di una struttura
        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        recensioniStruttura = inserimentoRecensioneFacade.visualizzaRecensioniRicevute(req.getSession(), idStruttura);

        // calcolo la media delle recensioni
        float totale = 0;
        float media = 0;
        for (int i = 0; i < recensioniStruttura.size(); i++) {
            Recensione recensione = recensioniStruttura.get(i);
            totale = totale + recensione.getVotoRecensione();
        }

        media = totale / recensioniStruttura.size();

        // inserisco la lista delle recensioni nella richiesta, in modo che la jsp l'avrà a disposizione
        req.setAttribute("recensioniStruttura", recensioniStruttura);

        // inserisco la media della recensioni
        req.setAttribute("mediaRecensioni", media);

        // inserisco il nome della struttura che servirà alla RecensioniStrutturaGUI.jsp
        req.setAttribute("nomeStruttura", nomeStruttura);

        // passo il controllo alla jsp che stamperà la lista delle recensioni
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/RecensioniStrutturaGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
