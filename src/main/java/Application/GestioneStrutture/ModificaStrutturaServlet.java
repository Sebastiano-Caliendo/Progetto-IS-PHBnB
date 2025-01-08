package Application.GestioneStrutture;

import Application.GestioneAlloggi.gestioneAlloggioFacade;
import Storage.Alloggio.Alloggio;
import Storage.Host.Host;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "ModificaStrutturaServlet", value = "/modificaStrutturaServlet")
public class ModificaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // prendiamo l'oggetto host dalla sessione perchè ci serve l'fkHost da associare alla struttura
        // L'host nella pagina modifica.jsp non inserirà il campo fk_host
        Host host = (Host) req.getSession().getAttribute("host");

        // prende il vecchio id della struttura che sarà sicuramente cambiato perchè id_struttura è auto_increment
        int oldIdStruttura = Integer.parseInt(req.getParameter("oldIdStruttura"));

        // prendiamo tutti i valori della struttura che si vuole inserire

        // l'idStruttura è auto_increment e quindi l'host non lo inserirà

        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("via");
        String citta = req.getParameter("citta");
        int numAlloggi = Integer.parseInt(req.getParameter("numAlloggi"));
        String descrizione = req.getParameter("descrizione");
        String urlImmagine = req.getParameter("urlImmagine");

        // creo la struttura che voglio inserire
        Struttura struttura = new Struttura();
        struttura.setFkHost(host.getEmail());
        struttura.setDescrizione(descrizione);
        struttura.setNumAlloggi(numAlloggi);
        struttura.setNomeStruttura(nomeStruttura);
        struttura.setCitta(citta);
        struttura.setVia(via);
        struttura.setUrlImmagine(urlImmagine);

        // elimino la vecchia struttura dal DB ed inserisco la struttura modificata
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.modificaStruttura(struttura, oldIdStruttura);

        // ritorno alla jsp che mi fa vedere tutti gli alloggi della struttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("RiepilogoStrutture.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
