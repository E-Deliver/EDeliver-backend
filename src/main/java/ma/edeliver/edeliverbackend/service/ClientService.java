package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  public Optional<Client> getClientById(Long id) {
    return clientRepository.findById(id);
  }

  // Example to find client by email
  public Optional<Client> getClientByEmail(String email) {
    return clientRepository.findByEmail(email);
  }

  // You can add methods for create, update, delete if needed
}
