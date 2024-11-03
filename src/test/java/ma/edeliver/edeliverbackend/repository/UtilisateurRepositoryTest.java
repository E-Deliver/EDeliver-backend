package ma.edeliver.edeliverbackend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    }

    @Test
    public void testFindByEmail() {
        when(utilisateurRepository.findByEmail("user@example.com")).thenReturn(Optional.of(utilisateur));

        Optional<Utilisateur> foundUser = utilisateurRepository.findByEmail("user@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("user@example.com", foundUser.get().getEmail());
    }
}
