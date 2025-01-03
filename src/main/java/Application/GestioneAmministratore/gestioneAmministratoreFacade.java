package Application.GestioneAmministratore;

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
import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.util.List;

public class gestioneAmministratoreFacade {

    private gestioneAmministratoreProxy proxy;

    public gestioneAmministratoreFacade(){
        this.proxy = new gestioneAmministratoreProxy();
    }

    public List<Alloggio> visualizzaDatiSistemaAlloggio(HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            List<Alloggio> alloggi;
            alloggi = alloggioDAO.doRetrieveAll();
            return alloggi;
        }
        return null;
    }

    public List<Host> visualizzaDatiSistemaHost(HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            HostDAO hostDAO = new HostDAO();
            List<Host> hosts;
            hosts = hostDAO.doRetrieveAll();
            return hosts;
        }
        return null;
    }

    public List<Prenotazione> visualizzaDatiSistemaPrenotazione(HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            List<Prenotazione> prenotazioni;
            prenotazioni = prenotazioneDAO.doRetrieveAll();
            return prenotazioni;
        }
        return null;
    }


    public List<Recensione> visualizzaDatiSistemaRecensione(HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            List<Recensione> recensioni;
            recensioni = recensioneDAO.doRetrieveAll();
            return recensioni;
        }
        return null;
    }

    public List<Struttura> visualizzaDatiSistemaStruttura(HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            List<Struttura> strutture;
            strutture = strutturaDAO.doRetrieveAll();
            return strutture;
        }
        return null;
    }

    public List<Utente> visualizzaDatiSistemaUtente(HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            UtenteDAO utenteDAO = new UtenteDAO();
            List<Utente> utenti;
            utenti = utenteDAO.doRetrieveAll();
            return utenti;
        }
        return null;
    }

    public void modificaDatiSistemaAlloggio(Alloggio a, int numAlloggio, int fkStruttura, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doUpdate(a, numAlloggio, fkStruttura);
        }
    }

    public void modificaDatiSistemaHost(Host h, String email, String nome, String cognome, String password, String recapitoTelefonico, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            HostDAO hostDAO = new HostDAO();
            hostDAO.doUpdate(h, email, nome, cognome, password, recapitoTelefonico);
        }
    }

    public void modificaDatiSistemaPrenotazione(Prenotazione p, Date checkIn, Date checkOut, int numPersone, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
            prenotazioneDAO.doUpdate(p, checkIn, checkOut, numPersone);
        }
    }

    public void modificaDatiSistemaRecensione(String email, String codicePrenotazione, int numeroAlloggio, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            Recensione recensione = recensioneDAO.doRetrieveByCodicePrenotazione(codicePrenotazione);
            recensioneDAO.doUpdate(recensione, email, codicePrenotazione, numeroAlloggio);
        }
    }

    public void modificaDatiSistemaStruttura(int idStruttura, Struttura struttura, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdate(struttura, idStruttura);
        }
    }

    public void modificaDatiSistemaUtente(Utente utente, String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, String recapitoTelefonico, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            UtenteDAO utenteDAO = new UtenteDAO();
            utenteDAO.doUpdate(utente, email, nome, cognome, password, citta, numeroCivico, via, recapitoTelefonico);
        }
    }



    public void cancellazioneDatiSitemaAlloggio(int numAlloggio, int fkStruttura, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doDelete(numAlloggio, fkStruttura);
        }
    }

    public void cancellazioneDatiSitemaRecensione(String email, String codicePrenotazione, int numeroAlloggio, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(email, codicePrenotazione, numeroAlloggio);
        }
    }
    public void cancellazioneDatiSitemaStruttura(int idStruttura, int numAlloggio, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doDelete(idStruttura);
            alloggioDAO.doDelete(numAlloggio, idStruttura);
        }
    }
}
