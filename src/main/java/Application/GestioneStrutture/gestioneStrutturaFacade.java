package Application.GestioneStrutture;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Host.Host;
import Storage.Occupa.Occupa;
import Storage.Occupa.OccupaDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Utility.Validator;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class gestioneStrutturaFacade {

    private Validator validator;

    public gestioneStrutturaFacade() {
        this.validator = new Validator();
    }

    public List<Struttura> visualizzaStrutture(Host host) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        return strutturaDAO.doRetrieveByCriteria("fk_host", host.getEmail());
    }

    public int aggiungiStruttura(Host host, String nomeStruttura, String via, String citta, String numCivico, String descrizione, String urlImmagine) {

        if(host == null) return 0;
        // creiamo la struttura

        try {
            Struttura struttura = new Struttura();
            struttura.setHost(host);
            struttura.setDescrizione(validator.validateDescrizione(descrizione));
            struttura.setNumCivico(validator.validateNumeroCivico(numCivico));
            struttura.setNomeStruttura(validator.validateNomeStruttura(nomeStruttura));
            struttura.setCitta(validator.validateCittaVia(citta));
            struttura.setVia(validator.validateCittaVia(via));
            struttura.setUrlImmagine(urlImmagine);

            StrutturaDAO strutturaDAO = new StrutturaDAO();

            int idStruttura = strutturaDAO.doSave(struttura);

            if(idStruttura == struttura.getIdStruttura())
                return 1;
            else
                return 0;
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public int modificaStruttura(Host host, String nomeStruttura, String via, String citta, String numCivico, String descrizione, String urlImmagine, String idStruttura) {
        try {
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            Struttura struttura = new Struttura();
            struttura.setHost(host);
            struttura.setDescrizione(validator.validateDescrizione(descrizione));
            struttura.setNumCivico(validator.validateNumeroCivico(numCivico));
            struttura.setNomeStruttura(validator.validateNomeStruttura(nomeStruttura));
            struttura.setCitta(validator.validateCittaVia(citta));
            struttura.setVia(validator.validateCittaVia(via));
            struttura.setUrlImmagine(urlImmagine);
            strutturaDAO.doUpdate(struttura, validator.validateInt(idStruttura));
            /*strutturaDAO.doDelete(validator.validateInt(idStruttura));
            aggiungiStruttura(host, nomeStruttura, via, citta, numCivico, descrizione, urlImmagine);*/
            return 1;
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public int eliminaStruttura(String idStruttura) {
        try {
            StrutturaDAO strutturaDAO =  new StrutturaDAO();
            return strutturaDAO.doDelete(validator.validateInt(idStruttura));
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public HashMap<Prenotazione, Occupa> visualizzaPrenotazioni(Struttura struttura) {
        // per ogni prenotazione associo un' occupa che contiene altri dati che servono
        HashMap<Prenotazione, Occupa> prenotazioniOccupa = new HashMap<Prenotazione, Occupa>();

        StrutturaDAO strutturaDAO = new StrutturaDAO();
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        OccupaDAO occupaDAO = new OccupaDAO();

        // ricevo tutti gli alloggi di una struttura
        List<Alloggio> alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);
        //List<Prenotazione> prenotazioniStruttura = new ArrayList<>();

        for (int i = 0; i < alloggi.size(); i++) {
            Alloggio alloggio = alloggi.get(i);
            // ricevo tutte le prenotazioni di un alloggio
            List<Prenotazione> prenotazioniAlloggio = prenotazioneDAO.doRetrievePrenotazioniByAlloggio(alloggio);

            // scorro tutte le prenotazioni
            for(int j = 0; j < prenotazioniAlloggio.size(); j++) {
                // prendo la singola prenotazione
                Prenotazione prenotazione = prenotazioniAlloggio.get(j);
                // prendo l'occupa associato a quella prenotazione
                Occupa occupa = occupaDAO.doRetrieveOccupaByPrenotazione(prenotazione.getCodicePrenotazione());
                // chiave Prenotazione, valore Occupa
                prenotazioniOccupa.put(prenotazione, occupa);
            }
            //prenotazioniStruttura.addAll(prenotazioniAlloggio);
        }

        return prenotazioniOccupa;
    }

    public Struttura returnStruttura(String idStruttura) {
        try {
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            return strutturaDAO.doRetrieveById(validator.validateInt(idStruttura));
        } catch (RuntimeException e) {
            return null;
        }
    }

}
