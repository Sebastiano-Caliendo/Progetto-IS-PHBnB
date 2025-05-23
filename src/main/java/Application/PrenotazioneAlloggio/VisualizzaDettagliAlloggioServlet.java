package Application.PrenotazioneAlloggio;

import Storage.Alloggio.Alloggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/visualizzaDettagliAlloggio")
public class VisualizzaDettagliAlloggioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("callByServlet", "yes");

        String numAlloggio = req.getParameter("numAlloggio");
        String codStruttura = req.getParameter("codStruttura");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();

        Alloggio a = prenotazioneAlloggioFacade.visualizzaDettagliAlloggio(numAlloggio, codStruttura);

        req.setAttribute("alloggioSelezionato", a);

        String address = "Interface/dettagliAlloggioGUI.jsp";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
