package ma.edeliver.edeliverbackend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.edeliver.edeliverbackend.dto.LoginUserDTO;
import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;

@Service
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UtilisateurRepository utilisateurRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur signup(RegisterUserDTO input) {
        Utilisateur utilisateur = new Utilisateur();
        
        utilisateur.setNom(input.getNom());
        utilisateur.setEmail(input.getEmail());

        String motDePasseHache = passwordEncoder.encode(input.getMotDePasse());
        utilisateur.setMotDePasse(motDePasseHache);
        
        return utilisateurRepository.save(utilisateur);
    }    

    public Utilisateur authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getMotDePasse()
                )
        );

        return utilisateurRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}