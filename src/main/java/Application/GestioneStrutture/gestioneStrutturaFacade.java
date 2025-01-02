package Application.GestioneStrutture;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Host.Host;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;

import java.util.ArrayList;
import java.util.List;

public class gestioneStrutturaFacade {

    public List<Struttura> visualizzaStrutture(Host host) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        return strutturaDAO.doRetrieveByCriteria("fk_host", host.getEmail());
    }

    public int aggiungiStruttura(Struttura struttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        int idStruttura = strutturaDAO.doSave(struttura);
        if(idStruttura == struttura.getIdStruttura())
            return 1;
        return 0;
    }

    public int modificaStruttura(Struttura struttura, int idStruttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        strutturaDAO.doDelete(idStruttura);
        return aggiungiStruttura(struttura);
    }

    public int eliminaStruttura(int idStruttura) {
        StrutturaDAO strutturaDAO =  new StrutturaDAO();
        return strutturaDAO.doDelete(idStruttura);
    }

    public List<Prenotazione> visualizzaPrenotazioni(Struttura struttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        List<Alloggio> alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);
        List<Prenotazione> prenotazioniStruttura = new ArrayList<>();

        for (int i = 0; i < alloggi.size(); i++) {
            Alloggio alloggio = alloggi.get(i);
            List<Prenotazione> prenotazioniAlloggio = prenotazioneDAO.doRetrievePrenotazioniByAlloggio(alloggio);
            prenotazioniStruttura.addAll(prenotazioniAlloggio);
        }

        return prenotazioniStruttura;
    }

    public Struttura returnStruttura(int idStruttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        return strutturaDAO.doRetrieveById(idStruttura);
    }
}
