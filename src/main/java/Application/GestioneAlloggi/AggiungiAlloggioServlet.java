package Application.GestioneAlloggi;

import Application.GestioneStrutture.gestioneStrutturaFacade;
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

@WebServlet(name = "AggiungiAlloggioServlet", value = "/aggiungiAlloggioServlet")
public class AggiungiAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo tutti i valori dell'alloggio che si vuole inserire

        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));
        double prezzoNotte = Double.parseDouble(req.getParameter("prezzoNotte"));
        int numPostiLetto = Integer.parseInt(req.getParameter("numPostiLetto"));
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descrizione");
        String urlImmagine = req.getParameter("urlImmagine");

        // inserisco l'alloggio nel DB
        gestioneAlloggioFacade alloggioFacade = new gestioneAlloggioFacade();
        alloggioFacade.aggiungiAlloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);

        // prendiamo la struttura che servirà alla jsp VisAlloggiStruttureGUI.jsp
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);

        req.setAttribute("struttura", struttura);

        // prendo tutti gli alloggi della struttura
        List<Alloggio> alloggi = new ArrayList<>();
        alloggi = alloggioFacade.visualizzaAlloggi(struttura);

        // inserisco la lista degli alloggi di una struttura nella richiesta
        req.setAttribute("alloggi", alloggi);

        // ritorno alla jsp che mi fa vedere tutti gli alloggi della struttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/VisAlloggiStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
