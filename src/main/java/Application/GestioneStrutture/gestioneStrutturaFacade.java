package Application.GestioneStrutture;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Host.Host;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class gestioneStrutturaFacade {

    public List<Struttura> visualizzaStrutture(Host host) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        return strutturaDAO.doRetrieveByCriteria("fk_host", host.getEmail());
    }

    public int aggiungiStruttura(Host host, String nomeStruttura, String via, String citta, int numAlloggi, String descrizione, String urlImmagine) {
        // creiamo la struttura

        Struttura struttura = new Struttura();
        struttura.setHost(host);
        struttura.setDescrizione(descrizione);
        struttura.setNumAlloggi(numAlloggi);
        struttura.setNomeStruttura(nomeStruttura);
        struttura.setCitta(citta);
        struttura.setVia(via);
        struttura.setUrlImmagine(urlImmagine);

        StrutturaDAO strutturaDAO = new StrutturaDAO();

        int idStruttura = strutturaDAO.doSave(struttura);

        if(idStruttura == struttura.getIdStruttura())
            return 1;
        return 0;
    }

    public int modificaStruttura(Host host, String nomeStruttura, String via, String citta, int numAlloggi, String descrizione, String urlImmagine, int idStruttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        strutturaDAO.doDelete(idStruttura);
        return aggiungiStruttura(host, nomeStruttura, via, citta, numAlloggi, descrizione, urlImmagine);
    }

    public int eliminaStruttura(int idStruttura) {
        StrutturaDAO strutturaDAO =  new StrutturaDAO();
        return strutturaDAO.doDelete(idStruttura);
    }

    public List<Prenotazione> visualizzaPrenotazioni(Struttura struttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        List<Alloggio> alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);
        List<Prenotazione> prenotazioniStruttura = new ArrayList<>();

        for (int i = 0; i < alloggi.size(); i++) {
            Alloggio alloggio = alloggi.get(i);
            List<Prenotazione> prenotazioniAlloggio = prenotazioneDAO.doRetrievePrenotazioniByAlloggio(alloggio);
            prenotazioniStruttura.addAll(prenotazioniAlloggio);
        }

        return prenotazioniStruttura;
    }

    public Struttura returnStruttura(int idStruttura) {
        StrutturaDAO strutturaDAO = new StrutturaDAO();
        return strutturaDAO.doRetrieveById(idStruttura);
    }

}
