package Application.GestioneAmministratore;

import Application.GestioneStrutture.gestioneStrutturaFacade;
import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Host.Host;
import Storage.Host.HostDAO;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Recensione.Recensione;
import Storage.Recensione.RecensioneDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import Utility.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestioneAmministratoreFacade {

    private Validator validator;

    public GestioneAmministratoreFacade(){
        this.validator = new Validator();
    }

    public List<Alloggio> visualizzaDatiSistemaAlloggio(){

        AlloggioDAO alloggioDAO = new AlloggioDAO();
        List<Alloggio> alloggi;
        alloggi = alloggioDAO.doRetrieveAll();
        return alloggi;
    }

    public List<Host> visualizzaDatiSistemaHost(){

        HostDAO hostDAO = new HostDAO();
        List<Host> hosts;
        hosts = hostDAO.doRetrieveAll();
        return hosts;
    }

    public List<Prenotazione> visualizzaDatiSistemaPrenotazione(){

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
        List<Prenotazione> prenotazioni;
        prenotazioni = prenotazioneDAO.doRetrieveAll();
        return prenotazioni;
    }


    public List<Recensione> visualizzaDatiSistemaRecensione(){

        RecensioneDAO recensioneDAO = new RecensioneDAO();
        List<Recensione> recensioni;
        recensioni = recensioneDAO.doRetrieveAll();
        return recensioni;
    }

    public List<Struttura> visualizzaDatiSistemaStruttura(){

        StrutturaDAO strutturaDAO = new StrutturaDAO();
        List<Struttura> strutture;
        strutture = strutturaDAO.doRetrieveAll();
        return strutture;
    }

    public List<Utente> visualizzaDatiSistemaUtente(){

        UtenteDAO utenteDAO = new UtenteDAO();
        List<Utente> utenti;
        utenti = utenteDAO.doRetrieveAll();
        return utenti;
    }

    public boolean modificaDatiSistemaAlloggio(String numeroAlloggio, String prezzoNotte, String postiLetto, String tipoAlloggio , String descrizione, String oldNumAlloggio, String fkStruttura){

        try {
            gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
            Alloggio alloggio = new Alloggio();

            alloggio.setNumeroAlloggio(validator.validateInt(numeroAlloggio));
            alloggio.setStruttura(strutturaFacade.returnStruttura(fkStruttura));
            alloggio.setTipoAlloggio(tipoAlloggio);
            alloggio.setDescrizione(descrizione);
            alloggio.setPostiletto(validator.validateInt(postiLetto));
            alloggio.setPrezzoNotte(validator.validateDouble(prezzoNotte));

            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doUpdate(alloggio, validator.validateInt(oldNumAlloggio), validator.validateInt(fkStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modificaDatiSistemaHost(String oldEmailHost, String email, String nome, String cognome, String password, String recapitoTelefonico){

        try {
            HostDAO hostDAO = new HostDAO();
            Host host = hostDAO.doRetrieveById(validator.validateEmail(oldEmailHost));
            hostDAO.doUpdate(host,
                            validator.validateEmail(email),
                            validator.validateNomeCognome(nome),
                            validator.validateNomeCognome(cognome),
                            validator.validatePassword(password),
                            validator.validateRecapitoTelefonico(recapitoTelefonico));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modificaDatiSistemaPrenotazione(String codPrenotazione, String checkIn, String checkOut, String numPersone){

        try {
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            Prenotazione p = prenotazioneDAO.doRetrieveById(validator.validateInt(codPrenotazione));

            prenotazioneDAO.doUpdate(p,
                                    validator.validateData(checkIn),
                                    validator.validateData(checkOut),
                                    validator.validateInt(numPersone));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

   /* public void modificaDatiSistemaRecensione(int idRecensione, int codicePrenotazione, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo) {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            Recensione recensione = recensioneDAO.doRetrieveByCodicePrenotazione(codicePrenotazione);
            recensioneDAO.doUpdate(recensione, idRecensione);
        }
    }*/

    public boolean modificaDatiSistemaStruttura(String fkHost, String nomeStruttura, String via, String citta, String numAlloggi, String numCivico, String descrizione, String urlImmagine, String idStruttura){

        try {
            HostDAO hostDAO = new HostDAO();
            Host host = hostDAO.doRetrieveById(validator.validateEmail(fkHost));

            Struttura struttura = new Struttura();
            struttura.setNomeStruttura(validator.validateNomeStruttura(nomeStruttura));
            struttura.setDescrizione(validator.validateDescrizione(descrizione));
            struttura.setIdStruttura(validator.validateInt(idStruttura));
            struttura.setCitta(validator.validateCittaVia(citta));
            struttura.setVia(validator.validateCittaVia(via));
            struttura.setHost(host);
            struttura.setNumCivico(validator.validateNumeroCivico(numCivico));
            struttura.setNumAlloggi(validator.validateInt(numAlloggi));



            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdate(struttura, validator.validateInt(idStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modificaDatiSistemaUtente(String oldEmailUtente, String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, String recapitoTelefonico) {

        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            Utente utente = utenteDAO.doRetrieveById(oldEmailUtente);

            utenteDAO.doUpdate(utente,
                            validator.validateEmail(email),
                            validator.validateNomeCognome(nome),
                            validator.validateNomeCognome(cognome),
                            validator.validatePassword(password),
                            validator.validateCittaVia(citta),
                            validator.validateNumeroCivico(numeroCivico),
                            validator.validateCittaVia(via),
                            validator.validateRecapitoTelefonico(recapitoTelefonico));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }



    public boolean cancellazioneDatiSitemaAlloggio(String numAlloggio, String fkStruttura){

        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doDelete(validator.validateInt(numAlloggio), validator.validateInt(fkStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean cancellazioneDatiSitemaRecensione(String idRecensione){

        try {
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(validator.validateInt(idRecensione));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    public boolean cancellazioneDatiSistemaStruttura(String idStruttura){

        try {
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            List<Alloggio> alloggi;
            alloggi = alloggioDAO.doRetrieveAll();
            for(Alloggio a: alloggi){
                if(a.getStruttura().getIdStruttura() == validator.validateInt(idStruttura)){
                    alloggioDAO.doDelete(a.getNumeroAlloggio(), a.getStruttura().getIdStruttura());
                }
            }
            strutturaDAO.doDelete(validator.validateInt(idStruttura));

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}