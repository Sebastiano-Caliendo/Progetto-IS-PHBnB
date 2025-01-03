package Application.GestioneAmministratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "cancellazioneDatiSistemaAlloggioServlet", value = "/cancellazioneDatiSistemaAlloggioServlet")
public class CancellazioneDatiSistemaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numAlloggio = Integer.parseInt(req.getParameter("numAlloggio"));
        int fkStruttura = Integer.parseInt(req.getParameter("fkStruttura"));

        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        gestioneAmministratoreFacade.cancellazioneDatiSitemaAlloggio(numAlloggio, fkStruttura, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleAlloggi.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
