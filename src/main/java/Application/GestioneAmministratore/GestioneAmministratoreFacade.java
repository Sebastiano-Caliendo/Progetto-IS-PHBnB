package Application.GestioneAmministratore;

import Application.GestioneStrutture.GestioneStrutturaFacade;
import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Recensione.Recensione;
import Storage.Recensione.RecensioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import Utility.Validator;

import java.util.List;

/**
 * classe che contiene tutti i metodi necessari per gestione amministratore
 */
public class GestioneAmministratoreFacade {

    private Validator validator;

    public GestioneAmministratoreFacade(){
        this.validator = new Validator();
    }

    /**
     * gestisce la logica relativa alla visualizzazione di tutti gli alloggi
     * @return il metodo restituisce una lista di tutti gli alloggi del sistema
     */
    public List<Alloggio> visualizzaDatiSistemaAlloggio(){

        AlloggioDAO alloggioDAO = new AlloggioDAO();
        List<Alloggio> alloggi;
        alloggi = alloggioDAO.doRetrieveAll();
        return alloggi;
    }

    /**
     * gestisce la logica relativa alla visualizzazione di tutti gli host
     * @return il metodo restituisce una lista di tutti gli host del sistema
     */
    public List<Host> visualizzaDatiSistemaHost(){

        HostDAO hostDAO = new HostDAO();
        List<Host> hosts;
        hosts = hostDAO.doRetrieveAll();
        return hosts;
    }

    /**
     * gestisce la logica relativa alla visualizzazione di tutte le prenotazioni
     * @return il metodo restituisce una lista di tutte le prenotazioni del sistema
     */
    public List<Prenotazione> visualizzaDatiSistemaPrenotazione(){

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        List<Prenotazione> prenotazioni;
        prenotazioni = prenotazioneDAO.doRetrieveAll();
        return prenotazioni;
    }


    /**
     * gestisce la logica relativa alla visualizzazione di tutte le recensioni
     * @return il metodo restituisce una lista di tutte le recensioni del sistema
     */
    public List<Recensione> visualizzaDatiSistemaRecensione(){

        RecensioneDAO recensioneDAO = new RecensioneDAO();
        List<Recensione> recensioni;
        recensioni = recensioneDAO.doRetrieveAll();
        return recensioni;
    }

    /**
     * gestisce la logica relativa alla visualizzazione di tutte le strutture
     * @return il metodo restituisce una lista di tutte le strutture del sistema
     */
    public List<Struttura> visualizzaDatiSistemaStruttura(){

        StrutturaDAO strutturaDAO = new StrutturaDAO();
        List<Struttura> strutture;
        strutture = strutturaDAO.doRetrieveAll();
        return strutture;
    }

    /**
     * gestisce la logica relativa alla visualizzazione di tutti gli utenti registrati
     * @return il metodo restituisce una lista di tutti gli utenti registrati del sistema
     */
    public List<Utente> visualizzaDatiSistemaUtente(){

        UtenteDAO utenteDAO = new UtenteDAO();
        List<Utente> utenti;
        utenti = utenteDAO.doRetrieveAll();
        return utenti;
    }

