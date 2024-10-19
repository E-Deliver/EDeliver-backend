package ma.edeliver.edeliverbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.edeliver.edeliverbackend.dto.LoginUserDTO;
import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.service.AuthenticationService;
import ma.edeliver.edeliverbackend.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        try {
            Utilisateur authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);

            // Préparer la réponse avec les données utilisateur et le token
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("message", "Authentification réussie");
            response.put("user", authenticatedUser); // Ajouter les données utilisateur

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Utiliser Map<String, Object> pour l'erreur aussi
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}
