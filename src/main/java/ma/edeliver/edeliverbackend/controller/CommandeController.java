package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    // Méthode pour affecter un livreur à une commande
    @PutMapping("/{commandeId}/assigner/{livreurId}")
    public ResponseEntity<String> assignerLivreur(@PathVariable Long commandeId, @PathVariable Long livreurId) {
        try {
            commandeService.assignerCommandeALivreur(commandeId, livreurId);
            return ResponseEntity.ok("Commande assignée avec succès au livreur");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<Commande>> getCommandesPourLivreur(@PathVariable Long livreurId) {
        List<Commande> commandes = commandeService.getCommandesByLivreurId(livreurId);
        return ResponseEntity.ok(commandes);
    }
}
