package ma.edeliver.edeliverbackend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Livreur extends Utilisateur {

  // private Long id_Livreur;

  private String localisation;
  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  private EtatLivreur etat;

  public enum EtatLivreur {
    DISPONIBLE, EN_LIVRAISON, INACTIF
  }

}
