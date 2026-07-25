package sn.isi.servicecommande.mapper;

import sn.isi.servicecommande.dto.CommandeDto;
import sn.isi.servicecommande.entity.Commande;

public class CommandeMapper {
    public static CommandeDto toDTO(Commande c) {
        return CommandeDto.builder()
                .id(c.getId())
                .numCommande(c.getNumCommande())
                .clientId(c.getClientId())
                .produitId(c.getProduitId())
                .quantiteCommande(c.getQuantiteCommande())
                .montantTotal(c.getMontantTotal())
                .dateCommande(c.getDateCommande())
                .statut(c.getStatut())
                .build();
    }

    public static Commande toEntity(CommandeDto d) {
        return Commande.builder()
                .id(d.getId())
                .numCommande(d.getNumCommande())
                .clientId(d.getClientId())
                .produitId(d.getProduitId())
                .quantiteCommande(d.getQuantiteCommande())
                .montantTotal(d.getMontantTotal())
                .dateCommande(d.getDateCommande())
                .statut(d.getStatut())
                .build();
    }
}
