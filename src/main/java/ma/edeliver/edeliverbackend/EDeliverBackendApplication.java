package ma.edeliver.edeliverbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class EDeliverBackendApplication {

  public static void main(String[] args) {

    SpringApplication.run(EDeliverBackendApplication.class, args);
  }

}
