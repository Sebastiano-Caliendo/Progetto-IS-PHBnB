package Application.GestioneStrutture;

import Application.GestioneAlloggi.gestioneAlloggioFacade;
import Storage.Alloggio.Alloggio;
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

@WebServlet(name = "SelezionaStrutturaServlet", value = "/selezionaStrutturaServlet")
public class SelezionaStrutturaServlet extends HttpServlet {

    // servlet che restituisce la lista degli alloggi di una struttura //
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendo l'id della struttura che voglio selezionare (voglio visualizzare i suoi alloggi)
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));

        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        gestioneAlloggioFacade alloggioFacade = new gestioneAlloggioFacade();

        // prendo la struttura che voglio selezionare
        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);

        // prendo tutti gli alloggi della struttura
        List<Alloggio> alloggi = new ArrayList<>();
        alloggi = alloggioFacade.visualizzaAlloggi(struttura);

        // inserisco la lista degli alloggi di una struttura nella richiesta
        req.setAttribute("alloggi", alloggi);

        // inserisco la struttura che servirà alla jsp per : avere id e nome della struttura
        req.setAttribute("struttura", struttura);

        // passo il controllo alla jsp che mostrerà la lista degli alloggi di una struttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/VisAlloggiStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
