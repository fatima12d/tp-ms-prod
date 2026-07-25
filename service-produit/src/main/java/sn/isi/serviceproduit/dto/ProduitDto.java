package sn.isi.serviceproduit.dto;

import jakarta.validation.constraints.Min;
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
    @NotBlank(message = "Le libellé est obligatoire")
    private String libelle;

    private String description;

    @NotBlank(message = "La catégorie est obligatoire")
    private String categorie;

    private String codeProduit;

    @NotNull(message = "Le prix est obligatoire")
    private Double prix;

    @NotNull(message = "La quantité est obligatoire")
    private Integer quantite;

    private LocalDate dateAjout;

    private boolean disponible;
}
