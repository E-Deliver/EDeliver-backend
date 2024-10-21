

-- Insert test data for Utilisateur (Client)
INSERT INTO utilisateur (nom, email, mot_de_passe, role)
VALUES ('John Doe', 'john.doe@example.com', 'password123', 'CLIENT');

-- Now insert into Client, assuming it will reference the last inserted utilisateur's ID
INSERT INTO client (adresse, telephone, id)
VALUES ('123 Main St', '555-1234',  LAST_INSERT_ID());

-- Insert test data for Utilisateur (Livreur)
INSERT INTO utilisateur (nom, email, mot_de_passe, role)
VALUES ('Jane Smith', 'jane.smith@example.com', 'password456', 'LIVREUR');

-- Now insert into Livreur
INSERT INTO livreur (localisation, etat, id)
VALUES ('City Center', 'DISPONIBLE', LAST_INSERT_ID());

-- Insert test data for Commande
INSERT INTO commande (adresse_livraison, statut, date_commande, livreur_id, client_id)
VALUES ('456 Elm St', 'EN_COURS', '2024-10-21', (SELECT id FROM livreur ORDER BY id DESC LIMIT 1), (SELECT id FROM client ORDER BY id DESC LIMIT 1));

-- Insert products for the Commande
INSERT INTO commande_produits (id_commande, produit)
VALUES ((SELECT id_commande FROM commande ORDER BY id_commande DESC LIMIT 1), 'Produit A'),
       ((SELECT id_commande FROM commande ORDER BY id_commande DESC LIMIT 1), 'Produit B');

-- Insert test data for Notification
INSERT INTO notification (message, date_envoi, id_client)
VALUES ('Votre commande est en cours', '2024-10-21 10:00:00', (SELECT id FROM client ORDER BY id DESC LIMIT 1));
