package Application.Autenticazione;

import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import Utility.Validator;
import jakarta.servlet.http.HttpSession;

public class AutenticazioneFacade {

    private HttpSession session;
    private Validator validator;

    public AutenticazioneFacade(HttpSession session) {
        this.session = session;
        this.validator = new Validator();
    }

    public boolean registrazioneUtente(String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, String dataNascita, String recapitoTelefonico) {

        UtenteDAO utenteDAO = new UtenteDAO();

        if(utenteDAO.doRetrieveById(email) != null) {
            return false;
        }

        try {
            Utente u = new Utente(
                    validator.validateEmail(email),
                    validator.validateNomeCognome(nome),
                    validator.validateNomeCognome(cognome),
                    validator.validatePassword(password),
                    validator.validateCittaVia(citta),
                    validator.validateNumeroCivico(numeroCivico),
                    validator.validateCittaVia(via),
                    validator.validateData(dataNascita),
                    validator.validateRecapitoTelefonico(recapitoTelefonico),
                    false);

            utenteDAO.doSave(u);
            session.setAttribute("utente", u);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean registrazioneHost(String email, String nome, String cognome, String password, String dataNascita, String recapitoTelefonico) {

        HostDAO hostDAO = new HostDAO();

        if(hostDAO.doRetrieveById(email) != null) {
            return false;
        }

        try {
            Host h = new Host(
                    validator.validateEmail(email),
                    validator.validateNomeCognome(nome),
                    validator.validateNomeCognome(cognome),
                    validator.validatePassword(password),
                    validator.validateData(dataNascita),
                    validator.validateRecapitoTelefonico(recapitoTelefonico));

            hostDAO.doSave(h);
            session.setAttribute("host", h);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
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
        } else if(tipo.equals("admin")){
            Utente u;

            UtenteDAO utenteDAO = new UtenteDAO();
            u = utenteDAO.doRetrieveById(email);

            if(u != null)
                session.setAttribute("admin", u);
            else
                return false;
        }

        return true;
    }

    public void logout() {
        session.invalidate();
    }

    public boolean modificaDatiPersonaliUtente(Utente u, String email, String nome, String cognome, String newPwd, String citta, String nCivico, String via, String recTel) {

        UtenteDAO utenteDAO = new UtenteDAO();

        if(u == null) return false;

        try {
            utenteDAO.doUpdate(u,
                            validator.validateEmail(email),
                            validator.validateNomeCognome(nome),
                            validator.validateNomeCognome(cognome),
                            validator.validatePassword(newPwd),
                            validator.validateCittaVia(citta),
                            validator.validateNumeroCivico(nCivico),
                            validator.validateCittaVia(via),
                            validator.validateRecapitoTelefonico(recTel));

            Utente utenteMod = utenteDAO.doRetrieveByEmailAndPassword(email, newPwd);
            session.setAttribute("utente", utenteMod);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modificaDatiPersonaliHost(Host h, String email, String nome, String cognome, String pwd, String recTel) {

        HostDAO hostDAO = new HostDAO();

        if(h == null) return false;

        try {
            hostDAO.doUpdate(h,
                            validator.validateEmail(email),
                            validator.validateNomeCognome(nome),
                            validator.validateNomeCognome(cognome),
                            validator.validatePassword(pwd),
                            validator.validateRecapitoTelefonico(recTel));

            Host hostMod = hostDAO.doRetrieveByEmailAndPassword(email, pwd);
            session.setAttribute("host", hostMod);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
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
