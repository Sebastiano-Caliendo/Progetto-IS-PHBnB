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
        String idStruttura = req.getParameter("idStruttura");
        String fkHost = req.getParameter("fkHost");
        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("viaStruttura");
        String numCivico = req.getParameter("numCivicoStruttura");
        String citta = req.getParameter("cittaStruttura");
        String numAlloggi = req.getParameter("numAlloggiStruttura");
        String descrizione = req.getParameter("descrizioneStruttura");
        String urlImmagine = req.getParameter("urlImmagine");

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaStruttura(fkHost, nomeStruttura, via, citta, numAlloggi ,numCivico, descrizione, urlImmagine, idStruttura);

        resp.sendRedirect(req.getContextPath() + "/Interface/indexAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
