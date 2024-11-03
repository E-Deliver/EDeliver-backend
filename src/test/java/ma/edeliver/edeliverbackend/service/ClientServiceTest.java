package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId_client(1L);
        client.setEmail("client@example.com");
        client.setAdresse("123 Street");
        client.setTelephone("1234567890");
    }

    @Test
    void getAllClients() {
        List<Client> clients = List.of(new Client(), new Client());
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();

        assertEquals(2, result.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void getClientById_Found() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(1L);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    void getClientById_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Client> result = clientService.getClientById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void getClientByEmail_Found() {
        when(clientRepository.findByEmail("client@example.com")).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientByEmail("client@example.com");

        assertTrue(result.isPresent());
        assertEquals("client@example.com", result.get().getEmail());
    }

    @Test
    void getClientByEmail_NotFound() {
        when(clientRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        Optional<Client> result = clientService.getClientByEmail("nonexistent@example.com");

        assertFalse(result.isPresent());
    }
}
