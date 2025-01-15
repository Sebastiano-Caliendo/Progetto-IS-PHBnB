package Storage.Prenotazione;

import java.sql.Date;
import java.time.LocalDate;


public class Prenotazione {

    private int codicePrenotazione;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String fkUtente;
    private int numeroPersone;

    public Prenotazione() {

    }

    public Prenotazione(LocalDate checkIn, LocalDate checkOut, String fkUtente, int numeroPersone) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.fkUtente = fkUtente;
        this.numeroPersone = numeroPersone;
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

    public String getFkUtente() {
        return fkUtente;
    }

    public void setFkUtente(String fkUtente) {
        this.fkUtente = fkUtente;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }
}
