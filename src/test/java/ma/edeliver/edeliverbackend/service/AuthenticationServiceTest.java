package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.dto.LoginUserDTO;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_Success() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("email@example.com");
        loginUserDTO.setMotDePasse("password");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMotDePasse("encodedPassword");

        when(utilisateurRepository.findByEmail(loginUserDTO.getEmail())).thenReturn(Optional.of(utilisateur));
        when(passwordEncoder.matches(loginUserDTO.getMotDePasse(), utilisateur.getMotDePasse())).thenReturn(true);

        Utilisateur result = authenticationService.authenticate(loginUserDTO);

        assertNotNull(result);
        assertEquals(utilisateur, result);
    }

    @Test
    void authenticate_Fail_EmailNotFound() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("wrong@example.com");
        loginUserDTO.setMotDePasse("password");

        when(utilisateurRepository.findByEmail(loginUserDTO.getEmail())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> authenticationService.authenticate(loginUserDTO));
    }

    @Test
    void authenticate_Fail_WrongPassword() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setEmail("email@example.com");
        loginUserDTO.setMotDePasse("wrongPassword");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMotDePasse("encodedPassword");

        when(utilisateurRepository.findByEmail(loginUserDTO.getEmail())).thenReturn(Optional.of(utilisateur));
        when(passwordEncoder.matches(loginUserDTO.getMotDePasse(), utilisateur.getMotDePasse())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.authenticate(loginUserDTO));
    }
}
