package Application.Autenticazione;

import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Occupa.Occupa;
import Storage.Occupa.OccupaDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import Utility.Validator;
import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 * classe che contiene tutti i metodi necessari per effettuare autenticazione
 */
public class AutenticazioneFacade {

    private HttpSession session;
    private Validator validator;

    public AutenticazioneFacade(HttpSession session) {
        this.session = session;
        this.validator = new Validator();
    }

    /**
     * gestisce la logica relativa alla registrazione di un utente
     * @param email campo email di utente
     * @param nome campo nome di utente
     * @param cognome campo cognome di utente
     * @param password campo password di utente
     * @param citta campo citta di utente
     * @param numeroCivico campo numeroCivico di utente
     * @param via campo via di utente
     * @param dataNascita campo dataNascita di utente
     * @param recapitoTelefonico campo recapitoTelefonico di utente
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la registrazione
     */
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

    /**
     * gestisce la logica relativa alla registrazione di un host
     * @param email campo email di host
     * @param nome campo nome di host
     * @param cognome campo cognome di host
     * @param password campo password di host
     * @param dataNascita campo dataNascita di host
     * @param recapitoTelefonico campo recapitoTelefonico
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la registrazione
     */

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

    /**
     * gestisce la logica relativa al login di un utente
     * @param email campo email preso da form di login
     * @param password campo password preso da form di login
     * @param tipo campo tipo preso da form di login (user, host, admin)
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stato effettuato o meno il login
     */
    public boolean login(String email, String password, String tipo) {

        if(tipo.equals("user")) {
            Utente u;

            OccupaDAO occupaDAO = new OccupaDAO();
            UtenteDAO utenteDAO = new UtenteDAO();
            u = utenteDAO.doRetrieveByEmailAndPassword(email, password);

            if(u != null) {
                List<Occupa> prenotazioni = occupaDAO.doRetrieveByUtente(email);
                session.setAttribute("prenotazioni", prenotazioni);
                session.setAttribute("utente", u);
            }
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

    /**
     * gestisce la logica relativa al logout di un utente
     */
    public void logout() {
        session.invalidate();
    }

    /**
     * gestisce la logica relativa alla modifica dei dati personali di un utente
     * @param u oggetto Utente dove verranno modificati i dati
     * @param email campo Email di utente
     * @param nome campo Nome di utente
     * @param cognome campo Cognome di utente
     * @param newPwd campo Nuova Password aggiornata di utente
     * @param citta campo Città di utente
     * @param nCivico campo Numero Civico di utente
     * @param via campo Via di utente
     * @param recTel campo Recapito Telefonico di utente
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica dei dati dell'utente
     */
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

    /**
     * gestisce la logica relativa alla modifica dei dati personali di un host
     * @param h oggetto Utente dove verranno modificati i dati
     * @param email campo Email di host
     * @param nome campo Nome di host
     * @param cognome campo Cognome di host
     * @param pwd campo Nuova Password aggiornata di host
     * @param recTel campo Recapito Telefonico di host
     * @throws RuntimeException eccezione generica di java.lang
     * @return il metodo restituisce un valore booleano (true o false) rispetto a se è stata effettuata o meno la modifica dei dati dell'host
     */
    public boolean modificaDatiPersonaliHost(Host h, String email, String nome, String cognome, String pwd, String recTel) {

        System.out.println("sono in modifica dati host");

        HostDAO hostDAO = new HostDAO();

        if(h == null) {
            System.out.println("host = null");
            return false;
        }

        try {

            System.out.println("sto per chiamare update");

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

    /**
     * gestisce la logica relativa all'eliminazione di un account
     */
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
