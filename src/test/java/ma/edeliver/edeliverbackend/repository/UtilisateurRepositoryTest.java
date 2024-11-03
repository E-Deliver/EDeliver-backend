package ma.edeliver.edeliverbackend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurRepositoryTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    private Utilisateur utilisateur;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        utilisateur = new Utilisateur();
        utilisateur.setEmail("user@example.com");
        utilisateur.setRole(Utilisateur.Role.CLIENT);
        utilisateur.setNom("John Doe");
    }

    @Test
    public void testFindByEmail() {
        when(utilisateurRepository.findByEmail("user@example.com")).thenReturn(Optional.of(utilisateur));

        Optional<Utilisateur> foundUser = utilisateurRepository.findByEmail("user@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("user@example.com", foundUser.get().getEmail());
        assertEquals("John Doe", foundUser.get().getNom());
    }

    @Test
    public void testExistsByEmail() {
        when(utilisateurRepository.existsByEmail("user@example.com")).thenReturn(true);

        boolean exists = utilisateurRepository.existsByEmail("user@example.com");

        assertTrue(exists);
    }

    @Test
    public void testCountByRole() {
        when(utilisateurRepository.countByRole(Utilisateur.Role.CLIENT)).thenReturn(5L);

        long count = utilisateurRepository.countByRole(Utilisateur.Role.CLIENT);

        assertEquals(5L, count);
    }

    @Test
    public void testFindByRole() {
        List<Utilisateur> clients = new ArrayList<>();
        clients.add(utilisateur);

        when(utilisateurRepository.findByRole(Utilisateur.Role.CLIENT)).thenReturn(clients);

        List<Utilisateur> foundClients = utilisateurRepository.findByRole(Utilisateur.Role.CLIENT);

        assertEquals(1, foundClients.size());
        assertEquals("user@example.com", foundClients.get(0).getEmail());
        assertEquals(Utilisateur.Role.CLIENT, foundClients.get(0).getRole());
    }
}
