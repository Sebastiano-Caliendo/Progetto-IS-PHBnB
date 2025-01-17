package Application.PrenotazioneAlloggio;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Occupa.Occupa;
import Storage.Occupa.OccupaDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;

import java.time.LocalDate;
import java.util.List;


public class PrenotazioneAlloggioFacade {

    public PrenotazioneAlloggioFacade() {
    }

    public List<Alloggio> visualizzaListaAlloggi(LocalDate checkIn, LocalDate checkOut, String destinazione, int numPostiLetto) {

        AlloggioDAO alloggioDAO = new AlloggioDAO();

        List<Alloggio> alloggi = alloggioDAO.doRetrieveAlloggiDisponibili(checkIn, checkOut, destinazione, numPostiLetto);

        return alloggi;
    }

    public Alloggio visualizzaDettagliAlloggio(int numAlloggio, int fkStruttura) {

        AlloggioDAO alloggioDAO = new AlloggioDAO();

        Alloggio alloggio = alloggioDAO.doRetrieveById(numAlloggio, fkStruttura);

        return alloggio;
    }

    public int finalizzaPrenotazione(LocalDate checkIn, LocalDate checkOut, int numPostiLetto, String fkUtente, int numAlloggio, int codStruttura, double costoPrenotazione) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione p = new Prenotazione(checkIn, checkOut, fkUtente, numPostiLetto);
        int fkPrenotazione = prenotazioneDAO.doSave(p);

        OccupaDAO occupaDAO = new OccupaDAO();
        Occupa o = new Occupa(fkPrenotazione, numAlloggio, codStruttura, costoPrenotazione);
        return occupaDAO.doSave(o);
    }

    public void modificaPrenotazione(LocalDate checkIn, LocalDate checkOut, int numPostiLetto, int codPrenotazione) {
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione p = new Prenotazione();
        p.setCodicePrenotazione(codPrenotazione);
        prenotazioneDAO.doUpdate(p, checkIn, checkOut, numPostiLetto);
    }

    public boolean eliminaPrenotazione(int codPrenotazione) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();

        Prenotazione p = prenotazioneDAO.doRetrieveById(codPrenotazione);
        LocalDate dataCheckIn = p.getCheckIn();

        LocalDate dataAttuale = LocalDate.now();

        if(dataAttuale.isBefore(dataCheckIn)) {
            prenotazioneDAO.doDelete(codPrenotazione);
            return true;
        } else {
            return false;
        }
    }

    public List<Prenotazione> visualizzaPrenotazioni(String codUtente) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();

        List<Prenotazione> prenotazioni = prenotazioneDAO.doRetrieveByUtente(codUtente);

        return prenotazioni;
    }
}
