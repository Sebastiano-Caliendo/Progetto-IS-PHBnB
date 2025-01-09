package Application.GestioneStrutture;

import Storage.Host.Host;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RiepilogoStruttureServlet", value = "/riepilogoStruttureServlet")
public class RiepilogoStruttureServlet extends HttpServlet {

    // servlet che restituisce la lista delle strutture di un host //
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // quando l'host accede, lo salviamo nella sessione,
        // quindi prendiamo le info dell'host dalla sessione
        Host host = (Host) req.getSession().getAttribute("host");

        // cerco tutte le strutture associate all'host
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        List<Struttura> strutture = strutturaFacade.visualizzaStrutture(host);

        // inserisco le strutture nella richiesta, serviranno alla prossima jsp che dovrà mostrate tutte le strutture
        // dell'host
        req.setAttribute("listaStrutture", strutture);

        // passo il controllo alla jsp che mostrerà il riepilogo delle strutture
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/RiepilogoStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
