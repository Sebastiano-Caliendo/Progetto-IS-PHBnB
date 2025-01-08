package Application.InserimentoRecensione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "visualizzaRecensioniPubblicateServlet", value = "/visualizzaRecensioniPubblicateServlet")
public class VisualizzaRecensioniPubblicateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailRecensore = req.getParameter("emailRecensore");

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        inserimentoRecensioneFacade.visualizzaRecensioniPubblicate(req.getSession(), emailRecensore);

        RequestDispatcher dispatcher = req.getRequestDispatcher("listRecensioni.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
