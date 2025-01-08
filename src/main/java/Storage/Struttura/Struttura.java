package Storage.Struttura;

public class Struttura {
    private int idStruttura;
    private String fkHost;
    private String nomeStruttura;
    private String via;
    private int numCivico;
    private String citta;
    private int numAlloggi;
    private String descrizione;

    private String urlImmagine;

    public Struttura() {
    }

    public Struttura(int idStruttura, String fkHost, String nomeStruttura, String via, int numCivico, String citta, String descrizione, String urlImmagine) {
        this.idStruttura = idStruttura;
        this.fkHost = fkHost;
        this.nomeStruttura = nomeStruttura;
        this.via = via;
        this.numCivico = numCivico;
        this.citta = citta;
        this.numAlloggi = 0;
        this.descrizione = descrizione;
        this.urlImmagine = urlImmagine;
    }

    public int getIdStruttura() {
        return idStruttura;
    }

    public void setIdStruttura(int idStruttura) {
        this.idStruttura = idStruttura;
    }

    public String getFkHost() {
        return fkHost;
    }

    public void setFkHost(String fkHost) {
        this.fkHost = fkHost;
    }

    public String getNomeStruttura() {
        return nomeStruttura;
    }

    public void setNomeStruttura(String nomeStruttura) {
        this.nomeStruttura = nomeStruttura;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumCivico() {
        return numCivico;
    }

    public void setNumCivico(int numCivico) {
        this.numCivico = numCivico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getNumAlloggi() {
        return numAlloggi;
    }

    public void setNumAlloggi(int numAlloggi) {
        this.numAlloggi = numAlloggi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
