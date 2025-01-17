package Application.PrenotazioneAlloggio;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/modificaPrenotazione")
public class ModificaPrenotazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String codPrenotazione = req.getParameter("codPrenotazione");
        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numPostiLetto = req.getParameter("numPostiLetto");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();
        prenotazioneAlloggioFacade.modificaPrenotazione(LocalDate.parse(dataCheckIn), LocalDate.parse(dataCheckOut), Integer.parseInt(codPrenotazione), Integer.parseInt(numPostiLetto));

        String address = "";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
