package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private RegistrationService registrationService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void testRegisterLivreur() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setNom("Livreur Test");
        registerUserDTO.setEmail("livreur@test.com");
        // Ajoutez d'autres propriétés nécessaires à RegisterUserDTO

        // Mocker le comportement du service
        doNothing().when(registrationService).registerLivreur(any(RegisterUserDTO.class));

        mockMvc.perform(post("/auth/registerLivreur")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"Livreur Test\",\"email\":\"livreur@test.com\"}")) // Modifiez en fonction des propriétés de RegisterUserDTO
                .andExpect(status().isOk())
                .andExpect(content().string("Inscription réussie!"));

        // Vérifiez que le service a été appelé
        verify(registrationService, times(1)).registerLivreur(any(RegisterUserDTO.class));
    }
}
