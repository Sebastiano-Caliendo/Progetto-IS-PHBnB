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
        String dataNascita = req.getParameter("dataNascitaHost");
        String recapitoTelefonico = req.getParameter("recapitoTelHost");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dataNascitaDate;

        try {
            dataNascitaDate = formatter.parse(dataNascita);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        Host host = new Host();

        host.setNome(nome);
        host.setCognome(cognome);
        host.setPassword(password);
        host.setDataNascita(dataNascitaDate);
        host.setRecapitoTelefonico(recapitoTelefonico);

        gestioneAmministratoreFacade.modificaDatiSistemaHost(host, email, nome, cognome, password, (java.sql.Date) dataNascitaDate, recapitoTelefonico, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleHost.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
