package Application.GestioneAmministratore;

import Storage.Host.Host;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "modificaDatiSistemaHostServlet", value = "/modificaDatiSistemaHostServlet")
public class ModificaDatiSistemaHostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailHost");
        String nome = req.getParameter("nomeHost");
        String cognome = req.getParameter("cognomeHost");
        String password = req.getParameter("passwordHost");
        String recapitoTelefonico = req.getParameter("recapitoTelHost");

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();

        Host host = new Host();

        host.setEmail(email);
        host.setNome(nome);
        host.setCognome(cognome);
        host.setPassword(password);
        host.setRecapitoTelefonico(recapitoTelefonico);

        gestioneAmministratoreFacade.modificaDatiSistemaHost(host, email, nome, cognome, password, recapitoTelefonico, req.getSession());

        resp.sendRedirect(req.getContextPath() + "/Interface/indexAdmin.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
