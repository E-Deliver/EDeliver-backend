package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import static ma.edeliver.edeliverbackend.entity.Utilisateur.Role.CLIENT;
import static ma.edeliver.edeliverbackend.entity.Utilisateur.Role.LIVREUR;

@Service
public class StatisticsService {

  private final UtilisateurRepository utilisateurRepository;
  private final CommandeRepository commandeRepository;

  public StatisticsService(UtilisateurRepository utilisateurRepository, CommandeRepository commandeRepository) {
    this.utilisateurRepository = utilisateurRepository;
    this.commandeRepository = commandeRepository;
  }

  public long getTotalCommands() {
    return commandeRepository.count();
  }

  public long getTotalClients() {
    return utilisateurRepository.countByRole(CLIENT);
  }

  public long getTotalLivreurs() {
    return utilisateurRepository.countByRole(LIVREUR);
  }
}

