package Application.GestioneAlloggi;

import Storage.Alloggio.Alloggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AggiungiAlloggioServlet", value = "/aggiungiAlloggioServlet")
public class AggiungiAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // prendiamo tutti i valori dell'alloggio che si vuole inserire

        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));
        double prezzoNotte = Double.parseDouble(req.getParameter("prezzoNotte"));
        int numPostiLetto = Integer.parseInt(req.getParameter("numPostiLetto"));
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descrizione");
        String urlImmagine = req.getParameter("urlImmagine");

        // creo l'alloggio che voglio inserire
        Alloggio alloggio = new Alloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);

        // inserisco l'alloggio nel DB
        gestioneAlloggioFacade alloggioFacade = new gestioneAlloggioFacade();
        alloggioFacade.aggiungiAlloggio(alloggio);

        // ritorno alla jsp che mi fa vedere tutti gli alloggi della struttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisAlloggiStruttura.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
