package sn.isi.serviceproduit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String libelle;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String categorie;

    @Column(nullable = false)
    private String codeProduit;

    @Column(nullable = false)
    private double prix;

    @Column(nullable = false)
    private int quantite;

    private LocalDate dateAjout;
    private boolean disponible;


}
