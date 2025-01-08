package Application.InserimentoRecensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "eliminaRecensioneServlet", value = "/eliminaRecensioneServlet")
public class EliminaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailRecensore = req.getParameter("emailRecensore");
        String codicePrenotazione = req.getParameter("codicePrenotazione");
        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        inserimentoRecensioneFacade.eliminaRecensione(req.getSession(), emailRecensore, codicePrenotazione, numeroAlloggio);

        String nameAlloggio = req.getParameter("nameAlloggio");

        RequestDispatcher dispatcher = req.getRequestDispatcher("viewAlloggio.jsp?nameAlloggio=" + nameAlloggio);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
