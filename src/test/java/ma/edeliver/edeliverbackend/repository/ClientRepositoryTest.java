package ma.edeliver.edeliverbackend.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import ma.edeliver.edeliverbackend.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    private Client client;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setEmail("test@example.com");
    }

    @Test
    public void testFindByEmail() {
        when(clientRepository.findByEmail("test@example.com")).thenReturn(Optional.of(client));

        Optional<Client> foundClient = clientRepository.findByEmail("test@example.com");

        assertTrue(foundClient.isPresent());
        assertTrue(foundClient.get().getEmail().equals("test@example.com"));
    }
}
