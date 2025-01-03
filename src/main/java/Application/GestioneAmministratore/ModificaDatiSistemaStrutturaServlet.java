package Application.GestioneAmministratore;

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
        String fkHost = req.getParameter("fkHost");
        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("viaStruttura");
        int numCivico = Integer.parseInt(req.getParameter("numCivicoStruttura"));
        String citta = req.getParameter("cittaStruttura");
        int numAlloggi = Integer.parseInt(req.getParameter("numAlloggiStruttura"));
        String descrizione = req.getParameter("descrizioneStruttura");

        Struttura struttura = new Struttura();
        struttura.setNomeStruttura(nomeStruttura);
        struttura.setDescrizione(descrizione);
        struttura.setIdStruttura(idStruttura);
        struttura.setCitta(citta);
        struttura.setVia(via);
        struttura.setFkHost(fkHost);
        struttura.setNumCivico(numCivico);
        struttura.setNumAlloggi(numAlloggi);

        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();

        gestioneAmministratoreFacade.modificaDatiSistemaStruttura(idStruttura, struttura, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleStrutture.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
