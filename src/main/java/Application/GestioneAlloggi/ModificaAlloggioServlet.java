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

@WebServlet(name = "ModificaAlloggioServlet", value = "/modificaAlloggioServlet")
public class ModificaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // prende il vecchio numero alloggio che potrebbe essere modificato
        int oldNumeroAlloggio = Integer.parseInt(req.getParameter("oldNumeroAlloggio"));

        // prendiamo tutti i valori dell'alloggio che si vuole inserire
        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));
        // l'idStruttura non potrà essere modificato, quindi sarà uguale al valore passato
        // dalla recente jsp.
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));
        double prezzoNotte = Double.parseDouble(req.getParameter("prezzoNotte"));
        int numPostiLetto = Integer.parseInt(req.getParameter("numPostiLetto"));
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descrizione");
        String urlImmagine = req.getParameter("urlImmagine");

        // creo l'alloggio che voglio inserire
        Alloggio alloggio = new Alloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);

        // elimino il vecchio alloggio dal DB ed inserisco l'alloggio modificato
        gestioneAlloggioFacade alloggioFacade = new gestioneAlloggioFacade();
        alloggioFacade.modificaAlloggio(alloggio, oldNumeroAlloggio, idStruttura);

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
        doGet(req, resp);
    }
}
