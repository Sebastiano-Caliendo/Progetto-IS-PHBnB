package Application.GestioneAmministratore;

import Storage.Utente.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "modificaDatiSistemaUtenteServlet", value = "/modificaDatiSistemaUtenteServlet")
public class ModificaDatiSistemaUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("emailUtente");
        String nome = req.getParameter("nomeUtente");
        String cognome = req.getParameter("cognomeUtente");
        String password = req.getParameter("passwordUtente");
        String citta = req.getParameter("cittaUtente");
        String numeroCivico = req.getParameter("numeroCivicoUtente");
        String via = req.getParameter("viaUtente");
        String dataNascita = req.getParameter("dataNascitaUtente");
        String recapitoTelefonico = req.getParameter("recapitoTelefonicoUtente");
        boolean isAdmin = Boolean.parseBoolean(req.getParameter("isAdminUtente"));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dataNascitaUtente;

        try{
            dataNascitaUtente = (Date) formatter.parse(dataNascita);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Utente utente = new Utente();

        utente.setAdmin(isAdmin);
        utente.setCitta(citta);
        utente.setCognome(cognome);
        utente.setNome(nome);
        utente.setEmail(email);
        utente.setPassword(password);
        utente.setNumeroCivico(numeroCivico);
        utente.setVia(via);
        utente.setRecapitoTelefonico(recapitoTelefonico);
        utente.setDataNascita(dataNascitaUtente);

        gestioneAmministratoreFacade gestioneAmministratoreFacade = new gestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaUtente(utente, email, nome, cognome, password, citta, numeroCivico, via, dataNascitaUtente, recapitoTelefonico, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisTotaleUtenti.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
