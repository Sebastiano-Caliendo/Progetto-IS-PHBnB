package Application.GestioneAmministratore;

import Storage.Host.Host;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "modificaDatiSistemaStrutturaServlet", value = "/modificaDatiSistemaStrutturaServlet")
public class ModificaDatiSistemaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idStruttura = Integer.parseInt(req.getParameter("idStruttura"));
        //String fkHost = req.getParameter("fkHost");
        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("viaStruttura");
        String numCivico = req.getParameter("numCivicoStruttura");
        String citta = req.getParameter("cittaStruttura");
        int numAlloggi = Integer.parseInt(req.getParameter("numAlloggiStruttura"));
        String descrizione = req.getParameter("descrizioneStruttura");
        String urlImmagine = req.getParameter("urlImmagine");

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();

        gestioneAmministratoreFacade.modificaDatiSistemaStruttura((Host) req.getSession().getAttribute("host"), nomeStruttura, via, citta, numAlloggi ,numCivico, descrizione, urlImmagine, idStruttura, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisDatiSistemaGUI.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
