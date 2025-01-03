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

    public void registrazioneUtente(Utente u) {

        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.doSave(u);

        session.setAttribute("utente", u);
    }

    public void registrazioneHost(Host h) {

        HostDAO hostDAO = new HostDAO();
        hostDAO.doSave(h);

        session.setAttribute("host", h);
    }

    public void login(String email, String password, boolean isUser) {

        if(isUser) {
            Utente u;

            UtenteDAO utenteDAO = new UtenteDAO();
            u = utenteDAO.doRetrieveByEmailAndPassword(email, password);

            session.setAttribute("utente", u);
        } else {
            Host h;

            HostDAO hostDAO = new HostDAO();
            h = hostDAO.doRetrieveByEmailAndPassword(email, password);

            session.setAttribute("host", h);
        }
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
