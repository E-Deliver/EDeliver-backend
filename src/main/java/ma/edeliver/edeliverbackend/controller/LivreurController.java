package ma.edeliver.edeliverbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.service.LivreurService;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    @Autowired
    private LivreurService livreurService;

    @GetMapping("/disponibles")
    public List<Livreur> getLivreursDisponibles() {
        return livreurService.findLivreursByEtat(Livreur.EtatLivreur.DISPONIBLE);
    }
  @GetMapping
  public List<Livreur> getAllLivreurs() {
    return livreurService.findAllLivreurs();
  }
}
