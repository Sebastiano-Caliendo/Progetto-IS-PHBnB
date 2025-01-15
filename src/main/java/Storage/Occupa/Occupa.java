package Storage.Occupa;

public class Occupa {

    private int fkPrenotazione;
    private int fkAlloggio;
    private int fkStrutturaAlloggio;
    private double costoPrenotazione;

    public Occupa(int fkPrenotazione, int fkAlloggio, int fkStrutturaAlloggio, double costoPrenotazione) {
        this.fkPrenotazione = fkPrenotazione;
        this.fkAlloggio = fkAlloggio;
        this.fkStrutturaAlloggio = fkStrutturaAlloggio;
        this.costoPrenotazione = costoPrenotazione;
    }

    public int getFkPrenotazione() {
        return fkPrenotazione;
    }

    public void setFkPrenotazione(int fkPrenotazione) {
        this.fkPrenotazione = fkPrenotazione;
    }

    public int getFkAlloggio() {
        return fkAlloggio;
    }

    public void setFkAlloggio(int fkAlloggio) {
        this.fkAlloggio = fkAlloggio;
    }

    public int getFkStrutturaAlloggio() {
        return fkStrutturaAlloggio;
    }

    public void setFkStrutturaAlloggio(int fkStrutturaAlloggio) {
        this.fkStrutturaAlloggio = fkStrutturaAlloggio;
    }

    public double getCostoPrenotazione() {
        return costoPrenotazione;
    }

    public void setCostoPrenotazione(double costoPrenotazione) {
        this.costoPrenotazione = costoPrenotazione;
    }
}
