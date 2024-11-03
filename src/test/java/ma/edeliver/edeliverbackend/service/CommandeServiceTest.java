package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.entity.Notification;
import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import ma.edeliver.edeliverbackend.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private LivreurRepository livreurRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private CommandeService commandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCommandes() {
        List<Commande> commandes = List.of(new Commande(), new Commande());
        when(commandeRepository.findAll()).thenReturn(commandes);

        List<Commande> result = commandeService.getAllCommandes();

        assertEquals(2, result.size());
        verify(commandeRepository, times(1)).findAll();
    }

    @Test
    void assignerCommandeALivreur_Success() {
        Commande commande = new Commande();
        commande.setIdCommande(1L);
        Livreur livreur = new Livreur();
        Client client = new Client();
        commande.setClient(client);

        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(livreurRepository.findById(2L)).thenReturn(Optional.of(livreur));
        when(commandeRepository.save(commande)).thenReturn(commande);

        Commande result = commandeService.assignerCommandeALivreur(1L, 2L);

        assertEquals("En cours", result.getStatut());
        assertEquals(livreur, result.getLivreur());
        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(commandeRepository, times(1)).save(commande);
    }

    @Test
    void assignerCommandeALivreur_Fail_CommandeNotFound() {
        when(commandeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> commandeService.assignerCommandeALivreur(1L, 2L));
    }

    @Test
    void assignerCommandeALivreur_Fail_LivreurNotFound() {
        Commande commande = new Commande();
        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(livreurRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> commandeService.assignerCommandeALivreur(1L, 2L));
    }

    @Test
    void updateStatus_Livree() {
        Commande commande = new Commande();
        commande.setIdCommande(1L);
        Client client = new Client();
        commande.setClient(client);

        when(commandeRepository.findById(1L)).thenReturn(Optional.of(commande));
        when(commandeRepository.save(commande)).thenReturn(commande);

        commandeService.updateStatus(1L, "Livrée");

        assertEquals("Livrée", commande.getStatut());
        verify(notificationRepository, times(1)).save(any(Notification.class));
        verify(commandeRepository, times(1)).save(commande);
    }
    
    @Test
    void getCommandesByClientIdAndStatus() {
        Long clientId = 1L;
        String statut = "En cours";
        List<Commande> commandes = List.of(new Commande(), new Commande());

        when(commandeRepository.findByClientIdAndStatut(clientId, statut)).thenReturn(commandes);

        List<Commande> result = commandeService.getCommandesByClientIdAndStatus(clientId, statut);

        assertEquals(2, result.size());
        verify(commandeRepository, times(1)).findByClientIdAndStatut(clientId, statut);
    }
    
    @Test
    void searchCommandes_ByAddress() {
        String address = "123 Main St";
        List<Commande> commandes = List.of(new Commande());

        when(commandeRepository.findByAdresseLivraisonContaining(address)).thenReturn(commandes);

        List<Commande> result = commandeService.searchCommandes(address, null, null);

        assertEquals(1, result.size());
        verify(commandeRepository, times(1)).findByAdresseLivraisonContaining(address);
    }
}
