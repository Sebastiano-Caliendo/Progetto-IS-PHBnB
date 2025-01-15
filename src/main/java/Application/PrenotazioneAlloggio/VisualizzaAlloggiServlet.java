package Application.PrenotazioneAlloggio;

import Storage.Alloggio.Alloggio;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/visualizzaAlloggi")
public class VisualizzaAlloggiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String destinazione = req.getParameter("destinazione");
        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numPostiLetto = req.getParameter("numPostiLetto");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();

        List<Alloggio> alloggi = prenotazioneAlloggioFacade.visualizzaListaAlloggi(LocalDate.parse(dataCheckIn), LocalDate.parse(dataCheckOut), destinazione, Integer.parseInt(numPostiLetto));
        req.setAttribute("alloggiPrenotabili", alloggi);

        String address = "alloggiPrenotabiliGUI.jsp";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
