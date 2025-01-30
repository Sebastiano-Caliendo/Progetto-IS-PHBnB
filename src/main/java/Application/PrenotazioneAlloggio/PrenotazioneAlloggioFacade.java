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
import java.util.List;


public class PrenotazioneAlloggioFacade {

    private Validator validator;

    public PrenotazioneAlloggioFacade() {
        this.validator = new Validator();
    }

    public List<Alloggio> visualizzaListaAlloggi(String checkIn, String checkOut, String destinazione, String numPostiLetto) {

        AlloggioDAO alloggioDAO = new AlloggioDAO();
        List<Alloggio> alloggi = alloggioDAO.doRetrieveAlloggiDisponibili(validator.validateData(checkIn), validator.validateData(checkOut), validator.validateDescrizione(destinazione), validator.validateInt(numPostiLetto));

        return alloggi;
    }

    public Alloggio visualizzaDettagliAlloggio(String numAlloggio, String fkStruttura) {

        AlloggioDAO alloggioDAO = new AlloggioDAO();

        Alloggio alloggio = alloggioDAO.doRetrieveById(validator.validateInt(numAlloggio), validator.validateInt(fkStruttura));

        return alloggio;
    }

    public int finalizzaPrenotazione(Utente utente, String checkIn, String checkOut, String numPostiLetto, String fkUtente, String numAlloggio, String codStruttura, String costoPrenotazione, String numeroCarta, String dataScadenza, String cviCarta) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione p = new Prenotazione(validator.validateData(checkIn), validator.validateData(checkOut), utente, validator.validateInt(numPostiLetto), validator.validateNumeroCarta(numeroCarta), validator.validateData(dataScadenza), validator.validateCVICarta(cviCarta));
        prenotazioneDAO.doSave(p);

        AlloggioDAO alloggioDAO = new AlloggioDAO();

        OccupaDAO occupaDAO = new OccupaDAO();
        Occupa o = new Occupa(p, alloggioDAO.doRetrieveById(validator.validateInt(numAlloggio), validator.validateInt(codStruttura)), validator.validateDouble(costoPrenotazione));
        return occupaDAO.doSave(o);
    }

    public void modificaPrenotazione(String checkIn, String checkOut, String numPostiLetto, String codPrenotazione) {
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione p = prenotazioneDAO.doRetrieveById(validator.validateInt(codPrenotazione));

        LocalDate dataAttuale = LocalDate.now();

        if(dataAttuale.isBefore(p.getCheckIn().minusDays(7))) {
            prenotazioneDAO.doUpdate(p, validator.validateData(checkIn), validator.validateData(checkOut), validator.validateInt(numPostiLetto));
        }
    }

    public void eliminaPrenotazione(String codPrenotazione) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();

        Prenotazione p = prenotazioneDAO.doRetrieveById(validator.validateInt(codPrenotazione));
        LocalDate dataCheckIn = p.getCheckIn();

        LocalDate dataAttuale = LocalDate.now();

        if(dataAttuale.plusDays(7).isBefore(dataCheckIn)) {
            prenotazioneDAO.doDelete(validator.validateInt(codPrenotazione));
        }
    }

    public List<Occupa> visualizzaPrenotazioni(String emailUtente) {

        OccupaDAO occupaDAO = new OccupaDAO();

        List<Occupa> prenotazioni = occupaDAO.doRetrieveByUtente(validator.validateEmail(emailUtente));

        return prenotazioni;
    }
}
