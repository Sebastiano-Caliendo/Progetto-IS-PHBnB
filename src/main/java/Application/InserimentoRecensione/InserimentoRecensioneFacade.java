package Application.InserimentoRecensione;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Recensione.Recensione;
import Storage.Recensione.RecensioneDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import Utility.Validator;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

/*
 * Classe che contiene tutti i metodi per l'inserimento di una recensione
 **/
public class InserimentoRecensioneFacade {
    private Validator validator;

    public InserimentoRecensioneFacade() {
        this.validator = new Validator();
    }


    /*
     * Gestisce la logica di inserimento di una recensione per uno specifico utente
     *
     * @param session oggetto HttpSession che rappresenta la sessione dell'utente corrente
     * @param descrizione stringa che rappresenta la descrizione della recensione
     * @param votoRecensione stringa che rappresenta la valutazione della recensione
     * @param codicePrenotazione stringa che rappresenta il codice della prenotazione da recensire
     * @param numeroAlloggio stringa che rappresenta il numero dell'alloggio da recensire
     * @param idStruttura stringa che rappresenta l'id della struttura da recensire
     *
     * @returns restituisce una lista di strutture
     **/
    public boolean inserisciRecensione(HttpSession session, String descrizione, String votoRecensione, String codicePrenotazione, String numeroAlloggio, String idStruttura){

        try {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Recensione recensione = new Recensione();


            recensione.setUtente((Utente) session.getAttribute("utente"));
            recensione.setDescrizione(validator.validateDescrizione(descrizione));
            recensione.setVotoRecensione(validator.validateInt(votoRecensione));
            recensione.setDataRecensione(LocalDate.now());
            recensione.setAlloggio(alloggioDAO.doRetrieveById(validator.validateInt(numeroAlloggio), validator.validateInt(idStruttura)));
            recensione.setPrenotazione(prenotazioneDAO.doRetrieveById(validator.validateInt(codicePrenotazione)));
            recensioneDAO.doSave(recensione);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /*public void modificaRecensione(HttpSession session, int idRecensione, Recensione recensione){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            //Recensione r = recensioneDAO.doRetrieveById(idRecensione);
            recensioneDAO.doUpdate(recensione, idRecensione);
        }
    }*/

    /*
     * Gestisce la logica di visualizzazione delle recensioni di uno specifico utente
     *
     * @param utente oggetto Utente che rappresenta l'utente di cui si vogliono visualizzare le recensioni
     *
     * @returns restituisce una lista di codici delle prenotazioni recensite
     **/
    public List<Integer> visualizzaRecensioniUtente(Utente utente){
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        return recensioneDAO.codiciPrenotazioniRecensite(utente);
    }

    /*
     * Gestisce la logica di eliminazione di una recensione
     *
     * @param idRecensione stringa che rappresenta l'id della recensione da eliminare
     *
     * @returns restituisce true se l'eliminazione è andata a buon fine, false altrimenti
     **/
    public boolean eliminaRecensione(String idRecensione){

        try {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(validator.validateInt(idRecensione));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /*
     * Gestisce la logica di visualizzazione delle recensioni pubblicate da uno specifico utente
     *
     * @param email stringa che rappresenta l'email dell'utente
     *
     * @returns restituisce una lista di recensioni
     **/
    public List<Recensione> visualizzaRecensioniPubblicate(String email){

        try {
            List<Recensione> recensioniPubblicate;

            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniPubblicate = recensioneDAO.doRetrieveByEmail(validator.validateEmail(email));
            return recensioniPubblicate;
        } catch (RuntimeException e) {
            return null;
        }
    }

    /*
     * Gestisce la logica di visualizzazione delle recensioni ricevute da una specifica struttura
     *
     * @param idStruttura stringa che rappresenta l'id della struttura
     *
     * @returns restituisce una lista di recensioni
     **/
    public List<Recensione> visualizzaRecensioniRicevute(String idStruttura){

        try {
            List<Recensione> recensioniRicevute;

            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniRicevute = recensioneDAO.doRetrieveByStruttura(validator.validateInt(idStruttura));
            return recensioniRicevute;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
