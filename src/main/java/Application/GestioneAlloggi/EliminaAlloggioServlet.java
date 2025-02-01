package Application.GestioneAlloggi;

import Application.GestioneStrutture.GestioneStrutturaFacade;
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

@WebServlet(name = "EliminaAlloggioServlet", value = "/eliminaAlloggioServlet")
public class EliminaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo gli identificatori dell'alloggio che vogliamo eliminare

        String numeroAlloggio = req.getParameter("numeroAlloggio");
        String idStruttura = req.getParameter("idStruttura");

        // elimino l'alloggio dal DB
        gestioneAlloggioFacade alloggioFacade = new gestioneAlloggioFacade();
        alloggioFacade.eliminaAlloggio(numeroAlloggio, idStruttura);

        // prendiamo la struttura che servirà alla jsp VisAlloggiStruttureGUI.jsp
        GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);

        // inserisco la struttura nella richiesta

        req.setAttribute("struttura", struttura);

        // prendo tutti gli alloggi della struttura
        List<Alloggio> alloggi = new ArrayList<>();
        alloggi = alloggioFacade.visualizzaAlloggi(struttura);

        // inserisco la lista degli alloggi di una struttura nella richiesta
        req.setAttribute("alloggi", alloggi);

        // passo il controllo alla jsp ModificaAlloggio
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/VisAlloggiStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
