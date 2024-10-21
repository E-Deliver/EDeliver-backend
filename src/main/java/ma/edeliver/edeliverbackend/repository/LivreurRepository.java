package ma.edeliver.edeliverbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.edeliver.edeliverbackend.entity.Livreur;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {
}