    /**
     * gestisce la logica relativa alla modifica di un alloggio
     * @param numeroAlloggio campo che contiene il nuovo numero dell'alloggio
     * @param prezzoNotte campo che contiene il nuovo prezzo per notte dell'alloggio
     * @param postiLetto campo che contiene il nuovo numero dei posti letto dell'alloggio
     * @param tipoAlloggio campo che contiene il nuovo tipo dell'alloggio
     * @param descrizione campo che contiene la nuova descrizione dell'alloggio
     * @param oldNumAlloggio campo che contiene il vecchio numero dell'alloggio da eliminare
     * @param fkStruttura campo che contiene l'id della struttura dove modificare l'alloggio
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica
     */
    public boolean modificaDatiSistemaAlloggio(String numeroAlloggio, String prezzoNotte, String postiLetto, String tipoAlloggio , String descrizione, String oldNumAlloggio, String fkStruttura){

        try {
            GestioneStrutturaFacade strutturaFacade = new GestioneStrutturaFacade();
            Alloggio alloggio = new Alloggio();

            alloggio.setNumeroAlloggio(validator.validateInt(numeroAlloggio));
            alloggio.setStruttura(strutturaFacade.returnStruttura(fkStruttura));
            alloggio.setTipoAlloggio(tipoAlloggio);
            alloggio.setDescrizione(descrizione);
            alloggio.setPostiletto(validator.validateInt(postiLetto));
            alloggio.setPrezzoNotte(validator.validateDouble(prezzoNotte));

            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doUpdate(alloggio, validator.validateInt(oldNumAlloggio), validator.validateInt(fkStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * gestisce la logica relativa alla modifica di un host
     * @param oldEmailHost campo che contiene la vecchia email dell'host
     * @param email campo che contiene la nuova email dell'host
     * @param nome campo che contiene il nuovo nome dell'host
     * @param cognome campo che contiene il nuovo cognome dell'host
     * @param password campo che contiene la nuova password dell'host
     * @param recapitoTelefonico campo che contiene il nuovo recapito telefonico dell'host
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica
     */
    public boolean modificaDatiSistemaHost(String oldEmailHost, String email, String nome, String cognome, String password, String recapitoTelefonico){

        try {
            HostDAO hostDAO = new HostDAO();
            Host host = hostDAO.doRetrieveById(validator.validateEmail(oldEmailHost));
            hostDAO.doUpdate(host,
                            validator.validateEmail(email),
                            validator.validateNomeCognome(nome),
                            validator.validateNomeCognome(cognome),
                            validator.validatePassword(password),
                            validator.validateRecapitoTelefonico(recapitoTelefonico));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * gestisce la logica relativa alla modifica di una prenotazione
     * @param codPrenotazione campo che contiene il codice della prenotazione
     * @param checkIn campo che contiene la nuova data di check-in
     * @param checkOut campo che contiene la nuova data di check-out
     * @param numPersone campo che contiene il nuovo numero di persone di una prenotazione
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica
     */
    public boolean modificaDatiSistemaPrenotazione(String codPrenotazione, String checkIn, String checkOut, String numPersone){

        try {
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Prenotazione p = prenotazioneDAO.doRetrieveById(validator.validateInt(codPrenotazione));

            prenotazioneDAO.doUpdate(p,
                                    validator.validateData(checkIn),
                                    validator.validateData(checkOut),
                                    validator.validateInt(numPersone));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

   /* public void modificaDatiSistemaRecensione(int idRecensione, int codicePrenotazione, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo) {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            Recensione recensione = recensioneDAO.doRetrieveByCodicePrenotazione(codicePrenotazione);
            recensioneDAO.doUpdate(recensione, idRecensione);
        }
    }*/

    /**
     * gestisce la logica relativa alla modifica di una struttura
     * @param fkHost campo che contiene l'host della struttura
     * @param nomeStruttura campo che contiene il nuovo nome della struttura
     * @param via campo che contiene la nuova via della struttura
     * @param citta campo che contiene la nuova città della struttura
     * @param numAlloggi campo che contiene il nuovo numero degli alloggi della struttura
     * @param numCivico campo che contiene il nuovo numero civico della struttura
     * @param descrizione campo che contiene la nuova descrizione della struttura
     * @param urlImmagine campo che contiene l'url della nuova immagine della struttura
     * @param idStruttura campo che contiene l'id della struttura
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica
     */
    public boolean modificaDatiSistemaStruttura(String fkHost, String nomeStruttura, String via, String citta, String numAlloggi, String numCivico, String descrizione, String urlImmagine, String idStruttura){

        try {
            HostDAO hostDAO = new HostDAO();
            Host host = hostDAO.doRetrieveById(validator.validateEmail(fkHost));

            Struttura struttura = new Struttura();
            struttura.setNomeStruttura(validator.validateNomeStruttura(nomeStruttura));
            struttura.setDescrizione(validator.validateDescrizione(descrizione));
            struttura.setIdStruttura(validator.validateInt(idStruttura));
            struttura.setCitta(validator.validateCittaVia(citta));
            struttura.setVia(validator.validateCittaVia(via));
            struttura.setHost(host);
            struttura.setNumCivico(validator.validateNumeroCivico(numCivico));
            struttura.setNumAlloggi(validator.validateInt(numAlloggi));



            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdate(struttura, validator.validateInt(idStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * gestisce la logica relativa alla modifica di un utente
     * @param oldEmailUtente campo che contiene la vecchia email dell'utente
     * @param email campo che contiene la nuova email dell'utente
     * @param nome campo che contiene il nuovo nome dell'utente
     * @param cognome campo che contiene il nuovo cognome dell'utente
     * @param password campo che contiene la nuova password dell'utente
     * @param citta campo che contiene la nuova città dell'utente
     * @param numeroCivico campo che contiene il nuovo numero civico dell'utente
     * @param via campo che contiene la nuova via dell'utente
     * @param recapitoTelefonico campo che contiene il nuovo recapito telefonico dell'utente
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica
     */
    public boolean modificaDatiSistemaUtente(String oldEmailUtente, String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, String recapitoTelefonico) {

        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            Utente utente = utenteDAO.doRetrieveById(oldEmailUtente);

            utenteDAO.doUpdate(utente,
                            validator.validateEmail(email),
                            validator.validateNomeCognome(nome),
                            validator.validateNomeCognome(cognome),
                            validator.validatePassword(password),
                            validator.validateCittaVia(citta),
                            validator.validateNumeroCivico(numeroCivico),
                            validator.validateCittaVia(via),
                            validator.validateRecapitoTelefonico(recapitoTelefonico));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * gestisce la logica relativa alla cancellazione di un alloggio
     * @param numAlloggio campo che contiene il numero dell'alloggio da eliminare
     * @param fkStruttura campo che contiene il numero della struttura che contiene l'alloggio
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la cancellazione
     */
    public boolean cancellazioneDatiSitemaAlloggio(String numAlloggio, String fkStruttura){

        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doDelete(validator.validateInt(numAlloggio), validator.validateInt(fkStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * gestisce la logica relativa alla cancellazione di una recensione
     * @param idRecensione campo che contiene l'id della recensione da eliminare
     * @throws RuntimeException eccezione genercia di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la cancellazione
     */
    public boolean cancellazioneDatiSitemaRecensione(String idRecensione){

        try {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(validator.validateInt(idRecensione));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * gestisce la logica relativa alla cancellazione di una struttura
     * @param idStruttura campo che contiene l'id della struttura da eliminare
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la cancellazione
     */
    public boolean cancellazioneDatiSistemaStruttura(String idStruttura){

        try {
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            List<Alloggio> alloggi;
            alloggi = alloggioDAO.doRetrieveAll();
            for(Alloggio a: alloggi){
                if(a.getStruttura().getIdStruttura() == validator.validateInt(idStruttura)){
                    alloggioDAO.doDelete(a.getNumeroAlloggio(), a.getStruttura().getIdStruttura());
                }
            }
            strutturaDAO.doDelete(validator.validateInt(idStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}