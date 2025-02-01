package GPA;

import Application.PrenotazioneAlloggio.PrenotazioneAlloggioFacade;
import Storage.Prenotazione.Prenotazione;
import Storage.Prenotazione.PrenotazioneDAO;
import Storage.Utente.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ModificaPrenotazioneAlloggioServiceTests {
    @Mock
    private HttpServletRequest request;

    @Mock
    private PrenotazioneAlloggioFacade prenotazioneAlloggioFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        prenotazioneAlloggioFacade = Mockito.mock(PrenotazioneAlloggioFacade.class);
    }

    @Test
    public void TC_3_2_1(){
        assertFalse(prenotazioneAlloggioFacade.modificaPrenotazione("2024-03-10", "2024-03-09", "2", "3"));
    }

    @Test
    public void TC_3_2_2(){
        assertFalse(prenotazioneAlloggioFacade.modificaPrenotazione("2024-04-10", "2024-04-12", "3", "3"));
    }

    @Test
    public void TC_3_2_3(){
        assertFalse(prenotazioneAlloggioFacade.modificaPrenotazione("2024-10-19", "2024-10-21","4", "Ciao"));
    }

    @Test
    public void TC_3_2_4(){
        assertTrue(prenotazioneAlloggioFacade.modificaPrenotazione("2025-10-19", "2025-10-21", "4", "12"));
    }
}
