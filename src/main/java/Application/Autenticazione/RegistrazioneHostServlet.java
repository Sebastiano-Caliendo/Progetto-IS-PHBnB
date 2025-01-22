package Application.Autenticazione;

import Storage.Host.Host;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        String psw = sha1Function(password);

        Host h = new Host(email, nome, cognome, psw, LocalDate.parse(dataNascita), recapitoTelefonico);

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());

        String address;

        if(autenticazioneFacade.registrazioneHost(h)) {
            address = "Interface/index.jsp";
        } else {
            address = "Interface/registrazioneHostGUI.jsp?error=1";
        }

        resp.sendRedirect(address);
    }

    private static String sha1Function(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            StringBuilder hexString = new StringBuilder();
            for (byte b : md.digest(password.getBytes(StandardCharsets.UTF_8))) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
