package GH;

import Application.GestioneAlloggi.GestioneAlloggioFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AggiungiAlloggioServiceTests {
    @Mock
    private GestioneAlloggioFacade gestioneAlloggioFacade;

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        gestioneAlloggioFacade = new GestioneAlloggioFacade();
    }

    @Test
    public void TC_2_3_1(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("", "1", "90.00", "2", "Suite", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_2(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("-1", "1", "90.00", "2", "Suite", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_3(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "", "2", "Suite", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_4(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "-35.00", "2", "Suite", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_5(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "90.00", "", "Suite", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_6(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "90.00", "0", "Suite", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_7(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "90.00", "2", "", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_8(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "90.00", "2", "Suite Super lussuosa con pareti d'oro che luccicano", "ciao", "/Interface/img/alloggio.png"));
    }

    @Test
    public void TC_2_3_9(){
        assertEquals(0, gestioneAlloggioFacade.aggiungiAlloggio("101", "1", "90.00", "2", "Suite", "ciao", "/Interface/img/alloggioooooooooooooooooooooooooooooooooooooo.png"));
    }

    @Test
    public void TC_2_3_10(){
        assertEquals(1, gestioneAlloggioFacade.aggiungiAlloggio("118", "1", "90", "2", "Suite", "ciao", "Interface/img/strutture/struttura1.jpg"));
    }
}
