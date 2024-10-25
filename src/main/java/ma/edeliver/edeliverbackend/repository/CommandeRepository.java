package ma.edeliver.edeliverbackend.repository;

import ma.edeliver.edeliverbackend.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
  // You can add custom query methods if needed.
  List<Commande> findByLivreurId(Long livreurId);
}