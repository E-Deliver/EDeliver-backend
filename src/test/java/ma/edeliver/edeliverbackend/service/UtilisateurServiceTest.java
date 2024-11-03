package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUtilisateurByEmail_Success() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@example.com");

        when(utilisateurRepository.findByEmail("test@example.com")).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.getUtilisateurByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testGetUtilisateurByEmail_NotFound() {
        when(utilisateurRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> utilisateurService.getUtilisateurByEmail("notfound@example.com"));
    }

    @Test
    void testSaveUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("newuser@example.com");

        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.saveUtilisateur(utilisateur);

        assertNotNull(result);
        assertEquals("newuser@example.com", result.getEmail());
        verify(utilisateurRepository, times(1)).save(utilisateur);
    }

    @Test
    void testGetUtilisateursByRole() {
        Utilisateur.Role role = Utilisateur.Role.ADMINISTRATEUR;
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setRole(role);
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setRole(role);

        when(utilisateurRepository.findByRole(role)).thenReturn(List.of(utilisateur1, utilisateur2));

        List<Utilisateur> result = utilisateurService.getUtilisateursByRole(role);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(u -> u.getRole() == role));
    }
}
