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
    private Utente utente;

    @Mock
    private HttpServletRequest request;

    @Mock
    private AutenticazioneFacade autenticazioneFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        autenticazioneFacade = new AutenticazioneFacade(session);
    }

    @Test
    public void TC_1_1_1(){
        assertTrue(autenticazioneFacade.registrazioneUtente("giacomoleopardigmail.com", "Giacomo", "Leopardi", "leopardi1798", "Recanati", "15",
                "Porto Recanati", "1990-06-29", "0769141258"));
    }
}
