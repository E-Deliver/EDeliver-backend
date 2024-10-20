package ma.edeliver.edeliverbackend.dto;

import lombok.Data;

@Data
public class LivreurDTO {

    // private Long idLivreur;
    private String localisation;
    private EtatLivreur etat;

    public enum EtatLivreur {
        DISPONIBLE, EN_LIVRAISON, INACTIF
    }
}
