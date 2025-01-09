package Storage.Recensione;

import java.util.Date;

public class Recensione {

    private int idRecensione;
    private String emailRecensore;
    private String descrizione;
    private int votoRecensione;
    private Date dataRecensione;
    private int codicePrenotazione;
    private int numeroAlloggio;

    private int idStrutturaAlloggio;

    public int getIdRecensione() { return idRecensione; }

    public void setIdRecensione(int idRecensione) { this.idRecensione = idRecensione; }

    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public void setCodicePrenotazione(int codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }

    public int getIdStrutturaAlloggio() { return idStrutturaAlloggio; }

    public void setIdStrutturaAlloggio(int idStrutturaAlloggio) { this.idStrutturaAlloggio = idStrutturaAlloggio; }

    public int getNumeroAlloggio() {
        return numeroAlloggio;
    }

    public void setNumeroAlloggio(int numeroAlloggio) {
        this.numeroAlloggio = numeroAlloggio;
    }

    public Recensione() {
    }


    public String getEmailRecensore() {
        return emailRecensore;
    }

    public void setEmailRecensore(String emailRecensore) {
        this.emailRecensore = emailRecensore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public java.sql.Date getDataRecensione() {
        return (java.sql.Date) dataRecensione;
    }

    public void setDataRecensione(Date dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public int getVotoRecensione() {
        return votoRecensione;
    }

    public void setVotoRecensione(int votoRecensione) {
        this.votoRecensione = votoRecensione;
    }
}
