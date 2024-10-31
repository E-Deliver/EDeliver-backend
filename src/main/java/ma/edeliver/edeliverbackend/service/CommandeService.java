package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import ma.edeliver.edeliverbackend.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.entity.Notification;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  @Autowired
  private NotificationRepository notificationRepository;

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
    commande.setStatut("En cours");

    LocalDateTime currentDateTime = LocalDateTime.now();
    Long idCommande = commande.getIdCommande();
    Client client = commande.getClient();

    Notification notification = new Notification();
    notification.setDateEnvoi(currentDateTime);
    notification.setMessage("Votre commande No. " + idCommande + " est en cours de livraison.");
    notification.setClient(client);

    notificationRepository.save(notification);

    return commandeRepository.save(commande);  // Sauvegarder la commande avec le livreur assigné
  }

//Consulter les commandes affectées par le livreur
  public List<Commande> getCommandesByLivreurId(Long livreurId) {
    return commandeRepository.findByLivreurId(livreurId);

  }

  public void updateStatus(Long id, String newStatus) {
    Commande commande = commandeRepository.findById(id).orElseThrow();

    Long idCommande = commande.getIdCommande();
    Client client = commande.getClient();

    commande.setStatut("Livrée");
    commandeRepository.save(commande);

    LocalDateTime currentDateTime = LocalDateTime.now();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // String datetimeStamp = currentDateTime.format(formatter);

    Notification notification = new Notification();
    notification.setDateEnvoi(currentDateTime);
    notification.setMessage("Votre commande No. " + idCommande + " est désormais livrée avec succès !");
    notification.setClient(client);

    notificationRepository.save(notification);
  }

  // Dans CommandeService
  public List<Commande> getCommandesByClientIdAndStatus(Long clientId, String statut) {
    return commandeRepository.findByClientIdAndStatut(clientId, statut);
  }

  public List<Long> getClientIdsByLivreurId(Long livreurId) {
        List<Commande> commandes = commandeRepository.findByLivreurId(livreurId);
        return commandes.stream()
                        .map(commande -> commande.getClient().getId())
                        .distinct()
                        .collect(Collectors.toList());
    }

}