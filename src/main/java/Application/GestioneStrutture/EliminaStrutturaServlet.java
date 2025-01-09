package Application.GestioneStrutture;

import Application.GestioneAlloggi.gestioneAlloggioFacade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "EliminaStrutturaServlet", value = "/eliminaStrutturaServlet")
public class EliminaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo l'id della struttura che vogliamo eliminare

        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));

        // elimino la struttura dal DB
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.eliminaStruttura(idStruttura);

        // passo il controllo alla jsp ModificaAlloggio
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/RiepilogoStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
