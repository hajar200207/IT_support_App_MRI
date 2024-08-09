
import com.itsolutions.equipment_management.controllers.PanneController;
import com.itsolutions.equipment_management.models.EtatPanne;
import com.itsolutions.equipment_management.models.Panne;
import com.itsolutions.equipment_management.services.PanneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PanneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PanneService panneService;

    @InjectMocks
    private PanneController panneController;

    private Panne panne;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(panneController).build();

        panne = new Panne();
        panne.setId(1L);
        panne.setDescription("Network issue");
        panne.setEtatPanne(EtatPanne.SIGNALE);
    }

    @Test
    public void testReportPanne() {
        when(panneService.reportPanne(any(Panne.class))).thenReturn(panne);

        ResponseEntity<Panne> response = panneController.reportPanne(panne);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Network issue", response.getBody().getDescription());
        verify(panneService, times(1)).reportPanne(panne);
    }

    @Test
    public void testGetPannesByEquipmentId() {
        List<Panne> pannes = Arrays.asList(panne);
        when(panneService.getPannesByEquipmentId(1L)).thenReturn(pannes);

        ResponseEntity<List<Panne>> response = panneController.getPannesByEquipmentId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(panneService, times(1)).getPannesByEquipmentId(1L);
    }

    @Test
    public void testGetAllPannes() {
        List<Panne> pannes = Arrays.asList(panne);
        when(panneService.getAllPannes()).thenReturn(pannes);

        ResponseEntity<List<Panne>> response = panneController.getAllPannes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(panneService, times(1)).getAllPannes();
    }

    @Test
    public void testUpdatePanne() {
        when(panneService.updatePanne(eq(1L), any(Panne.class))).thenReturn(panne);

        ResponseEntity<Panne> response = panneController.updatePanne(1L, panne);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Network issue", response.getBody().getDescription());
        verify(panneService, times(1)).updatePanne(1L, panne);
    }

    @Test
    public void testDeletePanne() {
        ResponseEntity<String> response = panneController.deletePanne(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Panne deleted successfully", response.getBody());
        verify(panneService, times(1)).deletePanne(1L);
    }

    @Test
    public void testSearchPannesByDescription() {
        List<Panne> pannes = Arrays.asList(panne);
        when(panneService.searchPannesByDescription("Network")).thenReturn(pannes);

        ResponseEntity<List<Panne>> response = panneController.searchPannesByDescription("Network");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(panneService, times(1)).searchPannesByDescription("Network");
    }

    @Test
    public void testSearchPannesByEtat() {
        List<Panne> pannes = Arrays.asList(panne);
        when(panneService.searchPannesByEtat(EtatPanne.SIGNALE)).thenReturn(pannes);

        ResponseEntity<List<Panne>> response = panneController.searchPannesByEtat(EtatPanne.SIGNALE);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(panneService, times(1)).searchPannesByEtat(EtatPanne.SIGNALE);
    }
}
