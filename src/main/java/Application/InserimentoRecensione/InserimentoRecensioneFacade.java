package Application.InserimentoRecensione;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Recensione.Recensione;
import Storage.Recensione.RecensioneDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import Utility.Validator;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

public class InserimentoRecensioneFacade {
    private Validator validator;

    public InserimentoRecensioneFacade() {
        this.validator = new Validator();
    }

    public boolean inserisciRecensione(HttpSession session, String descrizione, String votoRecensione, String codicePrenotazione, String numeroAlloggio, String idStruttura){

        try {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Recensione recensione = new Recensione();

            recensione.setUtente((Utente) session.getAttribute("utente"));
            recensione.setDescrizione(validator.validateDescrizione(descrizione));
            recensione.setVotoRecensione(validator.validateInt(votoRecensione));
            recensione.setDataRecensione(LocalDate.now());
            recensione.setAlloggio(alloggioDAO.doRetrieveById(validator.validateInt(numeroAlloggio), validator.validateInt(idStruttura)));
            recensione.setPrenotazione(prenotazioneDAO.doRetrieveById(validator.validateInt(codicePrenotazione)));
            recensioneDAO.doSave(recensione);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /*public void modificaRecensione(HttpSession session, int idRecensione, Recensione recensione){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            //Recensione r = recensioneDAO.doRetrieveById(idRecensione);
            recensioneDAO.doUpdate(recensione, idRecensione);
        }
    }*/

    public boolean eliminaRecensione(HttpSession session, String idRecensione){

        try {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(validator.validateInt(idRecensione));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    // email = fk_utente in recensione
    public List<Recensione> visualizzaRecensioniPubblicate(String email){

        try {
            List<Recensione> recensioniPubblicate;

            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniPubblicate = recensioneDAO.doRetrieveByEmail(validator.validateEmail(email));
            return recensioniPubblicate;
        } catch (RuntimeException e) {
            return null;
        }
    }

    // controlli commentati per testare il servizio (parte HOST e UTENTE ancora non fatta)
    public List<Recensione> visualizzaRecensioniRicevute(String idStruttura){

        try {
            List<Recensione> recensioniRicevute;

            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniRicevute = recensioneDAO.doRetrieveByStruttura(validator.validateInt(idStruttura));
            return recensioniRicevute;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
