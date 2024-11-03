package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StatisticsControllerTest {

    @InjectMocks
    private StatisticsController statisticsController;

    @Mock
    private StatisticsService statisticsService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }

    @Test
    public void testGetTotals() throws Exception {
        // Préparer les données de test
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalCommands", 100L);
        stats.put("totalClients", 50L);
        stats.put("totalLivreurs", 20L);

        // Configurer les comportements du service
        when(statisticsService.getTotalCommands()).thenReturn(100L);
        when(statisticsService.getTotalClients()).thenReturn(50L);
        when(statisticsService.getTotalLivreurs()).thenReturn(20L);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(get("/api/statistics/totals")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCommands").value(100))
                .andExpect(jsonPath("$.totalClients").value(50))
                .andExpect(jsonPath("$.totalLivreurs").value(20));
    }
}
