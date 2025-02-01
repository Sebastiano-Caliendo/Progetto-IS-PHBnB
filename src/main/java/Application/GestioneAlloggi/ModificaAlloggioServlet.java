package Application.GestioneAlloggi;

import Application.GestioneStrutture.GestioneStrutturaFacade;
import Storage.Alloggio.Alloggio;
import Storage.Struttura.Struttura;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModificaAlloggioServlet", value = "/modificaAlloggioServlet")
public class ModificaAlloggioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("callByServlet", "yes");

        // prende il vecchio numero alloggio che potrebbe essere modificato
        String oldNumeroAlloggio = req.getParameter("oldNumeroAlloggio");

        // prendiamo tutti i valori dell'alloggio che si vuole inserire
        String numeroAlloggio = req.getParameter("numeroAlloggio");
        // l'idStruttura non potrà essere modificato, quindi sarà uguale al valore passato
        // dalla recente jsp.
        String idStruttura = req.getParameter("idStruttura");
        String prezzoNotte = req.getParameter("prezzoNotte");
        String numPostiLetto = req.getParameter("numPostiLetto");
        String tipoAlloggio = req.getParameter("tipoAlloggio");
        String descrizione = req.getParameter("descrizione");
        //String urlImmagine = req.getParameter("urlImmagine");

        //prende il type file
        Part filePart = req.getPart("urlImmagine");

        // prendi nome del file caricato (serve solo per catturare l'estensione)
        String fileName = filePart.getSubmittedFileName();

        // prendi estensione del file caricato
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        //dove dev'essere inserita l'immagine
        String directory = "Interface/img/alloggi";

        //percorso
        String filePath =
                getServletContext().getRealPath("/" + directory) + "\\" + idStruttura + numeroAlloggio + "." + fileExtension;

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
        String urlImmagine = directory + "/" + idStruttura + numeroAlloggio + "." + fileExtension;

        // setta url nel db
        //prodottoDAO.setUrlImmagineByid(p.getId_Prodotto(), p.getUrlImmagine());



        // elimino il vecchio alloggio dal DB ed inserisco l'alloggio modificato
        GestioneAlloggioFacade alloggioFacade = new GestioneAlloggioFacade();
        alloggioFacade.modificaAlloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine, oldNumeroAlloggio, idStruttura);

        // prendiamo la struttura che servirà alla jsp VisAlloggiStruttureGUI.jsp
        GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);

        req.setAttribute("struttura", struttura);

        // prendo tutti gli alloggi della struttura
        List<Alloggio> alloggi = new ArrayList<>();
        alloggi = alloggioFacade.visualizzaAlloggi(struttura);

        // inserisco la lista degli alloggi di una struttura nella richiesta
        req.setAttribute("alloggi", alloggi);

        // ritorno alla jsp che mi fa vedere tutti gli alloggi della struttura
        RequestDispatcher dispatcher = req.getRequestDispatcher("Interface/VisAlloggiStruttureGUI.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
