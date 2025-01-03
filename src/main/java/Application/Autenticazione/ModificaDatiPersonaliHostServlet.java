package Application.Autenticazione;

import Storage.Host.Host;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/modificaDatiPersonaliHost")
public class ModificaDatiPersonaliHostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String password = req.getParameter("password");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");

        Host h = (Host) req.getSession().getAttribute("Utente");

        String address = "areaHost.jsp";

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());
        autenticazioneFacade.modificaDatiPersonaliHost(h, email, nome, cognome, password, recapitoTelefonico);

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
