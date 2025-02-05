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

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        boolean flag = gestioneAmministratoreFacade.modificaDatiSistemaUtente(oldEmailUtente, email, nome, cognome, password, citta, numeroCivico, via, recapitoTelefonico);

        String address;

        if(flag) {
            address = "Interface/indexAdminGUI.jsp?op=ok";
        } else {
            address = "Interface/indexAdminGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
