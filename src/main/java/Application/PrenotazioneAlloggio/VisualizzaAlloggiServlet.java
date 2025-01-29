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

        req.setAttribute("callByServlet", "yes");

        String destinazione = req.getParameter("luogo");
        String dataCheckIn = req.getParameter("dataCheckIn");
        String dataCheckOut = req.getParameter("dataCheckOut");
        String numOspiti = req.getParameter("numOspiti");

        PrenotazioneAlloggioFacade prenotazioneAlloggioFacade = new PrenotazioneAlloggioFacade();

        List<Alloggio> alloggi = prenotazioneAlloggioFacade.visualizzaListaAlloggi(dataCheckIn, dataCheckOut, destinazione, numOspiti);

        if(!alloggi.isEmpty()) {
            req.setAttribute("dataCheckIn", dataCheckIn);
            req.setAttribute("dataCheckOut", dataCheckOut);
            req.setAttribute("numOspiti", numOspiti);
            req.setAttribute("alloggiPrenotabili", alloggi);
        }

        String address = "Interface/alloggiPrenotabiliGUI.jsp";

        RequestDispatcher rd = req.getRequestDispatcher(address);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
