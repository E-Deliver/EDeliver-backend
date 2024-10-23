package ma.edeliver.edeliverbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Client extends Utilisateur {


  private Long id_client;

  private String adresse;

  private String telephone;

}

