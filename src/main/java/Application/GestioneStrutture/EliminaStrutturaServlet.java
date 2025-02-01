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
import java.util.List;

@WebServlet(name = "EliminaStrutturaServlet", value = "/eliminaStrutturaServlet")
public class EliminaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo l'id della struttura che vogliamo eliminare

        String idStruttura = req.getParameter("idStruttura");

        // elimino la struttura dal DB
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.eliminaStruttura(idStruttura);

        List<Struttura> strutture = strutturaFacade.visualizzaStrutture(((Host) req.getSession().getAttribute("host")));
        req.setAttribute("listaStrutture", strutture);

        // passo il controllo alla jsp ModificaAlloggio
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/RiepilogoStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
