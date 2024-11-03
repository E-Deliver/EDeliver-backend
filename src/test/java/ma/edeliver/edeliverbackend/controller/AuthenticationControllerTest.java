package ma.edeliver.edeliverbackend.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.edeliver.edeliverbackend.dto.LoginUserDTO;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.entity.Utilisateur.Role;
import ma.edeliver.edeliverbackend.service.AuthenticationService;
import ma.edeliver.edeliverbackend.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationControllerTest {
    
    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAuthenticateSuccess() throws Exception {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setNom("testUser");
        loginUserDTO.setEmail("testUser@example.com");
        loginUserDTO.setMotDePasse("password");
        loginUserDTO.setRole(Role.LIVREUR);

        Utilisateur user = new Utilisateur();
        user.setEmail("testUser@example.com");
        String token = "sampleToken";

        when(authenticationService.authenticate(any(LoginUserDTO.class))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("token", token);
        expectedResponse.put("message", "Authentification réussie");
        expectedResponse.put("user", user);

        mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginUserDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token))
                .andExpect(jsonPath("$.message").value("Authentification réussie"));
    }

    @Test
    public void testAuthenticateFailure() throws Exception {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setNom("testUser");
        loginUserDTO.setEmail("testUser@example.com");
        loginUserDTO.setMotDePasse("wrongPassword");

        when(authenticationService.authenticate(any(LoginUserDTO.class))).thenThrow(new IllegalArgumentException("Invalid credentials"));

        mockMvc.perform(post("/auth/login")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginUserDTO)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid credentials"));
    }
}
