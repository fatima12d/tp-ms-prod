package sn.isi.serviceproduit.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.serviceproduit.dto.ProduitDto;
import sn.isi.serviceproduit.service.ProduitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping
    public ResponseEntity<ProduitDto> create(@Valid @RequestBody ProduitDto produitDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produitService.createProduit(produitDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDto> update(@PathVariable Integer id,
                                             @Valid @RequestBody ProduitDto produitDto) {
        return ResponseEntity.ok(produitService.updateProduit(produitDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(produitService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProduitDto>> getAll() {
        return ResponseEntity.ok(produitService.findAllProduits());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ProduitDto>> getAvailable() {
        return ResponseEntity.ok(produitService.getAvailable());
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<ProduitDto>> getByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(produitService.getByCategorie(categorie));
    }
}


