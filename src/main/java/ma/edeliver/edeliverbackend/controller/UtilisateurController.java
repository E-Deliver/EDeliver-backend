package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

  @Autowired
  private UtilisateurService utilisateurService;

  // Get the profile of the currently authenticated user
  @GetMapping("/profile")
  public ResponseEntity<Utilisateur> getUserProfile(Authentication authentication) {
    String email = authentication.getName();
    Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
    return ResponseEntity.ok(utilisateur);
  }

  // Get all users by their role
  @GetMapping("/role/{role}")
  public ResponseEntity<List<Utilisateur>> getUsersByRole(@PathVariable("role") String roleStr) {
    Utilisateur.Role role;
    try {
      role = Utilisateur.Role.valueOf(roleStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(null);
    }
    List<Utilisateur> utilisateurs = utilisateurService.getUtilisateursByRole(role);
    return ResponseEntity.ok(utilisateurs);
  }
}