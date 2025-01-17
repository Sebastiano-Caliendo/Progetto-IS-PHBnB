package Storage.Alloggio;

import Storage.Struttura.Struttura;

public class Alloggio {
    private int numeroAlloggio;
    private Struttura struttura;
    private double prezzoNotte;
    private int postiletto;
    private String tipoAlloggio;
    private String descrizione;
    private String urlImmagine;

    public Alloggio() {
    }

    public Alloggio(int numeroAlloggio, Struttura struttura, double prezzoNotte, int postiletto, String tipoAlloggio, String descrizione, String urlImmagine) {
        this.numeroAlloggio = numeroAlloggio;
        this.struttura = struttura;
        this.prezzoNotte = prezzoNotte;
        this.postiletto = postiletto;
        this.tipoAlloggio = tipoAlloggio;
        this.descrizione = descrizione;
        this.urlImmagine = urlImmagine;
    }

    public int getNumeroAlloggio() {
        return numeroAlloggio;
    }

    public void setNumeroAlloggio(int numeroAlloggio) {
        this.numeroAlloggio = numeroAlloggio;
    }

    public Struttura getStruttura() {
        return struttura;
    }

    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }

    public double getPrezzoNotte() {
        return prezzoNotte;
    }

    public void setPrezzoNotte(double prezzoNotte) {
        this.prezzoNotte = prezzoNotte;
    }

    public int getPostiletto() {
        return postiletto;
    }

    public void setPostiletto(int postiletto) {
        this.postiletto = postiletto;
    }

    public String getTipoAlloggio() {
        return tipoAlloggio;
    }

    public void setTipoAlloggio(String tipoAlloggio) {
        this.tipoAlloggio = tipoAlloggio;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

}
