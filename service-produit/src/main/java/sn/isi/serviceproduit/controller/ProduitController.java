
package sn.isi.serviceproduit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.serviceproduit.dto.ProduitDto;
import sn.isi.serviceproduit.service.ProduitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produits")
@Slf4j
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitDto> create(
            @Valid @RequestBody ProduitDto produitDto) {

        log.info("Requête reçue : création d'un nouveau produit");

        ProduitDto produit = produitService.createProduit(produitDto);

        log.info("Réponse : produit créé avec succès, ID : {}", produit.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDto> update(
            @PathVariable Integer id,
            @Valid @RequestBody ProduitDto produitDto) {

        log.info("Requête reçue : modification du produit ID : {}", id);

        ProduitDto produit = produitService.updateProduit(produitDto, id);

        log.info("Réponse : produit ID {} modifié avec succès", id);

        return ResponseEntity.ok(produit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        log.info("Requête reçue : suppression du produit ID : {}", id);

        produitService.deleteProduit(id);

        log.info("Réponse : produit ID {} supprimé avec succès", id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDto> getById(@PathVariable Integer id) {

        log.debug("Requête reçue : recherche du produit ID : {}", id);

        ProduitDto produit = produitService.findById(id);

        log.debug("Réponse : produit ID {} récupéré avec succès", id);

        return ResponseEntity.ok(produit);
    }

    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAll() {

        log.debug("Requête reçue : récupération de tous les produits");

        List<ProduitDto> produits = produitService.findAllProduits();

        log.info("Réponse : {} produit(s) récupéré(s)", produits.size());

        return ResponseEntity.ok(produits);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ProduitDto>> getAvailable() {

        log.debug("Requête reçue : récupération des produits disponibles");

        List<ProduitDto> produits = produitService.getAvailable();

        log.info("Réponse : {} produit(s) disponible(s)", produits.size());

        return ResponseEntity.ok(produits);
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<ProduitDto>> getByCategorie(
            @PathVariable String categorie) {

        log.debug("Requête reçue : recherche des produits de la catégorie '{}'",
                categorie);

        List<ProduitDto> produits = produitService.getByCategorie(categorie);

        log.info("Réponse : {} produit(s) trouvé(s) pour la catégorie '{}'",
                produits.size(), categorie);

        return ResponseEntity.ok(produits);
    }
}
