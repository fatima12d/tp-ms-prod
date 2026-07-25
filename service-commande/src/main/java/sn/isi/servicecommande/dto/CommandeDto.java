package sn.isi.servicecommande.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommandeDto {
    private Integer id;
    private String numCommande;
    private Integer clientId;
    private Integer produitId;
    private Integer quantiteCommande;
    private double montantTotal;
    private LocalDate dateCommande;
    private String statut;

}
