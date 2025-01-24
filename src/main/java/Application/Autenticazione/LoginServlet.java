package Application.Autenticazione;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String tipo = req.getParameter("tipo");

        String address;

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());
        boolean flag;

        if(tipo.equals("user")) {
            flag = autenticazioneFacade.login(email, password, tipo);

            if(flag)
                address = "Interface/index.jsp";
            else
                address = "Interface/loginUtenteGUI.jsp?error=1";
        } else {
            flag = autenticazioneFacade.login(email, password, tipo);

            if(flag)
                address = "Interface/index.jsp";
            else
                address = "Interface/loginHostGUI.jsp?error=1";
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
}
