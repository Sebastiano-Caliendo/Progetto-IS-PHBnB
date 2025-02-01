package Application.GestioneStrutture;

import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/* questa servlet serve perchè quando andiamo a modificare una struttura, l'host dovrà prima osservare un form
   pre-compilato con i vecchi valori che potranno essere modificati. */
@WebServlet(name = "RedirectToModificaStrutturaServlet", value = "/redirectToModificaStrutturaServlet")
public class RedirectToModificaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");

        // prendiamo l'identificatore della struttura che vogliamo modificare

        String idStruttura = req.getParameter("idStruttura");

        //prendiamo i dati della struttura che vogliamo modificare
        GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);

        //System.out.println("url Immagine in redirect : " + struttura.getUrlImmagine());

        //aggiungiamo la struttura nella richiesta
        req.setAttribute("struttura", struttura);

        // passo il controllo alla jsp ModificaStruttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/ModificaStrutturaGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
