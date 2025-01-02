package Application.GestioneAlloggi;

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

    public Alloggio returnAlloggio(int numeroAlloggio, int fkStruttura) {
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        return alloggioDAO.doRetrieveById(numeroAlloggio, fkStruttura);
    }

    public int aggiungiAlloggio(Alloggio alloggio) {
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        List<Integer> verifica = alloggioDAO.doSave(alloggio);
        if(verifica.get(1) == alloggio.getNumeroAlloggio()){
            if(verifica.get(2) == alloggio.getFkStruttura())
                return 1;
        }
        return 0;
    }

    public int modificaAlloggio(Alloggio alloggio, int numeroAlloggio, int fkStruttura) {
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        alloggioDAO.doDelete(numeroAlloggio, fkStruttura);
        return aggiungiAlloggio(alloggio);
    }

    public List<Integer> eliminaAlloggio(int numeroAlloggio, int fkStruttura) {
        AlloggioDAO alloggioDAO = new AlloggioDAO();
        return alloggioDAO.doDelete(numeroAlloggio, fkStruttura);
    }

}
