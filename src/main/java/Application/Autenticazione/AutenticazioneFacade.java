package Application.Autenticazione;

import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import jakarta.servlet.http.HttpSession;

public class AutenticazioneFacade {

    private HttpSession session;

    public AutenticazioneFacade(HttpSession session) {
        this.session = session;
    }

    public boolean registrazioneUtente(Utente u) {

        UtenteDAO utenteDAO = new UtenteDAO();

        if(utenteDAO.doRetrieveById(u.getEmail()) != null) {
            return false;
        }

        utenteDAO.doSave(u);

        session.setAttribute("utente", u);

        return true;
    }

    public boolean registrazioneHost(Host h) {

        HostDAO hostDAO = new HostDAO();

        if(hostDAO.doRetrieveById(h.getEmail()) != null) {
            return false;
        }

        hostDAO.doSave(h);

        session.setAttribute("host", h);

        return true;
    }

    public boolean login(String email, String password, String tipo) {

        if(tipo.equals("user")) {
            Utente u;

            UtenteDAO utenteDAO = new UtenteDAO();
            u = utenteDAO.doRetrieveByEmailAndPassword(email, password);

            if(u != null)
                session.setAttribute("utente", u);
            else
                return false;
        } else if(tipo.equals("host")){
            Host h;

            HostDAO hostDAO = new HostDAO();
            h = hostDAO.doRetrieveByEmailAndPassword(email, password);

            if(h != null)
                session.setAttribute("host", h);
            else
                return false;
        }

        return true;
    }

    public void logout() {
        session.invalidate();
    }

    public void visualizzaAreaPersonale() {

    }

    public void modificaDatiPersonaliUtente(Utente u, String email, String nome, String cognome, String pwd, String citta, String nCivico, String via, String recTel) {

        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.doUpdate(u, email, nome, cognome, pwd, citta, nCivico, via, recTel);

        Utente utenteMod = utenteDAO.doRetrieveByEmailAndPassword(email, pwd);
        session.setAttribute("utente", utenteMod);
    }

    public void modificaDatiPersonaliHost(Host h, String email, String nome, String cognome, String pwd, String recTel) {

        HostDAO hostDAO = new HostDAO();
        hostDAO.doUpdate(h, email, nome, cognome, pwd, recTel);

        Host hostMod = hostDAO.doRetrieveByEmailAndPassword(email, pwd);
        session.setAttribute("host", hostMod);
    }
    public void eliminaAccount(){

        Utente u = (Utente) session.getAttribute("utente");
        Host h = (Host) session.getAttribute("host");

        if(u != null) {
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doDelete(u.getEmail());
        } else if(h != null) {
            HostDAO hostDAO = new HostDAO();
            hostDAO.doDelete(h.getEmail());
        }

        session.invalidate();
    }

}
