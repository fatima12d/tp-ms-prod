package sn.isi.servicecommande.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
}
