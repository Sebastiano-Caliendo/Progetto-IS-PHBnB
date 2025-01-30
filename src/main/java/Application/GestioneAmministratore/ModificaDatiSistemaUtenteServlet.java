package Application.GestioneAmministratore;

import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "modificaDatiSistemaUtenteServlet", value = "/modificaDatiSistemaUtenteServlet")
public class ModificaDatiSistemaUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String oldEmailUtente = req.getParameter("oldEmailUtente");
        String email = req.getParameter("emailUtente");
        String nome = req.getParameter("nomeUtente");
        String cognome = req.getParameter("cognomeUtente");
        String password = req.getParameter("passwordUtente");
        String citta = req.getParameter("cittaUtente");
        String numeroCivico = req.getParameter("numeroCivicoUtente");
        String via = req.getParameter("viaUtente");
        String recapitoTelefonico = req.getParameter("recapitoTelefonicoUtente");

        /*Utente utente = new Utente();

        utente.setAdmin(isAdmin);
        utente.setCitta(citta);
        utente.setCognome(cognome);
        utente.setNome(nome);
        utente.setEmail(email);
        utente.setPassword(password);
        utente.setNumeroCivico(numeroCivico);
        utente.setVia(via);
        utente.setRecapitoTelefonico(recapitoTelefonico);*/

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaUtente(oldEmailUtente, email, nome, cognome, password, citta, numeroCivico, via, recapitoTelefonico);


        resp.sendRedirect(req.getContextPath() + "/Interface/indexAdmin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
