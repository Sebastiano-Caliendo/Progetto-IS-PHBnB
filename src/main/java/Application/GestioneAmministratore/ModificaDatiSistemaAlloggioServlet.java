package Application.GestioneAmministratore;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
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
        int numeroAlloggio = Integer.parseInt(req.getParameter("numAlloggio"));
        int fkStruttura = Integer.parseInt(req.getParameter("fkStruttura"));
        double prezzoNotte = Double.parseDouble(req.getParameter("prezzoNotte"));
        int postiLetto = Integer.parseInt(req.getParameter("postiLetto"));
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descAlloggio");

        Alloggio alloggio = new Alloggio();

        alloggio.setTipoAlloggio(tipoAlloggio);
        alloggio.setDescrizione(descrizione);
        alloggio.setPostiletto(postiLetto);
        alloggio.setPrezzoNotte(prezzoNotte);

        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaAlloggio(alloggio, numeroAlloggio, fkStruttura, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleAlloggi.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
