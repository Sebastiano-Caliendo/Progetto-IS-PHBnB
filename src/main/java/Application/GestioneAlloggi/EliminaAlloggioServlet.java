package Application.GestioneAlloggi;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EliminaAlloggioServlet", value = "/eliminaAlloggioServlet")
public class EliminaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // prendiamo gli identificatori dell'alloggio che vogliamo eliminare

        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));

        // elimino l'alloggio dal DB
        gestioneAlloggioFacade alloggioFacade = new gestioneAlloggioFacade();
        alloggioFacade.eliminaAlloggio(numeroAlloggio, idStruttura);

        // passo il controllo alla jsp ModificaAlloggio
        RequestDispatcher dispatcher = req.getRequestDispatcher("VisAlloggiStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
