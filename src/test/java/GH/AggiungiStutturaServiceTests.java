package GH;

import Application.GestioneStrutture.GestioneStrutturaFacade;

import Storage.Host.Host;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggiungiStutturaServiceTests {

    @Mock
    private Host host;

    @Mock
    private GestioneStrutturaFacade gestioneStrutturaFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        host = Mockito.mock(Host.class);
        gestioneStrutturaFacade = new GestioneStrutturaFacade();
    }

    @Test
    public void TC_2_2_1() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "", "Via Toledo", "Napoli", "25", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_2() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "Struttura delle meraviglie incantate napoletana", "Via Toledo", "Napoli", "25", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_3() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "", "Napoli", "25", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_4() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo Quartieri Spagnoli Incantati Delle Meraviglie", "Napoli", "25", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_5() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "", "25", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_6() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "Napoliiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii", "25", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_7() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "Napoli", "", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_8() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "Napoli", "25098765", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_9() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "Napoli", "-1", "struttura a pochi passi dal centro", "struttura.png"));
    }

    @Test
    public void TC_2_2_10() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "Napoli", "25", "", "struttura.png"));
    }

    @Test
    public void TC_2_2_11() {
        assertEquals(0, gestioneStrutturaFacade.aggiungiStruttura(host, "A’ magica", "Via Toledo", "Napoli", "25", "struttura a pochi passi dal centro", ""));
    }

    @Test
    public void TC_2_2_12() {
        Host h = new Host();
        h.setEmail("pintocarlo09@gmail.com");

        assertEquals(1, gestioneStrutturaFacade.aggiungiStruttura(h, "A’ magica", "Via Toledo", "Napoli", "25", "struttura a pochi passi dal centro", "Interface/img/strutture/struttura1.jpg"));
    }
}
