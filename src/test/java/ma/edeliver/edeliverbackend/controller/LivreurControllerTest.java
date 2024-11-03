package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.service.LivreurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LivreurControllerTest {

    @InjectMocks
    private LivreurController livreurController;

    @Mock
    private LivreurService livreurService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(livreurController).build();
    }

    @Test
    public void testGetAllLivreurs() throws Exception {
        Livreur livreur1 = new Livreur();
        livreur1.setId(1L);
        livreur1.setNom("Livreur 1");

        Livreur livreur2 = new Livreur();
        livreur2.setId(2L);
        livreur2.setNom("Livreur 2");

        List<Livreur> livreurs = Arrays.asList(livreur1, livreur2);

        when(livreurService.findAllLivreurs()).thenReturn(livreurs);

        mockMvc.perform(get("/api/livreurs"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLivreursDisponibles() throws Exception {
        Livreur livreur1 = new Livreur();
        livreur1.setId(1L);
        livreur1.setNom("Livreur 1");
        livreur1.setEtat(Livreur.EtatLivreur.DISPONIBLE);

        Livreur livreur2 = new Livreur();
        livreur2.setId(2L);
        livreur2.setNom("Livreur 2");
        livreur2.setEtat(Livreur.EtatLivreur.DISPONIBLE);

        List<Livreur> livreursDisponibles = Arrays.asList(livreur1, livreur2);

        when(livreurService.findLivreursByEtat(Livreur.EtatLivreur.DISPONIBLE)).thenReturn(livreursDisponibles);

        mockMvc.perform(get("/api/livreurs/disponibles"))
                .andExpect(status().isOk());
    }
}
