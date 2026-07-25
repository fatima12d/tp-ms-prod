package sn.isi.servicecommande.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class CommandeDetailDto {
    private Integer id;
    private String numCommande;
    private ClientDto clientDto;
    private ProduitDto produitDto;
    private Integer quantiteCommande;
    private double montantTotal;
    private LocalDate dateCommande;
    private String statut;
}
