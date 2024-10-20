package ma.edeliver.edeliverbackend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.edeliver.edeliverbackend.dto.LoginUserDTO;
import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;

@Service
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur authenticate(LoginUserDTO input) {
        // Vérifie si l'utilisateur existe par email
        Utilisateur utilisateur = utilisateurRepository.findByEmail(input.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Email non trouvé"));

        // Vérifie si le mot de passe est correct
        if (!passwordEncoder.matches(input.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }

        // Si tout est correct, retourne l'utilisateur authentifié
        return utilisateur;
    }
}
