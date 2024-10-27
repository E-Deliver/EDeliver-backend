package ma.edeliver.edeliverbackend.service;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

  @Autowired
  private UtilisateurRepository utilisateurRepository;

  public Utilisateur getUtilisateurByEmail(String email) {
    return utilisateurRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
  public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
    return utilisateurRepository.save(utilisateur);
  }
  // Add a method to get user details based on their role
  public List<Utilisateur> getUtilisateursByRole(Utilisateur.Role role) {
    return utilisateurRepository.findByRole(role);
  }
}
