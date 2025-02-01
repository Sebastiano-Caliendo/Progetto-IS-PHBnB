package GU;

import Application.Autenticazione.AutenticazioneFacade;
import Storage.Utente.Utente;
import Storage.Utente.UtenteDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginServiceTests {

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
    public void TC_1_2_1() {
        UtenteDAO utenteDAO = Mockito.mock(UtenteDAO.class);
        when(utenteDAO.doRetrieveByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        assertFalse(autenticazioneFacade.login("Emailinesistente", "chiavediaccesso", "user"));
    }

    @Test
    public void TC_1_2_2() {
        UtenteDAO utenteDAO = Mockito.mock(UtenteDAO.class);
        when(utenteDAO.doRetrieveByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        assertFalse(autenticazioneFacade.login("pierorusso@gmail.com", "marioverdi10", "user"));
    }

    @Test
    public void TC_1_2_3() {
        UtenteDAO utenteDAO = Mockito.mock(UtenteDAO.class);
        when(utenteDAO.doRetrieveByEmailAndPassword(anyString(), anyString())).thenReturn(new Utente());

        assertTrue(autenticazioneFacade.login("annamagnani@libero.it", "magnaniattrice1", "user"));
    }
}
