package Application.GestioneAlloggi;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RedirectToAggiungiAlloggioServlet", value = "/redirectToAggiungiAlloggioServlet")
public class RedirectToAggiungiAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo l'id della struttura

        String idStruttura = req.getParameter("idStruttura");

        //aggiungiamo l'id della struttura nella richiesta
        req.setAttribute("idStruttura", idStruttura);

        // passo il controllo alla jsp ModificaAlloggio
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/InserisciAlloggioGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

