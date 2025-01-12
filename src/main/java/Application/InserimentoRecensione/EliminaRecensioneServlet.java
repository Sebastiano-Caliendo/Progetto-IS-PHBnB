package Application.InserimentoRecensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// solo admin potrà eliminare
@WebServlet(name = "eliminaRecensioneServlet", value = "/eliminaRecensioneServlet")
public class EliminaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idRecensione = Integer.parseInt(req.getParameter("idRecensione")); // type = "hidden" nella jsp

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        inserimentoRecensioneFacade.eliminaRecensione(req.getSession(), idRecensione);

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisDatiSistema.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
