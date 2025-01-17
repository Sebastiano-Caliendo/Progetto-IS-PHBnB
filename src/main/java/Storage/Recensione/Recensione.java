package Storage.Recensione;

import Storage.Alloggio.Alloggio;
import Storage.Prenotazione.Prenotazione;
import Storage.Utente.Utente;

import java.time.LocalDate;

public class Recensione {

    private int idRecensione;
    private Utente utente;
    private String descrizione;
    private int votoRecensione;
    private LocalDate dataRecensione;
    private Prenotazione prenotazione;
    private Alloggio alloggio;

    public Recensione() {
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getVotoRecensione() {
        return votoRecensione;
    }

    public void setVotoRecensione(int votoRecensione) {
        this.votoRecensione = votoRecensione;
    }

    public LocalDate getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(LocalDate dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    public Alloggio getAlloggio() {
        return alloggio;
    }

    public void setAlloggio(Alloggio alloggio) {
        this.alloggio = alloggio;
    }
}
