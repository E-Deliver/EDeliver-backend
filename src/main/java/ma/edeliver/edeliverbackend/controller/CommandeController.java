package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

  @Autowired
  private CommandeService commandeService;

  // Get all commandes
  @GetMapping
  public List<Commande> getAllCommandes() {
    return commandeService.getAllCommandes();
  }

  // Get a single commande by ID
  @GetMapping("/{id}")
  public Commande getCommandeById(@PathVariable Long id) {
    return commandeService.getCommandeById(id);
  }
}
