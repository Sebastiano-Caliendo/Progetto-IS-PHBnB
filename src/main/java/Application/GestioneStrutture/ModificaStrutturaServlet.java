package Application.GestioneStrutture;

import Storage.Host.Host;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ModificaStrutturaServlet", value = "/modificaStrutturaServlet")
public class ModificaStrutturaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");

        /* Debug per stampare tutti i parametri della richiesta
        for (String param : req.getParameterMap().keySet()) {
            System.out.println(param + " = " + req.getParameter(param));
        }*/

        // prendiamo l'oggetto host dalla sessione perchè ci serve l'fkHost da associare alla struttura
        // L'host nella pagina modifica.jsp non inserirà il campo fk_host
        Host host = (Host) req.getSession().getAttribute("host");
        /*HostDAO hostDAO = new HostDAO();
        Host host = hostDAO.doRetrieveById("pintocarlo09@gmail.com");*/

        // prende il vecchio id della struttura che sarà sicuramente cambiato perchè id_struttura è auto_increment
        String oldIdStruttura = req.getParameter("idStruttura");

        // prendiamo tutti i valori della struttura che si vuole inserire

        // l'idStruttura è auto_increment e quindi l'host non lo inserirà

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


        // elimino la vecchia struttura dal DB ed inserisco la struttura modificata
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        strutturaFacade.modificaStruttura(host, nomeStruttura, via, citta, numCivico, descrizione, urlImmagine, oldIdStruttura);

        List<Struttura> strutture = strutturaFacade.visualizzaStrutture(host);
        req.setAttribute("listaStrutture", strutture);

        // ritorno alla jsp che mi fa vedere tutti gli alloggi della struttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/RiepilogoStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
