package Application.Autenticazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isUser = Boolean.parseBoolean(req.getParameter("isUser"));

        String address;

        if(isUser) {
            address = "index.jsp";
        } else {
            address = "areaHost.jsp";
        }

        AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade(req.getSession());
        autenticazioneFacade.login(email, password, isUser);

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

}
