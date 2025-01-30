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
        String numAlloggio = req.getParameter("numAlloggio");
        String fkStruttura = req.getParameter("fkStruttura");

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.cancellazioneDatiSitemaAlloggio(numAlloggio, fkStruttura);

        resp.sendRedirect(req.getContextPath() + "/Interface/indexAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
