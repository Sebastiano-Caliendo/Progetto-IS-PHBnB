package GU;

import Application.Autenticazione.AutenticazioneFacade;
import Storage.Utente.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RegistrazioneServiceTests {
    @Mock
    private HttpServletRequest request;

    @Mock
    private AutenticazioneFacade autenticazioneFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        autenticazioneFacade = new AutenticazioneFacade(request.getSession());
    }

    @Test
    public void TC_1_1_1(){
        assertFalse(autenticazioneFacade.registrazioneUtente("giacomoleopardigmail.com", "Giacomo", "Leopardi", "leopardi1798", "Recanati", "15",
                "Via Porto Recanati", "1990-06-29", "0769141258"));
    }

    @Test
    public void TC_1_1_2(){
        assertFalse(autenticazioneFacade.registrazioneUtente("totodinatale@gmail.com", "Antonio", "Di Natale", "toto", "Napoli", "10",
                "Via Toledo", "1984-02-23", "0814567893"));
    }

    @Test
    public void TC_1_1_3(){
        assertFalse(autenticazioneFacade.registrazioneUtente("ciroimmobile@gmail.com", "C1r0", "Immobile", "forzalazio17", "Napoli", "9",
                "Via San Massimo", "1993-08-21", "0157893641"));
    }

    @Test
    public void TC_1_1_4(){
        assertFalse(autenticazioneFacade.registrazioneUtente("vitomasi@gmail.com", "Vito", "M", "ciccianocapitale", "Cicciano", "10",
                "Via Rossi", "1971-12-14", "0817463248"));
    }

    @Test
    public void TC_1_1_5(){
        assertFalse(autenticazioneFacade.registrazioneUtente("mariorossi@outlook.com", "Mario", "Rossi", "mario1968", "Mil@n0", "1",
                "Via Libertà", "1980-01-01", "0606889910"));
    }

    @Test
    public void TC_1_1_6(){
        assertFalse(autenticazioneFacade.registrazioneUtente("giuseppeverdi@gmail.com", "Giuseppe", "Verdi", "ilNabucco", "Milano", "80BisBis",
                "Via Primavera", "1960-12-25", "0688445526"));
    }

    @Test
    public void TC_1_1_7(){
        assertFalse(autenticazioneFacade.registrazioneUtente("emmamarrone@gmail.com", "Emma", "Marrone", "lamorenonmibasta", "Lecce", "35",
                "V8", "1999-10-01", "0102034578"));
    }

    @Test
    public void TC_1_1_8(){
        assertFalse(autenticazioneFacade.registrazioneUtente("giorgiameloni@presidenza.gov.it", "Giorgia", "Meloni", "fratelliditalia1", "Roma", "15",
                "Via della Liberazione", "1978-05-05", "0102aab3"));
    }

    @Test
    public void TC_1_1_9(){
        assertFalse(autenticazioneFacade.registrazioneUtente("mariamontessori@libero.it", "Maria", "Montessori", "montessori01", "Firenze", "90",
                "Via dei Martiri", "198-05-0a", "0424560147"));
    }

    @Test
    public void TC_1_1_10(){
        assertTrue(autenticazioneFacade.registrazioneUtente("luciaairone@gmail.com", "Lucia", "Airone", "lucia1980", "Palermo", "25",
                "Via Rinascimento", "1989-03-21", "0314789620"));
    }
}