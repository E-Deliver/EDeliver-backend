package ma.edeliver.edeliverbackend.controller;

import ma.edeliver.edeliverbackend.entity.Commande;
import ma.edeliver.edeliverbackend.service.CommandeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommandeControllerTest {

    @InjectMocks
    private CommandeController commandeController;

    @Mock
    private CommandeService commandeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commandeController).build();
    }

    @Test
    public void testGetAllCommandes() throws Exception {
        Commande commande1 = new Commande(); 
        commande1.setIdCommande(1L);
        commande1.setStatut("Commande 1");
        
        Commande commande2 = new Commande();
        commande2.setIdCommande(2L);
        commande2.setStatut("Commande 2");

        List<Commande> commandes = Arrays.asList(commande1, commande2);

        when(commandeService.getAllCommandes()).thenReturn(commandes);

        mockMvc.perform(get("/api/commandes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].statut").value("Commande 1"))
                .andExpect(jsonPath("$[1].statut").value("Commande 2"));
    }

    @Test
    public void testGetCommandeByIdSuccess() throws Exception {
        Long commandeId = 1L;
        Commande commande = new Commande(); 
        commande.setIdCommande(commandeId);
        commande.setStatut("Commande 1");

        when(commandeService.getCommandeById(commandeId)).thenReturn(commande);

        mockMvc.perform(get("/api/commandes/{id}", commandeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statut").value("Commande 1"));
    }

    @Test
    public void testGetCommandeByIdNotFound() throws Exception {
        Long commandeId = 1L;

        when(commandeService.getCommandeById(commandeId)).thenReturn(null);

        mockMvc.perform(get("/api/commandes/{id}", commandeId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCommandesPourLivreur() throws Exception {
        Long livreurId = 1L;
        Commande commande1 = new Commande(); 
        commande1.setIdCommande(1L);
        commande1.setStatut("Commande 1");
        
        Commande commande2 = new Commande();
        commande2.setIdCommande(2L);
        commande2.setStatut("Commande 2");

        List<Commande> commandes = Arrays.asList(commande1, commande2);

        when(commandeService.getCommandesByLivreurId(livreurId)).thenReturn(commandes);

        mockMvc.perform(get("/api/commandes/livreur/{livreurId}", livreurId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].statut").value("Commande 1"))
                .andExpect(jsonPath("$[1].statut").value("Commande 2"));
    }

    @Test
    public void testAssignerLivreurSuccess() throws Exception {
        Long commandeId = 1L;
        Long livreurId = 2L;

        commandeService.assignerCommandeALivreur(commandeId, livreurId);

        mockMvc.perform(put("/api/commandes/{commandeId}/assigner/{livreurId}", commandeId, livreurId))
                .andExpect(status().isOk())
                .andExpect(content().string("Commande assignée avec succès au livreur"));
    }

    @Test
    public void testAssignerLivreurBadRequest() throws Exception {
        Long commandeId = 1L;
        Long livreurId = 2L;

        when(commandeService.assignerCommandeALivreur(commandeId, livreurId)).thenThrow(new IllegalArgumentException("Erreur lors de l'assignation"));

        mockMvc.perform(put("/api/commandes/{commandeId}/assigner/{livreurId}", commandeId, livreurId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erreur lors de l'assignation"));
    }

    @Test
    public void testUpdateCommandeStatusSuccess() throws Exception {
        Long commandeId = 1L;
        Map<String, String> statusUpdate = new HashMap<>();
        statusUpdate.put("statut", "En cours");

        doNothing().when(commandeService).updateStatus(commandeId, "En cours");

        mockMvc.perform(put("/api/commandes/{id}/status", commandeId)
                .contentType("application/json")
                .content("{\"statut\":\"En cours\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Statut mis à jour avec succès"));
    }

    @Test
    public void testGetCommandesByClientIdAndStatus() throws Exception {
        Long clientId = 1L;
        String statut = "Livrée";
        Commande commande1 = new Commande();
        commande1.setIdCommande(1L);
        commande1.setStatut("Commande 1");
        
        Commande commande2 = new Commande();
        commande2.setIdCommande(2L);
        commande2.setStatut("Commande 2");

        List<Commande> commandes = Arrays.asList(commande1, commande2);

        when(commandeService.getCommandesByClientIdAndStatus(clientId, statut)).thenReturn(commandes);

        mockMvc.perform(get("/api/commandes/client/{clientId}/status/{statut}", clientId, statut))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].statut").value("Commande 1"))
                .andExpect(jsonPath("$[1].statut").value("Commande 2"));
    }

    @Test
    public void testSearchCommandesWithNoParams() throws Exception {
        when(commandeService.searchCommandes(null, null, null)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/commandes/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testSearchCommandesWithInvalidParams() throws Exception {
        when(commandeService.searchCommandes("invalid address", null, null)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/commandes/search?address=invalid address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

}
