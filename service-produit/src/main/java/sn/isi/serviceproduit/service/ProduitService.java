
package sn.isi.serviceproduit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.serviceproduit.dto.ProduitDto;
import sn.isi.serviceproduit.entity.Produit;
import sn.isi.serviceproduit.mapper.ProduitMapper;
import sn.isi.serviceproduit.repository.ProduitRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProduitService implements IProduit {

    private final ProduitRepository produitRepository;

    @Override
    public ProduitDto createProduit(ProduitDto produitDto) {
        log.info("Début de la création d'un nouveau produit");

        Produit produit = ProduitMapper.toEntity(produitDto);
        produit.setCodeProduit(UUID.randomUUID().toString());
        produit.setDateAjout(LocalDate.now());
        produit.setDisponible(true);

        Produit saved = produitRepository.save(produit);

        log.info("Produit créé avec succès. ID : {}, code : {}",
                saved.getId(), saved.getCodeProduit());

        return ProduitMapper.toDTO(saved);
    }

    @Override
    public ProduitDto updateProduit(ProduitDto produitDto, Integer id) {
        log.info("Début de la mise à jour du produit ID : {}", id);

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Mise à jour impossible : produit ID {} introuvable", id);
                    return new RuntimeException("Produit introuvable");
                });

        produit.setLibelle(produitDto.getLibelle());
        produit.setDescription(produitDto.getDescription());
        produit.setCategorie(produitDto.getCategorie());
        produit.setPrix(produitDto.getPrix());
        produit.setQuantite(produitDto.getQuantite());

        Produit saved = produitRepository.save(produit);

        log.info("Produit ID {} mis à jour avec succès", id);

        return ProduitMapper.toDTO(saved);
    }

    @Override
    public ProduitDto findById(Integer id) {
        log.debug("Recherche du produit ID : {}", id);

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Produit ID {} introuvable", id);
                    return new RuntimeException("Produit introuvable");
                });

        log.debug("Produit ID {} récupéré avec succès", id);

        return ProduitMapper.toDTO(produit);
    }

    @Override
    public void deleteProduit(Integer id) {
        log.info("Début de la suppression du produit ID : {}", id);

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Suppression impossible : produit ID {} introuvable", id);
                    return new RuntimeException("Produit introuvable");
                });

        produitRepository.delete(produit);

        log.info("Produit ID {} supprimé avec succès", id);
    }

    @Override
    public List<ProduitDto> findAllProduits() {
        log.debug("Récupération de la liste de tous les produits");

        List<ProduitDto> produits = produitRepository.findAll()
                .stream()
                .map(ProduitMapper::toDTO)
                .toList();

        log.info("Nombre de produits récupérés : {}", produits.size());

        return produits;
    }

    @Override
    public List<ProduitDto> getAvailable() {
        log.debug("Recherche des produits disponibles");

        List<ProduitDto> produits = produitRepository.findByDisponibleTrue()
                .stream()
                .map(ProduitMapper::toDTO)
                .toList();

        log.info("Nombre de produits disponibles : {}", produits.size());

        return produits;
    }

    @Override
    public List<ProduitDto> getByCategorie(String categorie) {
        log.debug("Recherche des produits pour la catégorie : {}", categorie);

        List<ProduitDto> produits = produitRepository
                .findByCategorieContainingIgnoreCase(categorie)
                .stream()
                .map(ProduitMapper::toDTO)
                .toList();

        log.info("Nombre de produits trouvés pour la catégorie '{}' : {}",
                categorie, produits.size());

        return produits;
    }
}
