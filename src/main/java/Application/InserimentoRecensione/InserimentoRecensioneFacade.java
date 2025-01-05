package Application.InserimentoRecensione;

import Storage.Recensione.Recensione;
import Storage.Recensione.RecensioneDAO;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class InserimentoRecensioneFacade {
    private InserimentoRecensioneProxy proxy;

    public InserimentoRecensioneFacade() {
        this.proxy = new InserimentoRecensioneProxy();
    }

    public void inserisciRecensione(HttpSession session, Recensione r){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doSave(r);
        }
    }

    public void modificaRecensione(HttpSession session, String email, String codicePrenotazione, int numeroAlloggio){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            Recensione r = recensioneDAO.doRetrieveByCodicePrenotazione(codicePrenotazione);
            recensioneDAO.doUpdate(r, email, codicePrenotazione, numeroAlloggio);
        }
    }

    public void eliminaRecensione(HttpSession session, String email, String codicePrenotazione, int numeroAlloggio){
        boolean successo = false;

        if(proxy.isUser(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(email, codicePrenotazione, numeroAlloggio);
        }
    }

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

    public List<Recensione> visualizzaRecensioniRicevute(HttpSession session, String idStruttura){
        boolean successo = false;
        List<Recensione> recensioniRicevute;

        if(proxy.isHost(session)){
            successo = true;
        }
        else{
            System.out.println("Operazione non ammessa");
        }

        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioniRicevute = recensioneDAO.doRetrieveByStruttura(idStruttura);
            return recensioniRicevute;
        }
        return null;
    }
}
