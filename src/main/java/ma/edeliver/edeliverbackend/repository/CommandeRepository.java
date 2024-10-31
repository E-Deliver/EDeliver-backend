package ma.edeliver.edeliverbackend.repository;

import ma.edeliver.edeliverbackend.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
  // You can add custom query methods if needed.
  List<Commande> findByLivreurId(Long livreurId);
  long count(); // Counts total orders
  // Count commands with "livrée" status
  @Query("SELECT COUNT(c) FROM Commande c WHERE c.statut = 'livrée'")
  Long countLivreeCommands();

  // Count commands with non "livrée" status
  @Query("SELECT COUNT(c) FROM Commande c WHERE c.statut != 'livrée'")
  Long countNonLivreeCommands();
  List<Commande> findByClientIdAndStatut(Long clientId, String statut);
}
