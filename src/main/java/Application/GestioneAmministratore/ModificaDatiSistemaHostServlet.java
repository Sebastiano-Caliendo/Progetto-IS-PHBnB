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

        String oldEmailHost = req.getParameter("oldEmailHost");
        String email = req.getParameter("emailHost");
        String nome = req.getParameter("nomeHost");
        String cognome = req.getParameter("cognomeHost");
        String password = req.getParameter("passwordHost");
        String recapitoTelefonico = req.getParameter("recapitoTelHost");

        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        boolean flag = gestioneAmministratoreFacade.modificaDatiSistemaHost(oldEmailHost, email, nome, cognome, password, recapitoTelefonico);

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
