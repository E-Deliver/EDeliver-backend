package ma.edeliver.edeliverbackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.entity.Utilisateur.Role;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;

@Service
public class RegistrationService {

    private final UtilisateurRepository utilisateurRepository;
    private final LivreurRepository livreurRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UtilisateurRepository utilisateurRepository, LivreurRepository livreurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.livreurRepository = livreurRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void registerLivreur(RegisterUserDTO input) {
        // Créer un livreur à partir de l'utilisateur
        Livreur livreur = new Livreur();
        livreur.setNom(input.getNom());
        livreur.setEmail(input.getEmail());
        livreur.setMotDePasse(passwordEncoder.encode(input.getMotDePasse()));
        livreur.setRole(Utilisateur.Role.LIVREUR);  // Définir le rôle de LIVREUR
        livreur.setLocalisation(input.getLocalisation());
        livreur.setEtat(Livreur.EtatLivreur.DISPONIBLE);
    
        // Sauvegarder le livreur (cela sauvegardera également l'utilisateur grâce à l'héritage)
        utilisateurRepository.save(livreur);  // `utilisateurRepository` gère l'enregistrement pour les deux entités
    }
      
}
