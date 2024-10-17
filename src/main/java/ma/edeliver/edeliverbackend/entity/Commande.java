package ma.edeliver.edeliverbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.entity.Livreur;

import java.util.List;

@Data
@Entity
public class Commande {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCommande;

  @ElementCollection
  @CollectionTable(name = "commande_produits", joinColumns = @JoinColumn(name = "idCommande"))
  @Column(name = "produit")
  private List<String> produits;

  private String adresseLivraison;
  private String statut;
  private String dateCommande;

  @ManyToOne
  private Livreur livreur;

  @ManyToOne
  private Client client;
}
