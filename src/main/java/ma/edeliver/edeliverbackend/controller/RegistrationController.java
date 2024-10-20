package ma.edeliver.edeliverbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.edeliver.edeliverbackend.dto.RegisterUserDTO;
import ma.edeliver.edeliverbackend.service.RegistrationService;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registerLivreur")
    public ResponseEntity<String> registerLivreur(@RequestBody RegisterUserDTO registerUserDTO) {
        registrationService.registerLivreur(registerUserDTO);
        return ResponseEntity.ok("Inscription réussie!");
    }
}
