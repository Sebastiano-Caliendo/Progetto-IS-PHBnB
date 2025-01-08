package Application.InserimentoRecensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "visualizzaRecensioniRicevuteServlet", value = "/visualizzaRecensioneRicevuteServlet")
public class VisualizzaRecensioniRicevuteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStruttura = req.getParameter("idStruttura");

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        inserimentoRecensioneFacade.visualizzaRecensioniRicevute(req.getSession(), idStruttura);

        String nameAlloggio = req.getParameter("nameAlloggio");

        RequestDispatcher dispatcher = req.getRequestDispatcher("listRecensioniAlloggio?nameAlloggio=" + nameAlloggio);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
