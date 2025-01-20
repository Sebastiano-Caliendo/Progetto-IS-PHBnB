package Application.GestioneAlloggi;

import Application.GestioneStrutture.gestioneStrutturaFacade;
import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;

import java.util.ArrayList;
import java.util.List;

public class gestioneAlloggioFacade {

    public List<Alloggio> visualizzaAlloggi(Struttura struttura) {
        // in strutturaDAO abbiamo un metodo che restituisce una lista di alloggi data una struttura

        List<Alloggio> alloggi = new ArrayList<>();
        StrutturaDAO strutturaDAO = new StrutturaDAO();

        alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);

        return alloggi;
    }

    public int aggiungiAlloggio(int numeroAlloggio, int idStruttura, double prezzoNotte, int numPostiLetto, String tipoAlloggio, String descrizione, String urlImmagine) {
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        // creo l'alloggio che voglio inserire
        Alloggio alloggio = new Alloggio(numeroAlloggio, strutturaFacade.returnStruttura(idStruttura), prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);
        AlloggioDAO alloggioDAO = new AlloggioDAO();

        Struttura struttura = strutturaFacade.returnStruttura(idStruttura);
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        strutturaDAO.doUpdateNumeroAlloggi((struttura.getNumAlloggi()+1), idStruttura);


        List<Integer> verifica = alloggioDAO.doSave(alloggio);
        if(verifica.get(1) == alloggio.getNumeroAlloggio()){
            if(verifica.get(2) == alloggio.getStruttura().getIdStruttura())
                return 1;
        }
        return 0;
    }

    public int modificaAlloggio(int numeroAlloggio, int idStruttura, double prezzoNotte, int numPostiLetto, String tipoAlloggio, String descrizione, String urlImmagine, int oldNumeroAlloggio, int fkStruttura) {
        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        Alloggio alloggio = new Alloggio(numeroAlloggio, strutturaFacade.returnStruttura(idStruttura), prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        alloggioDAO.doDelete(oldNumeroAlloggio, fkStruttura);
        return aggiungiAlloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);
    }

    public List<Integer> eliminaAlloggio(int numeroAlloggio, int fkStruttura) {
        AlloggioDAO alloggioDAO = new AlloggioDAO();

        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        Struttura struttura = strutturaFacade.returnStruttura(fkStruttura);
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        strutturaDAO.doUpdateNumeroAlloggi((struttura.getNumAlloggi()-1), fkStruttura);


        return alloggioDAO.doDelete(numeroAlloggio, fkStruttura);
    }

    public Alloggio returnAlloggio(int numeroAlloggio, int fkStruttura) {
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        return alloggioDAO.doRetrieveById(numeroAlloggio, fkStruttura);
    }

}
