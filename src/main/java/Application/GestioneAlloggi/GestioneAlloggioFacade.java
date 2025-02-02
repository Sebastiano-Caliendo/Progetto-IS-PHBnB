package Application.GestioneAlloggi;

import Application.GestioneStrutture.GestioneStrutturaFacade;
import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Utility.Validator;

import java.util.List;

/**
 * classe che contiene tutti i metodi necessari per la gestione alloggio
 */
public class GestioneAlloggioFacade {

    private Validator validator;

    public GestioneAlloggioFacade() {
        this.validator = new Validator();
    }

    /**
     * gestisce la logica relativa alla visualizzazione degli alloggi di una struttura
     * @param struttura oggetto struttura dove ricavare lista di alloggi
     * @return il metodo restituisce una lista di alloggi di una struttura
     */
    public List<Alloggio> visualizzaAlloggi(Struttura struttura) {
        // in strutturaDAO abbiamo un metodo che restituisce una lista di alloggi data una struttura

        List<Alloggio> alloggi;
        StrutturaDAO strutturaDAO = new StrutturaDAO();

        alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);

        return alloggi;
    }

    /**
     * gestisce la logica relativa all'aggiunta di un alloggio a una struttura
     * @param numeroAlloggio campo che contiene il nuovo numero di un alloggio
     * @param idStruttura campo che contiene l'id della struttura dove inserire alloggio
     * @param prezzoNotte campo che contiene il prezzo per notte dell'alloggio
     * @param numPostiLetto campo che contiene il numero dei posti letto dell'alloggio
     * @param tipoAlloggio campo che contiene il tipo dell'alloggio
     * @param descrizione campo che contiene la descrizione dell'alloggio
     * @param urlImmagine campo che contiene l'url dell'immagine dell'alloggio
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un intero (0 o 1) rispetto a se è stata effettuata o meno l'aggiunta
     */
    public int aggiungiAlloggio(String numeroAlloggio, String idStruttura, String prezzoNotte, String numPostiLetto, String tipoAlloggio, String descrizione, String urlImmagine) {

        try {
            GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
            // creo l'alloggio che voglio inserire
            Alloggio alloggio = new Alloggio(validator.validateInt(numeroAlloggio),
                                            strutturaFacade.returnStruttura(idStruttura),
                                            validator.validateDouble(prezzoNotte),
                                            validator.validateInt(numPostiLetto),
                                            validator.validateTipoAlloggio(tipoAlloggio),
                                            validator.validateDescrizione(descrizione),
                                            urlImmagine);

            AlloggioDAO alloggioDAO = new AlloggioDAO();

            Struttura struttura = strutturaFacade.returnStruttura(idStruttura);
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdateNumeroAlloggi((struttura.getNumAlloggi()+1), validator.validateInt(idStruttura));


            List<Integer> verifica = alloggioDAO.doSave(alloggio);
            if(verifica.get(0) == alloggio.getNumeroAlloggio()){
                if(verifica.get(1) == alloggio.getStruttura().getIdStruttura())
                    return 1;
                else
                    return 0;
            } else {
                return 0;
            }
        } catch (RuntimeException e) {
            return 0;
        }
    }

    /**
     * gestisce la logica relativa alla modifica di un alloggio esistente
     * @param numeroAlloggio campo che contiene il numero dell'alloggio esistente
     * @param idStruttura campo che contiene l'id della struttura associato al numero dell'alloggio
     * @param prezzoNotte campo che contiene il nuovo prezzo per notte dell'alloggio
     * @param numPostiLetto campo che contiene il nuovo numero dei posti letto dell'alloggio
     * @param tipoAlloggio campo che contiene il nuovo tipo dell'alloggio
     * @param descrizione campo che contiene la nuova descrizione dell'alloggio
     * @param urlImmagine campo che contiene il nuovo url dell'immagine
     * @param oldNumeroAlloggio campo che contiene il vecchio numero dell'alloggio da eliminare
     * @param fkStruttura campo che contiene l'id della struttura dove eliminare il vecchio alloggio
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un intero (0 o 1) rispetto a se è stata effettuata o meno la modifica dell'alloggio
     */
    public int modificaAlloggio(String numeroAlloggio, String idStruttura, String prezzoNotte, String numPostiLetto, String tipoAlloggio, String descrizione, String urlImmagine, String oldNumeroAlloggio, String fkStruttura) {

        try {
            GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
            Alloggio alloggio = new Alloggio(validator.validateInt(numeroAlloggio),
                                            strutturaFacade.returnStruttura(idStruttura),
                                            validator.validateDouble(prezzoNotte),
                                            validator.validateInt(numPostiLetto),
                                            validator.validateTipoAlloggio(tipoAlloggio),
                                            validator.validateDescrizione(descrizione),
                                            urlImmagine);

            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doDelete(validator.validateInt(oldNumeroAlloggio), validator.validateInt(fkStruttura));

            List<Integer> verifica = alloggioDAO.doSave(alloggio);
            if(verifica.get(1) == alloggio.getNumeroAlloggio()){
                if(verifica.get(2) == alloggio.getStruttura().getIdStruttura())
                    return 1;
                else
                    return 0;
            } else {
                return 0;
            }
        } catch (RuntimeException e) {
            return 0;
        }

        // incrementava il numero di alloggi
        //return aggiungiAlloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);
    }

    /**
     * gestisce la logica relativa all'eliminazione di un alloggio
     * @param numeroAlloggio campo che contiene il numero dell'alloggio da eliminare
     * @param fkStruttura campo che contiene l'id della struttura da dove eliminare l'alloggio
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce una lista di alloggi aggiornata senza l'alloggio eliminato
     */
    public List<Integer> eliminaAlloggio(String numeroAlloggio, String fkStruttura) {

        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();

            GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
            Struttura struttura = strutturaFacade.returnStruttura(fkStruttura);
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdateNumeroAlloggi((((struttura.getNumAlloggi())-1)), validator.validateInt(fkStruttura));


            return alloggioDAO.doDelete(validator.validateInt(numeroAlloggio), validator.validateInt(fkStruttura));
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * gestisce la logica relativa alla ricerca di un alloggio in una struttura
     * @param numeroAlloggio campo che contiene il numero dell'alloggio da ricercare
     * @param fkStruttura campo che contiene l'id della struttura dove ricerca l'alloggio
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce l'alloggio ricercato
     */
    public Alloggio returnAlloggio(String numeroAlloggio, String fkStruttura) {
        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            return alloggioDAO.doRetrieveById(validator.validateInt(numeroAlloggio), validator.validateInt(fkStruttura));
        } catch (RuntimeException e) {
            return null;
        }
    }

}
