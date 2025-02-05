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

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        boolean flag = gestioneAmministratoreFacade.modificaDatiSistemaStruttura(fkHost, nomeStruttura, via, citta, numAlloggi ,numCivico, descrizione, idStruttura);

        String address;

        if(flag) {
            address = "Interface/indexAdminGUI.jsp?op=ok";
        } else {
            address = "Interface/indexAdminGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
