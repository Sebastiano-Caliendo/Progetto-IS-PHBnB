package Application.PrenotazioneAlloggio;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Occupa.Occupa;
import Storage.Occupa.OccupaDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Utente.Utente;
import Utility.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Classe che contiene tutti i metodi per effettuare la prenotazione di un alloggio
 **/
public class PrenotazioneAlloggioFacade {

    private Validator validator;

    public PrenotazioneAlloggioFacade() {
        this.validator = new Validator();
    }

    /**
     * Gestisce la logica di visualizzazione di alloggi secondo i criteri specificati
     *
     * @param checkIn stringa che rappresenta la data di check-in
     * @param checkOut stringa che rappresenta la data di check-out
     * @param destinazione stringa che rappresenta la destinazione
     * @param numPostiLetto stringa che rappresenta il numero di posti letto
     *
     * @return restituisce una lista di alloggi
     **/
    public List<Alloggio> visualizzaListaAlloggi(String checkIn, String checkOut, String destinazione, String numPostiLetto) {

        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            List<Alloggio> alloggi = alloggioDAO.doRetrieveAlloggiDisponibili(validator.validateData(checkIn), validator.validateData(checkOut), validator.validateDescrizione(destinazione), validator.validateInt(numPostiLetto));

            return alloggi;
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * Gestisce la logica di visualizzazione dei dettagli di uno specifico alloggio
     *
     * @param numAlloggio stringa che rappresenta il numero dell'alloggio
     * @param fkStruttura stringa che rappresenta l'id della struttura a cui appartiene l'alloggio
     *
     * @return restituisce un alloggio
     **/
    public Alloggio visualizzaDettagliAlloggio(String numAlloggio, String fkStruttura) {

        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();

            Alloggio alloggio = alloggioDAO.doRetrieveById(validator.validateInt(numAlloggio), validator.validateInt(fkStruttura));

            return alloggio;
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * Gestisce la logica di finalizzazione di una prenotazione per uno specifico utente
     *
     * @param utente oggetto Utente che rappresenta l'utente che sta effettuando la prenotazione
     * @param nome stringa che rappresenta il nome dell'intestatario della prenotazione
     * @param cognome stringa che rappresenta il cognome dell'intestatario della prenotazione
     * @param checkIn stringa che rappresenta la data di check-in
     * @param checkOut stringa che rappresenta la data di check-out
     * @param numPostiLetto stringa che rappresenta il numero di posti letto
     * @param numAlloggio stringa che rappresenta il numero dell'alloggio
     * @param codStruttura stringa che rappresenta l'id della struttura a cui appartiene l'alloggio
     * @param numeroCarta stringa che rappresenta il numero della carta di credito
     * @param dataScadenza stringa che rappresenta la data di scadenza della carta di credito
     * @param cvvCarta stringa che rappresenta il CVV della carta di credito
     *
     * @return restituisce true se la prenotazione è andata a buon fine, false altrimenti
     **/
    public boolean finalizzaPrenotazione(Utente utente,String nome, String cognome, String checkIn, String checkOut, String numPostiLetto, String numAlloggio, String codStruttura, String numeroCarta, String dataScadenza, String cvvCarta) {

        try {
            LocalDate checkInDate = validator.validateData(checkIn);
            LocalDate checkOutDate = validator.validateData(checkOut);

            if(checkInDate.isAfter(checkOutDate)) return false;

            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Prenotazione p = new Prenotazione(validator.validateNomeCognome(nome),
                                            validator.validateNomeCognome(cognome),
                                            checkInDate,
                                            checkOutDate,
                                            utente,
                                            validator.validateInt(numPostiLetto),
                                            validator.validateNumeroCarta(numeroCarta),
                                            validator.validateData(dataScadenza),
                                            validator.validateCVVCarta(cvvCarta));

            int codicePrenotazione = prenotazioneDAO.doSave(p);
            p.setCodicePrenotazione(codicePrenotazione);

            double costoPrenotazione = 0.0;

            AlloggioDAO alloggioDAO = new AlloggioDAO();
            Alloggio alloggio = alloggioDAO.doRetrieveById(validator.validateInt(numAlloggio), validator.validateInt(codStruttura));

            int diffGiorni = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            costoPrenotazione = diffGiorni * alloggio.getPrezzoNotte();

            OccupaDAO occupaDAO = new OccupaDAO();
            Occupa o = new Occupa(p, alloggio , costoPrenotazione);
            occupaDAO.doSave(o);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Gestisce la logica di modifica di una specifica prenotazione
     *
     * @param checkIn stringa che rappresenta la data di check-in
     * @param checkOut stringa che rappresenta la data di check-out
     * @param numPostiLetto stringa che rappresenta il numero di posti letto
     * @param codPrenotazione stringa che rappresenta il codice della prenotazione
     *
     * @return restituisce true se la modifica della prenotazione è andata a buon fine, false altrimenti
     **/
    public boolean modificaPrenotazione(String checkIn, String checkOut, String numPostiLetto, String codPrenotazione) {

        try {
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Prenotazione p = prenotazioneDAO.doRetrieveById(validator.validateInt(codPrenotazione));

            LocalDate dataAttuale = LocalDate.now();

            LocalDate dataCheckIn = validator.validateData(checkIn);
            LocalDate dataCheckOut = validator.validateData(checkOut);
            int postiLetto = validator.validateInt(numPostiLetto);

            if(dataAttuale.isBefore(p.getCheckIn().minusDays(7)) && dataCheckIn.isBefore(dataCheckOut)) {
                prenotazioneDAO.doUpdate(p, dataCheckIn, dataCheckOut, postiLetto);
                return true;
            }

            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Gestisce la logica di eliminazione di una specifica prenotazione
     *
     * @param codPrenotazione stringa che rappresenta il codice della prenotazione
     *
     * @return restituisce true se l'eliminazione della prenotazione è andata a buon fine, false altrimenti
     **/
    public boolean eliminaPrenotazione(String codPrenotazione) {

        try {
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();

            Prenotazione p = prenotazioneDAO.doRetrieveById(validator.validateInt(codPrenotazione));
            LocalDate dataCheckIn = p.getCheckIn();

            LocalDate dataAttuale = LocalDate.now();

            if(dataAttuale.plusDays(7).isBefore(dataCheckIn)) {
                prenotazioneDAO.doDelete(validator.validateInt(codPrenotazione));
                return true;
            }

            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Gestisce la logica di visualizzazione delle prenotazione di uno specifico utente
     *
     * @param emailUtente stringa che rappresenta l'id dell'utente
     *
     * @return restituisce una lista di prenotazioni
     **/
    public List<Occupa> visualizzaPrenotazioni(String emailUtente) {

        try {
            OccupaDAO occupaDAO = new OccupaDAO();

            List<Occupa> prenotazioni = occupaDAO.doRetrieveByUtente(validator.validateEmail(emailUtente));

            return prenotazioni;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
