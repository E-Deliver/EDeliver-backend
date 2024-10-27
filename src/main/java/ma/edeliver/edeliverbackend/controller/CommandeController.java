package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.service.CommandeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    /*@PutMapping("/{commandeId}/assigner/{livreurId}")
    public ResponseEntity<String> assignerLivreur(@PathVariable Long commandeId, @PathVariable Long livreurId) {
        try {
            commandeService.assignerCommandeALivreur(commandeId, livreurId);
            return ResponseEntity.ok("Commande assignée avec succès au livreur");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }*/

    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<Commande>> getCommandesPourLivreur(@PathVariable Long livreurId) {
        List<Commande> commandes = commandeService.getCommandesByLivreurId(livreurId);
        return ResponseEntity.ok(commandes);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{commandeId}/assigner/{livreurId}", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    public ResponseEntity<String> assignerLivreur(@PathVariable Long commandeId, @PathVariable Long livreurId) {
        try {
            commandeService.assignerCommandeALivreur(commandeId, livreurId);
            return ResponseEntity.ok("Commande assignée avec succès au livreur");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateCommandeStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("statut");
        commandeService.updateStatus(id, newStatus);
        return ResponseEntity.ok("Statut mis à jour avec succès");
    }

    // Dans CommandeController
    @GetMapping("/client/{clientId}/status/{statut}")
    public ResponseEntity<List<Commande>> getCommandesByClientIdAndStatus(
            @PathVariable Long clientId, 
            @PathVariable String statut) {
        List<Commande> commandes = commandeService.getCommandesByClientIdAndStatus(clientId, statut);
        return ResponseEntity.ok(commandes);
    }

}
