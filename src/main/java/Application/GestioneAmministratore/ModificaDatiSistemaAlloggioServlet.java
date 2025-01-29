package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "modificaDatiSistemaAlloggioServlet", value = "/modificaDatiSistemaAlloggioServlet")
public class ModificaDatiSistemaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldNumeroAlloggio = req.getParameter("oldNumAlloggio");
        String numeroAlloggio = req.getParameter("numAlloggio");
        String fkStruttura = req.getParameter("fkStruttura");
        String prezzoNotte = req.getParameter("prezzoNotte");
        String postiLetto = req.getParameter("postiLetto");
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descAlloggio");

        /*Alloggio alloggio = new Alloggio();

        alloggio.setTipoAlloggio(tipoAlloggio);
        alloggio.setDescrizione(descrizione);
        alloggio.setPostiletto(postiLetto);
        alloggio.setPrezzoNotte(prezzoNotte);*/

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaAlloggio(numeroAlloggio, prezzoNotte, postiLetto, tipoAlloggio, descrizione, oldNumeroAlloggio, fkStruttura);

        resp.sendRedirect(req.getContextPath() + "/Interface/indexAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
