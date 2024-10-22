package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")  // Base URL for the client API
public class ClientController {

  @Autowired
  private ClientService clientService;

  // Get all clients
  @GetMapping
  public List<Client> getAllClients() {
    return clientService.getAllClients();
  }

  // Get client by ID
  @GetMapping("/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable Long id) {
    Optional<Client> client = clientService.getClientById(id);
    return client.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Optional: Get client by email
  @GetMapping("/email/{email}")
  public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
    Optional<Client> client = clientService.getClientByEmail(email);
    return client.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
