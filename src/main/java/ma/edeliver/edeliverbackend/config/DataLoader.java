package ma.edeliver.edeliverbackend.config;

import ma.edeliver.edeliverbackend.entity.Client;
import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.entity.Notification;
import ma.edeliver.edeliverbackend.entity.Utilisateur;
import ma.edeliver.edeliverbackend.repository.ClientRepository;
import ma.edeliver.edeliverbackend.repository.CommandeRepository;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;
import ma.edeliver.edeliverbackend.repository.NotificationRepository;
import ma.edeliver.edeliverbackend.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LivreurRepository livreurRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si la base de données est vide
        if (clientRepository.count() == 0 && livreurRepository.count() == 0) {

            // Administrateur 
            Utilisateur user = new Utilisateur();
            user.setNom("Ali Bennis");
            user.setEmail("ali.bennis@example.com");
            user.setMotDePasse(passwordEncoder.encode("123"));
            user.setRole(Utilisateur.Role.ADMINISTRATEUR);
            utilisateurRepository.save(user);

            // Insert test data for Utilisateur (Client)
            // Client 1
            Client client1 = new Client();
            client1.setNom("Omar El Madani");
            client1.setEmail("omar.elmadani@example.com");
            client1.setMotDePasse(passwordEncoder.encode("123"));
            client1.setRole(Utilisateur.Role.CLIENT);
            client1.setAdresse("Martil Al Bayt Al Atiq");
            client1.setTelephone("0612345678");
            clientRepository.save(client1);

            // Client 2
            Client client2 = new Client();
            client2.setNom("Sara El Amrani");
            client2.setEmail("sara.amrani@example.com");
            client2.setMotDePasse(passwordEncoder.encode("123"));
            client2.setRole(Utilisateur.Role.CLIENT);
            client2.setAdresse("Bd Anfa, Casablanca");
            client2.setTelephone("0623456789");
            clientRepository.save(client2);

            // Client 3
            Client client3 = new Client();
            client3.setNom("Khalid El Fassi");
            client3.setEmail("khalid.fassi@example.com");
            client3.setMotDePasse(passwordEncoder.encode("123"));
            client3.setRole(Utilisateur.Role.CLIENT);
            client3.setAdresse("Derb Omar, Casablanca");
            client3.setTelephone("0634567890");
            clientRepository.save(client3);

            // Client 4
            Client client4 = new Client();
            client4.setNom("Yasmine Mouline");
            client4.setEmail("yasmine.mouline@example.com");
            client4.setMotDePasse(passwordEncoder.encode("123"));
            client4.setRole(Utilisateur.Role.CLIENT);
            client4.setAdresse("Hay Riad, Rabat");
            client4.setTelephone("0645678901");
            clientRepository.save(client4);

            // Client 5
            Client client5 = new Client();
            client5.setNom("Hamid Zemmouri");
            client5.setEmail("hamid.zemmouri@example.com");
            client5.setMotDePasse(passwordEncoder.encode("123"));
            client5.setRole(Utilisateur.Role.CLIENT);
            client5.setAdresse("Route de Meknès, Fès");
            client5.setTelephone("0656789012");
            clientRepository.save(client5);

            // Client 6
            Client client6 = new Client();
            client6.setNom("Nora El Baz");
            client6.setEmail("nora.baz@example.com");
            client6.setMotDePasse(passwordEncoder.encode("123"));
            client6.setRole(Utilisateur.Role.CLIENT);
            client6.setAdresse("Avenue Mohammed V, Agadir");
            client6.setTelephone("0667890123");
            clientRepository.save(client6);

            // Client 7
            Client client7 = new Client();
            client7.setNom("Mehdi Darif");
            client7.setEmail("mehdi.darif@example.com");
            client7.setMotDePasse(passwordEncoder.encode("123"));
            client7.setRole(Utilisateur.Role.CLIENT);
            client7.setAdresse("Rue Oujda, Tanger");
            client7.setTelephone("0678901234");
            clientRepository.save(client7);

            // Client 8
            Client client8 = new Client();
            client8.setNom("Fatima Zahra");
            client8.setEmail("fatima.zahra@example.com");
            client8.setMotDePasse(passwordEncoder.encode("123"));
            client8.setRole(Utilisateur.Role.CLIENT);
            client8.setAdresse("Place Djemaa El Fna, Marrakech");
            client8.setTelephone("0689012345");
            clientRepository.save(client8);

            // Client 9
            Client client9 = new Client();
            client9.setNom("Aziz Naciri");
            client9.setEmail("aziz.naciri@example.com");
            client9.setMotDePasse(passwordEncoder.encode("123"));
            client9.setRole(Utilisateur.Role.CLIENT);
            client9.setAdresse("Quartier Palmier, Casablanca");
            client9.setTelephone("0690123456");
            clientRepository.save(client9);

            // Client 10
            Client client10 = new Client();
            client10.setNom("Leila El Madi");
            client10.setEmail("leila.madi@example.com");
            client10.setMotDePasse(passwordEncoder.encode("123"));
            client10.setRole(Utilisateur.Role.CLIENT);
            client10.setAdresse("Boulevard Hassan II, Rabat");
            client10.setTelephone("0701234567");
            clientRepository.save(client10);

            // Livreur 1
            Livreur livreur1 = new Livreur();
            livreur1.setNom("Omar Karimi");
            livreur1.setEmail("omar.karimi@example.com");
            livreur1.setMotDePasse(passwordEncoder.encode("123"));
            livreur1.setRole(Utilisateur.Role.LIVREUR);
            livreur1.setLocalisation("Bd Al Qods, Casablanca");
            livreur1.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur1);

            // Livreur 2
            Livreur livreur2 = new Livreur();
            livreur2.setNom("Salma Lahlou");
            livreur2.setEmail("salma.lahlou@example.com");
            livreur2.setMotDePasse(passwordEncoder.encode("123"));
            livreur2.setRole(Utilisateur.Role.LIVREUR);
            livreur2.setLocalisation("Hay Salam, Agadir");
            livreur2.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur2);

            // Livreur 3
            Livreur livreur3 = new Livreur();
            livreur3.setNom("Amine Jebli");
            livreur3.setEmail("amine.jebli@example.com");
            livreur3.setMotDePasse(passwordEncoder.encode("123"));
            livreur3.setRole(Utilisateur.Role.LIVREUR);
            livreur3.setLocalisation("Avenue Mohammed V, Marrakech");
            livreur3.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur3);

            // Livreur 4
            Livreur livreur4 = new Livreur();
            livreur4.setNom("Karima Fakhri");
            livreur4.setEmail("karima.fakhri@example.com");
            livreur4.setMotDePasse(passwordEncoder.encode("123"));
            livreur4.setRole(Utilisateur.Role.LIVREUR);
            livreur4.setLocalisation("Rue des Far, Fès");
            livreur4.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur4);

            // Livreur 5
            Livreur livreur5 = new Livreur();
            livreur5.setNom("Hicham Kabbaj");
            livreur5.setEmail("hicham.kabbaj@example.com");
            livreur5.setMotDePasse(passwordEncoder.encode("123"));
            livreur5.setRole(Utilisateur.Role.LIVREUR);
            livreur5.setLocalisation("Boulevard Zerktouni, Casablanca");
            livreur5.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur5);

            // Livreur 6
            Livreur livreur6 = new Livreur();
            livreur6.setNom("Latifa Tazi");
            livreur6.setEmail("latifa.tazi@example.com");
            livreur6.setMotDePasse(passwordEncoder.encode("123"));
            livreur6.setRole(Utilisateur.Role.LIVREUR);
            livreur6.setLocalisation("Quartier Hassan, Rabat");
            livreur6.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur6);

            // Livreur 7
            Livreur livreur7 = new Livreur();
            livreur7.setNom("Rachid Maftoul");
            livreur7.setEmail("rachid.maftoul@example.com");
            livreur7.setMotDePasse(passwordEncoder.encode("123"));
            livreur7.setRole(Utilisateur.Role.LIVREUR);
            livreur7.setLocalisation("Boulevard Ghandi, Casablanca");
            livreur7.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur7);

            // Livreur 8
            Livreur livreur8 = new Livreur();
            livreur8.setNom("Zineb El Alami");
            livreur8.setEmail("zineb.elalami@example.com");
            livreur8.setMotDePasse(passwordEncoder.encode("123"));
            livreur8.setRole(Utilisateur.Role.LIVREUR);
            livreur8.setLocalisation("Avenue de la Liberté, Tanger");
            livreur8.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur8);

            // Livreur 9
            Livreur livreur9 = new Livreur();
            livreur9.setNom("Ahmed El Mansouri");
            livreur9.setEmail("ahmed.mansouri@example.com");
            livreur9.setMotDePasse(passwordEncoder.encode("123"));
            livreur9.setRole(Utilisateur.Role.LIVREUR);
            livreur9.setLocalisation("Place El Massira, Laâyoune");
            livreur9.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur9);

            // Livreur 10
            Livreur livreur10 = new Livreur();
            livreur10.setNom("Fatima Bouzid");
            livreur10.setEmail("fatima.bouzid@example.com");
            livreur10.setMotDePasse(passwordEncoder.encode("123"));
            livreur10.setRole(Utilisateur.Role.LIVREUR);
            livreur10.setLocalisation("Rue de Fès, Oujda");
            livreur10.setEtat(Livreur.EtatLivreur.DISPONIBLE);
            livreurRepository.save(livreur10);

            // Commande 1
            Commande commande1 = new Commande();
            commande1.setAdresseLivraison("Avenue Hassan II, Rabat");
            commande1.setStatut("En attente");
            commande1.setDateCommande(LocalDateTime.now().toString());
            commande1.setLivreur(null);
            commande1.setClient(client1);
            commande1.setProduits(List.of("Livre", "Stylo"));
            commandeRepository.save(commande1);

            // Commande 2
            Commande commande2 = new Commande();
            commande2.setAdresseLivraison("Bd Mohammed V, Casablanca");
            commande2.setStatut("En cours");
            commande2.setDateCommande(LocalDateTime.now().minusDays(1).toString());
            commande2.setLivreur(livreur2);
            commande2.setClient(client2);
            commande2.setProduits(List.of("Ordinateur", "Clavier"));
            commandeRepository.save(commande2);

            // Commande 3
            Commande commande3 = new Commande();
            commande3.setAdresseLivraison("Rue Marrakech, Fès");
            commande3.setStatut("Livrée");
            commande3.setDateCommande(LocalDateTime.now().minusDays(2).toString());
            commande3.setLivreur(livreur3);
            commande3.setClient(client3);
            commande3.setProduits(List.of("Chaussures", "T-shirt"));
            commandeRepository.save(commande3);

            // Commande 4
            Commande commande4 = new Commande();
            commande4.setAdresseLivraison("Rue de la Liberté, Tanger");
            commande4.setStatut("En attente");
            commande4.setDateCommande(LocalDateTime.now().minusDays(3).toString());
            commande4.setLivreur(null);
            commande4.setClient(client1);
            commande4.setProduits(List.of("Casque Audio", "Chargeur"));
            commandeRepository.save(commande4);

            // Commande 5
            Commande commande5 = new Commande();
            commande5.setAdresseLivraison("Boulevard Zerktouni, Casablanca");
            commande5.setStatut("En cours");
            commande5.setDateCommande(LocalDateTime.now().minusHours(6).toString());
            commande5.setLivreur(livreur2);
            commande5.setClient(client2);
            commande5.setProduits(List.of("Sac à dos", "Bouteille d'eau"));
            commandeRepository.save(commande5);

            // Commande 6
            Commande commande6 = new Commande();
            commande6.setAdresseLivraison("Quartier Agdal, Rabat");
            commande6.setStatut("En attente");
            commande6.setDateCommande(LocalDateTime.now().minusDays(4).toString());
            commande6.setLivreur(null);
            commande6.setClient(client3);
            commande6.setProduits(List.of("Clé USB", "Disque Dur Externe"));
            commandeRepository.save(commande6);

            // Commande 7
            Commande commande7 = new Commande();
            commande7.setAdresseLivraison("Avenue des Fleurs, Marrakech");
            commande7.setStatut("Livrée");
            commande7.setDateCommande(LocalDateTime.now().minusDays(5).toString());
            commande7.setLivreur(livreur2);
            commande7.setClient(client1);
            commande7.setProduits(List.of("Bouquet de Fleurs", "Carte de Vœux"));
            commandeRepository.save(commande7);

            // Commande 8
            Commande commande8 = new Commande();
            commande8.setAdresseLivraison("Rue des Palais, Rabat");
            commande8.setStatut("En cours");
            commande8.setDateCommande(LocalDateTime.now().minusDays(6).toString());
            commande8.setLivreur(livreur3);
            commande8.setClient(client2);
            commande8.setProduits(List.of("Lampe de Bureau", "Agenda"));
            commandeRepository.save(commande8);

            // Commande 9
            Commande commande9 = new Commande();
            commande9.setAdresseLivraison("Avenue Mohammed VI, Agadir");
            commande9.setStatut("En attente");
            commande9.setDateCommande(LocalDateTime.now().minusDays(7).toString());
            commande9.setLivreur(null);
            commande9.setClient(client3);
            commande9.setProduits(List.of("Parfum", "Montre"));
            commandeRepository.save(commande9);

            // Commande 10
            Commande commande10 = new Commande();
            commande10.setAdresseLivraison("Place Jamaâ El Fna, Marrakech");
            commande10.setStatut("Livrée");
            commande10.setDateCommande(LocalDateTime.now().minusDays(8).toString());
            commande10.setLivreur(livreur10);
            commande10.setClient(client1);
            commande10.setProduits(List.of("Tajine", "Epices"));
            commandeRepository.save(commande10);

            // Notification 1
            Notification notification1 = new Notification();
            notification1.setMessage("Votre commande No. 3 est désormais livrée avec succès !");
            notification1.setDateEnvoi(LocalDateTime.now());
            notification1.setClient(client1);
            notificationRepository.save(notification1);

            // Notification 2
            Notification notification2 = new Notification();
            notification2.setMessage("Votre commande No. 2 est en cours de livraison.");
            notification2.setDateEnvoi(LocalDateTime.now().minusHours(2));
            notification2.setClient(client2);
            notificationRepository.save(notification2);

            // Notification 3
            Notification notification3 = new Notification();
            notification3.setMessage("Votre commande a été annulée");
            notification3.setDateEnvoi(LocalDateTime.now().minusDays(1));
            notification3.setClient(client3);
            notificationRepository.save(notification3);

            // Notification 4
            Notification notification4 = new Notification();
            notification4.setMessage("Votre commande No. 1 est en attente de livraison.");
            notification4.setDateEnvoi(LocalDateTime.now().minusHours(3));
            notification4.setClient(client1);
            notificationRepository.save(notification4);

            // Notification 5
            Notification notification5 = new Notification();
            notification5.setMessage("Votre commande No. 6 est en attente de livraison.");
            notification5.setDateEnvoi(LocalDateTime.now().minusHours(5));
            notification5.setClient(client2);
            notificationRepository.save(notification5);

            // Notification 6
            Notification notification6 = new Notification();
            notification6.setMessage("Votre commande No. 2 est en cours de livraison.");
            notification6.setDateEnvoi(LocalDateTime.now().minusDays(2));
            notification6.setClient(client3);
            notificationRepository.save(notification6);

            // Notification 7
            Notification notification7 = new Notification();
            notification7.setMessage("Votre commande No. 10 est en attente de livraison.");
            notification7.setDateEnvoi(LocalDateTime.now().minusDays(3));
            notification7.setClient(client1);
            notificationRepository.save(notification7);

            // Notification 8
            Notification notification8 = new Notification();
            notification8.setMessage("Votre commande No. 9 est désormais livrée avec succès !");
            notification8.setDateEnvoi(LocalDateTime.now().minusHours(7));
            notification8.setClient(client2);
            notificationRepository.save(notification8);

            // Notification 9
            Notification notification9 = new Notification();
            notification9.setMessage("Votre commande No. 6 est en attente de livraison.");
            notification9.setDateEnvoi(LocalDateTime.now().minusDays(4));
            notification9.setClient(client3);
            notificationRepository.save(notification9);

            // Notification 10
            Notification notification10 = new Notification();
            notification10.setMessage("Votre commande No. 7 est désormais livrée avec succès !");
            notification10.setDateEnvoi(LocalDateTime.now().minusDays(1).minusHours(2));
            notification10.setClient(client1);
            notificationRepository.save(notification10);

        }
    }
}