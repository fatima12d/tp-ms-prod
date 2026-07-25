package sn.isi.servicecommande.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numCommande;

    private Integer clientId;

    private Integer produitId;

    private Integer quantiteCommande;

    private double montantTotal;

    private LocalDate dateCommande;

    private String statut;
}
