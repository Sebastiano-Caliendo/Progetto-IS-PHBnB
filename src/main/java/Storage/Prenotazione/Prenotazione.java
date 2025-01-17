package Storage.Prenotazione;

import Storage.Utente.Utente;

import java.sql.Date;
import java.time.LocalDate;


public class Prenotazione {

    private int codicePrenotazione;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Utente utente;
    private int numeroPersone;
    private String numeroCartaCredito;
    private LocalDate dataScadenzaCarta;
    private String cviCarta;

    public Prenotazione() {
    }

    public Prenotazione(LocalDate checkIn, LocalDate checkOut, Utente utente, int numeroPersone, String numeroCartaCredito, LocalDate dataScadenzaCarta, String cviCarta) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.utente = utente;
        this.numeroPersone = numeroPersone;
        this.numeroCartaCredito = numeroCartaCredito;
        this.dataScadenzaCarta = dataScadenzaCarta;
        this.cviCarta = cviCarta;
    }

    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public void setCodicePrenotazione(int codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public String getNumeroCartaCredito() {
        return numeroCartaCredito;
    }

    public void setNumeroCartaCredito(String numeroCartaCredito) {
        this.numeroCartaCredito = numeroCartaCredito;
    }

    public LocalDate getDataScadenzaCarta() {
        return dataScadenzaCarta;
    }

    public void setDataScadenzaCarta(LocalDate dataScadenzaCarta) {
        this.dataScadenzaCarta = dataScadenzaCarta;
    }

    public String getCviCarta() {
        return cviCarta;
    }

    public void setCviCarta(String cviCarta) {
        this.cviCarta = cviCarta;
    }
}
