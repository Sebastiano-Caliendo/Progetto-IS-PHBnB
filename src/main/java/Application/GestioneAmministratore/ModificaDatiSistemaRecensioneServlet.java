package Application.GestioneAmministratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "modificaDatiSistemaRecensioneServlet", value = "/modificaDatiSistemaRecensioneServlet")
public class ModificaDatiSistemaRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailRecensore = req.getParameter("emailRecensore");
        String descrizioneRecensione = req.getParameter("descrizioneRecensione");
        int votoRecensione = Integer.parseInt(req.getParameter("votoRecensione"));
        String dataRecensione = req.getParameter("dataRecensione");
        int codicePrenotazione = req.getIntHeader("codicePrenotazione");
        int numAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));

        Date dateReview;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try{
            dateReview = (Date) format.parse(dataRecensione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        GestioneAmministratoreFacade gestioneAmministratoreFacade = new GestioneAmministratoreFacade();
        gestioneAmministratoreFacade.modificaDatiSistemaRecensione(emailRecensore, descrizioneRecensione, votoRecensione, dateReview, codicePrenotazione, numAlloggio, req.getSession());

        RequestDispatcher dispatcher = req.getRequestDispatcher("VisDatiSistema.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
