package Application.GestioneAlloggi;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/* questa servlet serve perchè quando andiamo a modificare un alloggio, l'host dovrà prima osservare un form
   pre-compilato con i vecchi valori che potranno essere modificati. */
@WebServlet(name = "RedirectToModificaAlloggioServlet", value = "/redirectToModificaAlloggioServlet")
public class RedirectToModificaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo gli identificatori dell'alloggio che vogliamo modificare

        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));
        int fkStruttura = Integer.parseInt(req.getParameter("idStruttura"));

        //prendiamo i dati dell'alloggio che vogliamo modificare
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        Alloggio alloggio = alloggioDAO.doRetrieveById(numeroAlloggio, fkStruttura);

        //aggiungiamo l'alloggio nella richiesta
        req.setAttribute("alloggio", alloggio);

        // passo il controllo alla jsp ModificaAlloggio
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/ModificaAlloggioGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
