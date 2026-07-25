package sn.isi.serviceclient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ClientDto {
    private Integer id;
    private String nom;
    private String prenom;
    @Email(message = "il faut respecter le format d'email valide")
    private String email;
    private String adresse;
    private String telephone;
}
