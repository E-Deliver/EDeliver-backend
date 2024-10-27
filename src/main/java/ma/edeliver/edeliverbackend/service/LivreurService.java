package ma.edeliver.edeliverbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.edeliver.edeliverbackend.entity.Livreur;
import ma.edeliver.edeliverbackend.repository.LivreurRepository;

@Service
public class LivreurService {

    @Autowired
    private LivreurRepository livreurRepository;

    // Retrieve all available livreurs
    public List<Livreur> findLivreursByEtat(Livreur.EtatLivreur etat) {
        return livreurRepository.findByEtat(etat);
    }
  public List<Livreur> findAllLivreurs() {
    return livreurRepository.findAll();
  }
}
