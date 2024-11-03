package ma.edeliver.edeliverbackend.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.http.MediaType.*;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testGetAllClients() throws Exception {
        Client client1 = new Client(); // Configurez les données de test
        client1.setId(1L);
        client1.setNom("Client 1");
        
        Client client2 = new Client();
        client2.setId(2L);
        client2.setNom("Client 2");

        List<Client> clients = Arrays.asList(client1, client2);

        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Client 1"))
                .andExpect(jsonPath("$[1].nom").value("Client 2"));
    }

    @Test
    public void testGetClientByIdSuccess() throws Exception {
        Long clientId = 1L;
        Client client = new Client(); // Configurez les données de test
        client.setId(clientId);
        client.setNom("Client 1");

        when(clientService.getClientById(clientId)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/clients/{id}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Client 1"));
    }

    @Test
    public void testGetClientByIdNotFound() throws Exception {
        Long clientId = 1L;

        when(clientService.getClientById(clientId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clients/{id}", clientId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetClientByEmailSuccess() throws Exception {
        String email = "client@example.com";
        Client client = new Client(); // Configurez les données de test
        client.setEmail(email);
        client.setNom("Client 1");

        when(clientService.getClientByEmail(email)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/clients/email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Client 1"));
    }

    @Test
    public void testGetClientByEmailNotFound() throws Exception {
        String email = "unknown@example.com";

        when(clientService.getClientByEmail(email)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clients/email/{email}", email))
                .andExpect(status().isNotFound());
    }
    
}
