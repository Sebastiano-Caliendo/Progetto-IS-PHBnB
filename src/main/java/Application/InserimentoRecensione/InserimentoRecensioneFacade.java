package Application.InserimentoRecensione;

import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Recensione.Recensione;
import Storage.Recensione.RecensioneDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

public class InserimentoRecensioneFacade {
    private InserimentoRecensioneProxy proxy;

    public InserimentoRecensioneFacade() {
        this.proxy = new InserimentoRecensioneProxy();
    }

    public void inserisciRecensione(HttpSession session, String email, String descrizione, int votoRecensione, LocalDate dataRecensione, int codicePrenotazione, int numeroAlloggio, int idStruttura){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Recensione recensione = new Recensione();

            recensione.setUtente((Utente) session.getAttribute("utente"));
            recensione.setDescrizione(descrizione);
            recensione.setVotoRecensione(votoRecensione);
            recensione.setDataRecensione(dataRecensione);
            recensione.setAlloggio(alloggioDAO.doRetrieveById(numeroAlloggio, idStruttura));
            recensione.setPrenotazione(prenotazioneDAO.doRetrieveById(codicePrenotazione));
            recensioneDAO.doSave(recensione);
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

    public void eliminaRecensione(HttpSession session, int idRecensione){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(idRecensione);
        }
    }

    // email = fk_utente in recensione
    public List<Recensione> visualizzaRecensioniPubblicate(HttpSession session, String email){
        boolean successo = false;
        List<Recensione> recensioniPubblicate;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniPubblicate = recensioneDAO.doRetrieveByEmail(email);
            return recensioniPubblicate;
        }
        return null;
    }

    // controlli commentati per testare il servizio (parte HOST e UTENTE ancora non fatta)
    public List<Recensione> visualizzaRecensioniRicevute(HttpSession session, int idStruttura){
        //boolean successo = false;
        List<Recensione> recensioniRicevute;

        /*if(proxy.isHost(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }*/

        //if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniRicevute = recensioneDAO.doRetrieveByStruttura(idStruttura);
            return recensioniRicevute;
        //}
        //return null;
    }
}
