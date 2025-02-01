package GPA;

import Application.Autenticazione.AutenticazioneFacade;
import Application.PrenotazioneAlloggio.PrenotazioneAlloggioFacade;
import Storage.Alloggio.Alloggio;
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

public class PrenotazioneAlloggioServiceTests {
    @Mock
    private Utente utente;

    @Mock
    private HttpServletRequest request;

    @Mock
    private PrenotazioneAlloggioFacade prenotazioneAlloggioFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(utente);

        prenotazioneAlloggioFacade = Mockito.mock(PrenotazioneAlloggioFacade.class);
    }

    @Test
    public void TC_3_1_1(){
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mar1o", "Rossi", "2024-03-10", "2024-03-12",
                "1", "101", "2", "1111222233334444", "2026-01-31", "111"));
    }

    @Test
    public void TC_3_1_2() {
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Ross1", "2024-03-10", "2024-03-15",
                "1", "101", "2", "1111222233334444", "2026-01-31", "111"));
    }

    @Test
    public void TC_3_1_3() {
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Rossi", "2024-10-03", "2024-03-15",
                "1", "101", "2", "1111222233334444", "2026-01-31", "111"));
    }

    @Test
    public void TC_3_1_4() {
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Rossi", "2024-03-10", "2024-03-09",
                "1", "101", "2", "1111222233334444", "2026-01-31", "111"));
    }

    @Test
    public void TC_3_1_5() {
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Rossi", "2024-03-10", "2024-03-15",
                "1", "101", "2", "1111222233334444", "2026-01-31", "111"));
    }

    @Test
    public void TC_3_1_6() {
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Rossi", "2024-03-10", "2024-03-15",
                "1", "101", "2", "1111222233334444", "2024-09-31", "111"));
    }

    @Test
    public void TC_3_1_7() {
        assertFalse(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Rossi", "2024-03-10", "2024-03-15",
                "1", "101", "2", "1111222233334444", "2026-01-31", "11"));
    }

    @Test
    public void TC_3_1_8() {
        assertTrue(prenotazioneAlloggioFacade.finalizzaPrenotazione(utente, "Mario", "Rossi", "2024-03-10", "2024-03-15",
                "1", "101", "2", "1111222233334444", "2026-01-31", "111"));
    }
}
