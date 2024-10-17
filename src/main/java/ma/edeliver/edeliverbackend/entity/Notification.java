package ma.edeliver.edeliverbackend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idNotification;



  private String message;

  private LocalDateTime dateEnvoi;

  // ManyToOne relationship with Client
  @ManyToOne
  @JoinColumn(name = "id_client")  // Foreign key column
  private Client client;

}

