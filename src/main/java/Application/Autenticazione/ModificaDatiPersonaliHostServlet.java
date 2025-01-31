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

        req.setAttribute("callByServlet", "yes");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String password = req.getParameter("password");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");

        Host h = (Host) req.getSession().getAttribute("Utente");

        String address;

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());

        if(autenticazioneFacade.modificaDatiPersonaliHost(h, email, nome, cognome, password, recapitoTelefonico)) {
            address = "Interface/areaHostGUI.jsp";
        } else {
            address = "Interface/modificaDatiHostGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
