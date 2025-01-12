package Application.InserimentoRecensione;

import Storage.Recensione.Recensione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "inserisciRecensioneServlet", value = "/inserisciRecensioneServlet")
public class InserisciRecensioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailRecensore = req.getParameter("emailRecensore");
        String descrizioneRecensione = req.getParameter("descrizioneRecensione");
        int votoRecensione = Integer.parseInt(req.getParameter("votoRecensione"));
        String dataRecensione = req.getParameter("dataRecensione");
        int codicePrenotazione = Integer.parseInt(req.getParameter("codicePrenotazione"));
        int numeroAlloggio = Integer.parseInt(req.getParameter("numeroAlloggio"));
        int codiceStruttura = Integer.parseInt(req.getParameter("codiceStruttura"));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        try{
            date = formatter.parse(dataRecensione);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Recensione recensione = new Recensione();

        recensione.setEmailRecensore(emailRecensore);
        recensione.setDescrizione(descrizioneRecensione);
        recensione.setVotoRecensione(votoRecensione);
        recensione.setDataRecensione(date);
        recensione.setNumeroAlloggio(numeroAlloggio);
        recensione.setCodicePrenotazione(codicePrenotazione);
        recensione.setFk_codiceStruttura(codiceStruttura);

        InserimentoRecensioneFacade inserimentoRecensioneFacade = new InserimentoRecensioneFacade();
        inserimentoRecensioneFacade.inserisciRecensione(req.getSession(), recensione);

        String nameAlloggio = req.getParameter("nameAlloggio");

        RequestDispatcher dispatcher = req.getRequestDispatcher("viewAlloggio.jsp?nameAlloggio=" + nameAlloggio);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
