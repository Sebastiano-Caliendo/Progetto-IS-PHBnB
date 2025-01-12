package Application.Autenticazione;

import Storage.Host.Host;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@WebServlet("/registrazioneHost")
public class RegistrazioneHostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String password = req.getParameter("password");
        String dataNascita = req.getParameter("dataNascita");
        String recapitoTelefonico = req.getParameter("recapitoTelefonico");

        String address = "areaHost.jsp";

        //Host h = new Host(email, nome, cognome, password, (Date) dataNascita, recapitoTelefonico);

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());
        //autenticazioneFacade.registrazioneHost(h);

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
