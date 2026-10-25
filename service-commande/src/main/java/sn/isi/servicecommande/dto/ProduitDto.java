package sn.isi.servicecommande.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ProduitDto {

    private Integer id;
    private String libelle;
    private String description;
    private String categorie;
    private Double prix;
    private Integer quantite;
    private boolean disponible;
}
