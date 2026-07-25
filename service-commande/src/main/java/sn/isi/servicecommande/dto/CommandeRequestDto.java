package sn.isi.servicecommande.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommandeRequestDto {
    @NotNull(message = "Le clientID est obligatoire")
    private Integer clientId;

    @NotNull(message = "Le produitID est obligatoire")
    private Integer produitId;
    @NotNull(message = "La quantite est obligatoire")
    private Integer quantiteCommande;
}
