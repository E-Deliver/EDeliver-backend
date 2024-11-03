package ma.edeliver.edeliverbackend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import ma.edeliver.edeliverbackend.entity.Commande;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CommandeRepositoryTest {

    @Mock
    private CommandeRepository commandeRepository;

    private List<Commande> commandes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        commandes = new ArrayList<>();
        Commande commande = new Commande();
        commande.setStatut("EN_COURS");
        commandes.add(commande);
    }

    @Test
    public void testFindByClientIdAndStatut() {
        when(commandeRepository.findByClientIdAndStatut(1L, "EN_COURS")).thenReturn(commandes);

        List<Commande> foundCommandes = commandeRepository.findByClientIdAndStatut(1L, "EN_COURS");

        assertEquals(1, foundCommandes.size());
        assertEquals("EN_COURS", foundCommandes.get(0).getStatut());
    }
}
