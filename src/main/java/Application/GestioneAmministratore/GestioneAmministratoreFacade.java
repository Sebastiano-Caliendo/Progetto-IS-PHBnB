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

    private GestioneAmministratoreProxy proxy;

    public GestioneAmministratoreFacade(){
        this.proxy = new GestioneAmministratoreProxy();
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

    public void modificaDatiSistemaAlloggio(int numeroAlloggio, double prezzoNotte, int postiLetto, String tipoAlloggio , String descrizione, int oldNumAlloggio, int fkStruttura, HttpSession session){

        gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
        Alloggio alloggio = new Alloggio();

        alloggio.setNumeroAlloggio(numeroAlloggio);
        alloggio.setStruttura(strutturaFacade.returnStruttura(fkStruttura));
        alloggio.setTipoAlloggio(tipoAlloggio);
        alloggio.setDescrizione(descrizione);
        alloggio.setPostiletto(postiLetto);
        alloggio.setPrezzoNotte(prezzoNotte);

        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doUpdate(alloggio, oldNumAlloggio, fkStruttura);
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
            Host host = hostDAO.doRetrieveById(h.getEmail());
            hostDAO.doUpdate(host, email, nome, cognome, password, recapitoTelefonico);
        }
    }

    public void modificaDatiSistemaPrenotazione(Prenotazione p, LocalDate checkIn, LocalDate checkOut, int numPersone, HttpSession session){
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

    public void modificaDatiSistemaStruttura(Host host, String nomeStruttura, String via, String citta, int numAlloggi, String numCivico, String descrizione, String urlImmagine, int idStruttura, HttpSession session){
        boolean successo = false;

        Struttura struttura = new Struttura();
        struttura.setNomeStruttura(nomeStruttura);
        struttura.setDescrizione(descrizione);
        struttura.setIdStruttura(idStruttura);
        struttura.setCitta(citta);
        struttura.setVia(via);
        struttura.setHost(host);
        struttura.setNumCivico(numCivico);
        struttura.setNumAlloggi(numAlloggi);


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

    public void modificaDatiSistemaUtente(Utente utente, String email, String nome, String cognome, String password, String citta, String numeroCivico, String via, String recapitoTelefonico, HttpSession session) throws ServletException, IOException {
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

    public void cancellazioneDatiSitemaRecensione(int idRecensione, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            RecensioneDAO recensioneDAO = new RecensioneDAO();
            recensioneDAO.doDelete(idRecensione);
        }
    }
    public void cancellazioneDatiSistemaStruttura(int idStruttura, HttpSession session){
        boolean successo = false;

        if(proxy.isAutenticato(session)){
            successo = true;
        }else{
            System.out.println("Operazione non permessa.");
        }
        if(successo){
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            List<Alloggio> alloggi;
            alloggi = alloggioDAO.doRetrieveAll();
            for(Alloggio a: alloggi){
                if(a.getStruttura().getIdStruttura() == idStruttura){
                    alloggioDAO.doDelete(a.getNumeroAlloggio(), a.getStruttura().getIdStruttura());
                }
            }
            strutturaDAO.doDelete(idStruttura);
        }
    }
}
