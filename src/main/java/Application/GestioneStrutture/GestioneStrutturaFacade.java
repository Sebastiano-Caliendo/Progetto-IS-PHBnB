package Application.GestioneStrutture;

import Storage.Alloggio.Alloggio;
import Storage.Host.Host;
import Storage.Occupa.Occupa;
import Storage.Occupa.OccupaDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Utility.Validator;

import java.util.HashMap;
import java.util.List;


/**
* Classe che contiene tutti i metodi per la gestione di una struttura
 */
public class GestioneStrutturaFacade {

    private Validator validator;

    public GestioneStrutturaFacade() {
        this.validator = new Validator();
    }

    /**
    * Gestisce la logica di visualizzazione delle strutture relative ad uno specifico host
    *
    * @param host oggetto Host che rappresenta l'host a cui appartengono le strutture
    *
    * @return restituisce una lista di strutture
     */
    public List<Struttura> visualizzaStrutture(Host host) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        return strutturaDAO.doRetrieveByCriteria("fk_host", host.getEmail());
    }


    /**
     * Gestisce la logica di aggiunta di una struttura per uno specifico host
     *
     * @param host oggetto Host che rappresenta l'host a cui appartiene la struttura
     * @param nomeStruttura stringa che rappresenta il nome della struttura
     * @param via stringa che rappresenta la via dove si trova la struttura
     * @param citta stringa che rappresenta la città dove si trova la struttura
     * @param numCivico stringa che rappresenta il numero civico della struttura
     * @param descrizione stringa che rappresenta la descrizione della struttura
     * @param urlImmagine stringa che rappresenta l'url dell'immagine della struttura
     *
     * @return restituisce 1 se l'aggiunta è andata a buon fine, 0 altrimenti
     **/
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
            strutturaDAO.doSave(struttura);

            return 1;
        } catch (RuntimeException e) {
            return 0;
        }
    }

    /**
     * Gestisce la logica di modifica di una struttura per uno specifico host
     *
     * @param host oggetto Host che rappresenta l'host a cui appartiene la struttura
     * @param nomeStruttura stringa che rappresenta il nome della struttura
     * @param via stringa che rappresenta la via dove si trova la struttura
     * @param citta stringa che rappresenta la città dove si trova la struttura
     * @param numCivico stringa che rappresenta il numero civico della struttura
     * @param descrizione stringa che rappresenta la descrizione della struttura
     * @param urlImmagine stringa che rappresenta l'url dell'immagine della struttura
     * @param idStruttura stringa che rappresenta l'id della struttura da modificare
     *
     * @return restituisce 1 se la modifica è andata a buon fine, 0 altrimenti
     **/
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

    /**
     * Gestisce la logica di eliminazione di una struttura
     *
     * @param idStruttura stringa che rappresenta l'id della struttura da eliminare
     *
     * @return restituisce l'id della struttura se l'eliminazione è andata a buon fine, 0 altrimenti
     **/
    public int eliminaStruttura(String idStruttura) {
        try {
            StrutturaDAO strutturaDAO =  new StrutturaDAO();
            return strutturaDAO.doDelete(validator.validateInt(idStruttura));
        } catch (RuntimeException e) {
            return 0;
        }
    }

    /**
     * Gestisce la logica di visualizzazione delle prenotazione di una specifica struttura
     *
     * @param struttura oggetto Struttura che rappresenta la struttura a cui fanno riferimento le prenotazioni da visualizzare
     *
     * @return restituisce un'hashmap contenente tutte le prenotazioni
     **/
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

    /**
     * Gestisce la logica di visualizzazione di una specifica struttura
     *
     * @param idStruttura stringa che rappresenta l'id della struttura da visualizzare
     *
     * @return restituisce la struttura avente l'id specificato, null altrimenti
     **/
    public Struttura returnStruttura(String idStruttura) {
        try {
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            return strutturaDAO.doRetrieveById(validator.validateInt(idStruttura));
        } catch (RuntimeException e) {
            return null;
        }
    }

}
