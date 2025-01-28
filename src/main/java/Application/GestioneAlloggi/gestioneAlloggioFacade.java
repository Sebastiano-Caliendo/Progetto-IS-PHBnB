package Application.GestioneAlloggi;

import Application.GestioneStrutture.gestioneStrutturaFacade;
import Storage.Alloggio.Alloggio;
import Storage.Alloggio.AlloggioDAO;
import Storage.Struttura.Struttura;
import Storage.Struttura.StrutturaDAO;
import Utility.Validator;

import java.util.ArrayList;
import java.util.List;

public class gestioneAlloggioFacade {

    private Validator validator;

    public gestioneAlloggioFacade() {
        this.validator = new Validator();
    }

    public List<Alloggio> visualizzaAlloggi(Struttura struttura) {
        // in strutturaDAO abbiamo un metodo che restituisce una lista di alloggi data una struttura

        List<Alloggio> alloggi;
        StrutturaDAO strutturaDAO = new StrutturaDAO();

        alloggi = strutturaDAO.doRetrieveAlloggiByStruttura(struttura);

        return alloggi;
    }

    public int aggiungiAlloggio(String numeroAlloggio, String idStruttura, String prezzoNotte, String numPostiLetto, String tipoAlloggio, String descrizione, String urlImmagine) {

        try {
            gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
            // creo l'alloggio che voglio inserire
            Alloggio alloggio = new Alloggio(validator.validateInt(numeroAlloggio),
                                            strutturaFacade.returnStruttura(idStruttura),
                                            validator.validateDouble(prezzoNotte),
                                            validator.validateInt(numPostiLetto),
                                            validator.validateTipoAlloggio(tipoAlloggio),
                                            validator.validateDescrizione(descrizione),
                                            urlImmagine);

            AlloggioDAO alloggioDAO = new AlloggioDAO();

            Struttura struttura = strutturaFacade.returnStruttura(idStruttura);
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdateNumeroAlloggi((struttura.getNumAlloggi()+1), validator.validateInt(idStruttura));


            List<Integer> verifica = alloggioDAO.doSave(alloggio);
            if(verifica.get(1) == alloggio.getNumeroAlloggio()){
                if(verifica.get(2) == alloggio.getStruttura().getIdStruttura())
                    return 1;
                else
                    return 0;
            } else {
                return 0;
            }
        } catch (RuntimeException e) {
            return 0;
        }
    }

    public int modificaAlloggio(String numeroAlloggio, String idStruttura, String prezzoNotte, String numPostiLetto, String tipoAlloggio, String descrizione, String urlImmagine, String oldNumeroAlloggio, String fkStruttura) {

        try {
            gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
            Alloggio alloggio = new Alloggio(validator.validateInt(numeroAlloggio),
                                            strutturaFacade.returnStruttura(idStruttura),
                                            validator.validateDouble(prezzoNotte),
                                            validator.validateInt(numPostiLetto),
                                            validator.validateTipoAlloggio(tipoAlloggio),
                                            validator.validateDescrizione(descrizione),
                                            urlImmagine);

            AlloggioDAO alloggioDAO = new AlloggioDAO();
            alloggioDAO.doDelete(validator.validateInt(oldNumeroAlloggio), validator.validateInt(fkStruttura));

            List<Integer> verifica = alloggioDAO.doSave(alloggio);
            if(verifica.get(1) == alloggio.getNumeroAlloggio()){
                if(verifica.get(2) == alloggio.getStruttura().getIdStruttura())
                    return 1;
                else
                    return 0;
            } else {
                return 0;
            }
        } catch (RuntimeException e) {
            return 0;
        }

        // incrementava il numero di alloggi
        //return aggiungiAlloggio(numeroAlloggio, idStruttura, prezzoNotte, numPostiLetto, tipoAlloggio, descrizione, urlImmagine);
    }

    public List<Integer> eliminaAlloggio(String numeroAlloggio, String fkStruttura) {

        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();

            gestioneStrutturaFacade strutturaFacade = new gestioneStrutturaFacade();
            Struttura struttura = strutturaFacade.returnStruttura(fkStruttura);
            StrutturaDAO strutturaDAO = new StrutturaDAO();
            strutturaDAO.doUpdateNumeroAlloggi((((struttura.getNumAlloggi())-1)), validator.validateInt(fkStruttura));


            return alloggioDAO.doDelete(validator.validateInt(numeroAlloggio), validator.validateInt(fkStruttura));
        } catch (RuntimeException e) {
            return null;
        }
    }

    public Alloggio returnAlloggio(String numeroAlloggio, String fkStruttura) {
        try {
            AlloggioDAO alloggioDAO = new AlloggioDAO();
            return alloggioDAO.doRetrieveById(validator.validateInt(numeroAlloggio), validator.validateInt(fkStruttura));
        } catch (RuntimeException e) {
            return null;
        }
    }

}
