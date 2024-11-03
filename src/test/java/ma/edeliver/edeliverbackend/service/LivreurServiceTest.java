package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivreurServiceTest {

    @InjectMocks
    private LivreurService livreurService;

    @Mock
    private LivreurRepository livreurRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindLivreursByEtat() {
        Livreur livreur = new Livreur();
        livreur.setEtat(Livreur.EtatLivreur.DISPONIBLE);
        when(livreurRepository.findByEtat(Livreur.EtatLivreur.DISPONIBLE)).thenReturn(Arrays.asList(livreur));

        List<Livreur> result = livreurService.findLivreursByEtat(Livreur.EtatLivreur.DISPONIBLE);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindAllLivreurs() {
        Livreur livreur1 = new Livreur();
        Livreur livreur2 = new Livreur();
        when(livreurRepository.findAll()).thenReturn(Arrays.asList(livreur1, livreur2));

        List<Livreur> result = livreurService.findAllLivreurs();
        assertEquals(2, result.size());
    }
}
