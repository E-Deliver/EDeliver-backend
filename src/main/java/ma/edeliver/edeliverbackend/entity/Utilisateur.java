package ma.edeliver.edeliverbackend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Allows inheritance in JPA
public class Utilisateur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;

  private String email;

  private String motDePasse;

  @Enumerated(EnumType.STRING)
  private Role role;

  public enum Role {
    ADMINISTRATEUR, LIVREUR, CLIENT
  }

}
