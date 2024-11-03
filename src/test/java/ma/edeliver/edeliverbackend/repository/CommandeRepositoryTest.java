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
        
        Commande commande1 = new Commande();
        commande1.setStatut("Livrée");
        commande1.setAdresseLivraison("123 Main St");
        commande1.setDateCommande("2024-11-03");
        
        Commande commande2 = new Commande();
        commande2.setStatut("En cours");
        commande2.setAdresseLivraison("456 Oak Ave");
        commande2.setDateCommande("2024-11-02");
        
        commandes.add(commande1);
        commandes.add(commande2);
    }

    @Test
    public void testCountLivreeCommands() {
        when(commandeRepository.countLivreeCommands()).thenReturn(1L);

        Long count = commandeRepository.countLivreeCommands();

        assertEquals(1L, count);
    }

    @Test
    public void testCountNonLivreeCommands() {
        when(commandeRepository.countNonLivreeCommands()).thenReturn(1L);

        Long count = commandeRepository.countNonLivreeCommands();

        assertEquals(1L, count);
    }

    @Test
    public void testFindByLivreurId() {
        List<Commande> livreurCommandes = new ArrayList<>();
        Commande commande = new Commande();
        commande.setStatut("Livrée");
        livreurCommandes.add(commande);

        when(commandeRepository.findByLivreurId(1L)).thenReturn(livreurCommandes);

        List<Commande> foundCommandes = commandeRepository.findByLivreurId(1L);

        assertEquals(1, foundCommandes.size());
        assertEquals("Livrée", foundCommandes.get(0).getStatut());
    }

    @Test
    public void testFindByClientIdAndStatut() {
        when(commandeRepository.findByClientIdAndStatut(1L, "En cours")).thenReturn(commandes);

        List<Commande> foundCommandes = commandeRepository.findByClientIdAndStatut(1L, "En cours");

        assertEquals(2, foundCommandes.size());
        assertEquals("Livrée", foundCommandes.get(0).getStatut());
    }

    @Test
    public void testFindByAdresseLivraisonContaining() {
        when(commandeRepository.findByAdresseLivraisonContaining("Main")).thenReturn(commandes);

        List<Commande> foundCommandes = commandeRepository.findByAdresseLivraisonContaining("Main");

        assertEquals(2, foundCommandes.size());
        assertEquals("123 Main St", foundCommandes.get(0).getAdresseLivraison());
    }

    @Test
    public void testFindByDateCommande() {
        when(commandeRepository.findByDateCommande("2024-11-03")).thenReturn(commandes);

        List<Commande> foundCommandes = commandeRepository.findByDateCommande("2024-11-03");

        assertEquals(2, foundCommandes.size());
        assertEquals("2024-11-03", foundCommandes.get(0).getDateCommande());
    }

    @Test
    public void testFindByStatut() {
        List<Commande> enCoursCommandes = new ArrayList<>();
        Commande commande = new Commande();
        commande.setStatut("En cours");
        enCoursCommandes.add(commande);

        when(commandeRepository.findByStatut("En cours")).thenReturn(enCoursCommandes);

        List<Commande> foundCommandes = commandeRepository.findByStatut("En cours");

        assertEquals(1, foundCommandes.size());
        assertEquals("En cours", foundCommandes.get(0).getStatut());
    }
}
