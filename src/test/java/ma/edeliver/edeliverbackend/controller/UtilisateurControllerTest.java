package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UtilisateurControllerTest {

    @InjectMocks
    private UtilisateurController utilisateurController;

    @Mock
    private UtilisateurService utilisateurService;

    @Mock
    private Authentication authentication;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
    }

    @Test
    public void testGetUserProfile() throws Exception {
        // Préparer les données de test
        String email = "test@example.com";
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);
        utilisateur.setNom("Test User");

        // Configurer le comportement du mock
        when(authentication.getName()).thenReturn(email);
        when(utilisateurService.getUtilisateurByEmail(email)).thenReturn(utilisateur);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(get("/api/utilisateurs/profile")
                .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.nom").value("Test User"));
    }

    @Test
    public void testGetUsersByRole() throws Exception {
        // Préparer les données de test
        List<Utilisateur> utilisateurs = Collections.singletonList(new Utilisateur());
        
        // Configurer le comportement du mock
        when(utilisateurService.getUtilisateursByRole(Utilisateur.Role.ADMINISTRATEUR)).thenReturn(utilisateurs);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(get("/api/utilisateurs/role/USER"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateUserProfile() throws Exception {
        // Préparer les données de test
        String email = "test@example.com";
        Utilisateur existingUser = new Utilisateur();
        existingUser.setEmail(email);
        existingUser.setNom("Old Name");

        Utilisateur updatedUser = new Utilisateur();
        updatedUser.setNom("Updated Name");
        updatedUser.setEmail(email);

        // Configurer les comportements du mock
        when(authentication.getName()).thenReturn(email);
        when(utilisateurService.getUtilisateurByEmail(email)).thenReturn(existingUser);
        when(utilisateurService.saveUtilisateur(existingUser)).thenReturn(updatedUser);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(put("/api/utilisateurs/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"Updated Name\", \"email\":\"test@example.com\"}")
                .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Updated Name"));
    }
}
