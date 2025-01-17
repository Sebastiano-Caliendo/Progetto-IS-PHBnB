package Storage.Occupa;

import Storage.Alloggio.Alloggio;
import Storage.Prenotazione.Prenotazione;

public class Occupa {

    private Prenotazione prenotazione;
    private Alloggio alloggio;
    private double costoPrenotazione;

    public Occupa() {
    }

    public Occupa(Prenotazione prenotazione, Alloggio alloggio, double costoPrenotazione) {
        this.prenotazione = prenotazione;
        this.alloggio = alloggio;
        this.costoPrenotazione = costoPrenotazione;
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

    public double getCostoPrenotazione() {
        return costoPrenotazione;
    }

    public void setCostoPrenotazione(double costoPrenotazione) {
        this.costoPrenotazione = costoPrenotazione;
    }
}
