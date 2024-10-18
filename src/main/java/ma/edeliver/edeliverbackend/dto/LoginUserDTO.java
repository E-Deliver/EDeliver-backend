package ma.edeliver.edeliverbackend.dto;

import lombok.Data;
import ma.edeliver.edeliverbackend.entity.Utilisateur.Role;

@Data
public class LoginUserDTO {
    private Long id;
    private String nom;
    private String email;
    private String motDePasse; 
    private Role role;
}
