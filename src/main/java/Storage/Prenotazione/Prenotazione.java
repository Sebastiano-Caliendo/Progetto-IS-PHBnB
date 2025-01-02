package Storage.Prenotazione;

import Storage.Utente.Utente;

import java.util.Date;

public class Prenotazione {

    private int codicePrenotazione;
    private Date checkIn;
    private Date checkOut;
    private String fkUtente;
    private int numeroPersone;

    public Prenotazione() {

    }

    public Prenotazione(int codicePrenotazione, Date checkIn, Date checkOut, String fkUtente, int numeroPersone) {
        this.codicePrenotazione = codicePrenotazione;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
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
