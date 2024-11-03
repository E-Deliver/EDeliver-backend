package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private LivreurRepository livreurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterLivreur() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setNom("Test Nom");
        dto.setEmail("test@example.com");
        dto.setMotDePasse("password");
        dto.setLocalisation("Test Location");

        when(passwordEncoder.encode(dto.getMotDePasse())).thenReturn("encodedPassword");

        registrationService.registerLivreur(dto);

        verify(utilisateurRepository, times(1)).save(any(Livreur.class));
    }
}
