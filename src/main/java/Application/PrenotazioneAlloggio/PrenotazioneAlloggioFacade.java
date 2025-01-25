package Application.PrenotazioneAlloggio;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Occupa.Occupa;
import Storage.Occupa.OccupaDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Utente.Utente;

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

    public int finalizzaPrenotazione(Utente utente, LocalDate checkIn, LocalDate checkOut, int numPostiLetto, String fkUtente, int numAlloggio, int codStruttura, double costoPrenotazione, String numeroCarta, LocalDate dataScadenza, String cviCarta) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione p = new Prenotazione(checkIn, checkOut, utente, numPostiLetto, numeroCarta, dataScadenza, cviCarta);
        prenotazioneDAO.doSave(p);

        AlloggioDAO alloggioDAO = new AlloggioDAO();

        OccupaDAO occupaDAO = new OccupaDAO();
        Occupa o = new Occupa(p, alloggioDAO.doRetrieveById(numAlloggio, codStruttura), costoPrenotazione);
        return occupaDAO.doSave(o);
    }

    public void modificaPrenotazione(LocalDate checkIn, LocalDate checkOut, int numPostiLetto, int codPrenotazione) {
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        Prenotazione p = prenotazioneDAO.doRetrieveById(codPrenotazione);

        LocalDate dataAttuale = LocalDate.now();

        if(dataAttuale.isBefore(p.getCheckIn().minusDays(7))) {
            prenotazioneDAO.doUpdate(p, checkIn, checkOut, numPostiLetto);
        }
    }

    public void eliminaPrenotazione(int codPrenotazione) {

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();

        Prenotazione p = prenotazioneDAO.doRetrieveById(codPrenotazione);
        LocalDate dataCheckIn = p.getCheckIn();

        LocalDate dataAttuale = LocalDate.now();

        if(dataAttuale.plusDays(7).isBefore(dataCheckIn)) {
            prenotazioneDAO.doDelete(codPrenotazione);
        }
    }

    public List<Occupa> visualizzaPrenotazioni(String codUtente) {

        OccupaDAO occupaDAO = new OccupaDAO();

        List<Occupa> prenotazioni = occupaDAO.doRetrieveByUtente(codUtente);

        return prenotazioni;
    }
}
