package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

  @Autowired
  private CommandeRepository commandeRepository;

  // Retrieve all commandes
  public List<Commande> getAllCommandes() {
    return commandeRepository.findAll();
  }

  // Retrieve a specific commande by ID
  public Commande getCommandeById(Long id) {
    return commandeRepository.findById(id).orElse(null);
  }

  // You can add other CRUD operations if needed
}
