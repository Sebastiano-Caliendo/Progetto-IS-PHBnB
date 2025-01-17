package Application.PrenotazioneAlloggio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/eliminaPrenotazione")
public class EliminaPrenotazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String codPrenotazione = req.getParameter("codPrenotazione");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        prenotazioneAlloggioFacade.eliminaPrenotazione(Integer.parseInt(codPrenotazione));

        String address = "";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
