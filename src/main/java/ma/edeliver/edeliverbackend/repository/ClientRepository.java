package ma.edeliver.edeliverbackend.repository;

import ma.edeliver.edeliverbackend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
  Optional<Client> findByEmail(String email);  // Example method if you need to search by email
}
