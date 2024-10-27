package ma.edeliver.edeliverbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "commande")  // Ensure the table name matches
public class Commande {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_commande")  // Map to the actual column name in the table
  private Long idCommande;

  @ElementCollection
  @CollectionTable(name = "commande_produits", joinColumns = @JoinColumn(name = "id_commande"))
  @Column(name = "produit")
  private List<String> produits;

  @Column(name = "adresse_livraison")  // Match the column name
  private String adresseLivraison;

  @Column(name = "statut")  // This seems to match, no change needed
  private String statut;

  @Column(name = "date_commande")  // Match the column name
  private String dateCommande;

  @ManyToOne
  @JoinColumn(name = "livreur_id")  // Specify the join column name
  private Livreur livreur;

  @ManyToOne
  @JoinColumn(name = "client_id")  // Specify the join column name
  private Client client;

  // Getter pour le nom du livreur
  public String getLivreurNom() {
    return (livreur != null) ? livreur.getNom() : null;
  }
}