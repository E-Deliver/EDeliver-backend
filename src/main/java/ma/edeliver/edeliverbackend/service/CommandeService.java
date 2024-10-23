package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.edeliver.edeliverbackend.entity.Livreur;


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

  // Affecter une commande à un livreur
  @Autowired
  private LivreurRepository livreurRepository;

  // Méthode pour assigner une commande à un livreur
  public Commande assignerCommandeALivreur(Long commandeId, Long livreurId) {
    Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new IllegalArgumentException("Commande introuvable"));

    Livreur livreur = livreurRepository.findById(livreurId)
            .orElseThrow(() -> new IllegalArgumentException("Livreur introuvable"));

    commande.setLivreur(livreur);  // Assigner le livreur à la commande
    return commandeRepository.save(commande);  // Sauvegarder la commande avec le livreur assigné
  }

//Consulter les commandes affectées par le livreur
  public List<Commande> getCommandesByLivreurId(Long livreurId) {
    return commandeRepository.findByLivreurId(livreurId);

  }



}