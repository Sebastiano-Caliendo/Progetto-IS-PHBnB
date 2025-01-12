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
    private int fk_codiceStruttura;

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public void setFk_codiceStruttura(int fk_codiceStruttura) {
        this.fk_codiceStruttura = fk_codiceStruttura;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public int getFk_codiceStruttura() {
        return fk_codiceStruttura;
    }

    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public void setCodicePrenotazione(int codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }

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
