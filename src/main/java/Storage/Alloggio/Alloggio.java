package Storage.Alloggio;

public class Alloggio {
    private int numeroAlloggio;
    private int fkStruttura;
    private double prezzoNotte;
    private int Postiletto;
    private String tipoAlloggio;
    private String descrizione;

    public Alloggio() {
    }

    public int getNumeroAlloggio() {
        return numeroAlloggio;
    }

    public void setNumeroAlloggio(int numeroAlloggio) {
        this.numeroAlloggio = numeroAlloggio;
    }

    public int getFkStruttura() {
        return fkStruttura;
    }

    public void setFkStruttura(int fkStruttura) {
        this.fkStruttura = fkStruttura;
    }

    public double getPrezzoNotte() {
        return prezzoNotte;
    }

    public void setPrezzoNotte(double prezzoNotte) {
        this.prezzoNotte = prezzoNotte;
    }

    public int getPostiletto() {
        return Postiletto;
    }

    public void setPostiletto(int postiletto) {
        Postiletto = postiletto;
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
}
