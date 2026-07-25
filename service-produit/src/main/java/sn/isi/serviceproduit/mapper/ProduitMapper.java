package sn.isi.serviceproduit.mapper;

import sn.isi.serviceproduit.dto.ProduitDto;
import sn.isi.serviceproduit.entity.Produit;

public class ProduitMapper {
    public static ProduitDto toDTO(Produit p) {
        return ProduitDto.builder()
                .id(p.getId())
                .libelle(p.getLibelle())
                .description(p.getDescription())
                .categorie(p.getCategorie())
                .codeProduit(p.getCodeProduit())
                .prix(p.getPrix())
                .quantite(p.getQuantite())
                .dateAjout(p.getDateAjout())
                .disponible(p.isDisponible())
                .build();
    }

    public static Produit toEntity(ProduitDto d) {
        return Produit.builder()
                .id(d.getId())
                .libelle(d.getLibelle())
                .description(d.getDescription())
                .categorie(d.getCategorie())
                .codeProduit(d.getCodeProduit())
                .prix(d.getPrix())
                .quantite(d.getQuantite())
                .dateAjout(d.getDateAjout())
                .disponible(d.isDisponible())
                .build();
    }
}
