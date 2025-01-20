package Application.GestioneStrutture;

import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "AggiungiStrutturaServlet", value = "/aggiungiStrutturaServlet")
public class AggiungiStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");
        // prendiamo l'host dalla sessione (il campo fk_host non verrà inserito)
        //Host host = (Host) req.getSession().getAttribute("host");
        // Debug per stampare tutti i parametri della richiesta
        for (String param : req.getParameterMap().keySet()) {
            System.out.println(param + " = " + req.getParameter(param));
        }
        
        HostDAO hostDAO = new HostDAO();
        Host host = hostDAO.doRetrieveById("pintocarlo09@gmail.com");

        // prendiamo tutti i campi insriti dall'host
        String nomeStruttura = req.getParameter("nomeStruttura");
        String via = req.getParameter("via");
        String citta = req.getParameter("citta");
        String numCivico = req.getParameter("numCivico");
        String descrizione = req.getParameter("descrizione");
        //String urlImmagine = req.getParameter("urlImmagine");

        //prende il type file
        Part filePart = req.getPart("urlImmagine");

        // prendi nome del file caricato (serve solo per catturare l'estensione)
        String fileName = filePart.getSubmittedFileName();

        // prendi estensione del file caricato
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        //dove dev'essere inserita l'immagine
        String directory = "Interface/img/strutture";

        //percorso
        String filePath =
                getServletContext().getRealPath("/" + directory) + "\\" + nomeStruttura + "." + fileExtension;

        // salva file (inserirlo nel percorso passato [cartella])

        filePart.write(filePath);
        /*File savedFile = new File(filePath);
        if (savedFile.exists()) {
            System.out.println("File salvato correttamente in: " + filePath);
        } else {
            System.out.println("Errore: il file non è stato salvato!");
        }*/


        // setta url nel prodotto inserito
        //p.setUrlImmagine(directory + p.getId_Prodotto() + "." + fileExtension);
        String urlImmagine = directory + "/" + nomeStruttura + "." + fileExtension;

        // setta url nel db
        //prodottoDAO.setUrlImmagineByid(p.getId_Prodotto(), p.getUrlImmagine());

        // inseriamo la struttura nel DB
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.aggiungiStruttura(host, nomeStruttura, via, citta, numCivico, descrizione, urlImmagine);

        // passo il controllo alla jsp che mostrerà il riepilogo delle strutture
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/RiepilogoStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
