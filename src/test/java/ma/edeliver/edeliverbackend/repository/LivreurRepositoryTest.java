package ma.edeliver.edeliverbackend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import ma.edeliver.edeliverbackend.entity.Livreur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class LivreurRepositoryTest {

    @Mock
    private LivreurRepository livreurRepository;

    private List<Livreur> livreurs;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        livreurs = new ArrayList<>();
        Livreur livreur = new Livreur();
        livreur.setEtat(Livreur.EtatLivreur.DISPONIBLE);
        livreurs.add(livreur);
    }

    @Test
    public void testFindByEtat() {
        when(livreurRepository.findByEtat(Livreur.EtatLivreur.DISPONIBLE)).thenReturn(livreurs);

        List<Livreur> foundLivreurs = livreurRepository.findByEtat(Livreur.EtatLivreur.DISPONIBLE);

        assertEquals(1, foundLivreurs.size());
        assertEquals(Livreur.EtatLivreur.DISPONIBLE, foundLivreurs.get(0).getEtat());
    }
}
