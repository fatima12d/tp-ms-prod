package sn.isi.serviceproduit.service;

import lombok.RequiredArgsConstructor;
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
public class ProduitService implements IProduit {
    private final ProduitRepository produitRepository;

    @Override
    public ProduitDto createProduit(ProduitDto produitDto) {

        Produit produit = ProduitMapper.toEntity(produitDto);
        produit.setCodeProduit(UUID.randomUUID().toString());
        produit.setDateAjout(LocalDate.now());
        produit.setDisponible(true);
        Produit saved = produitRepository.save(produit);
        return ProduitMapper.toDTO(saved);
    }

    @Override
    public ProduitDto updateProduit(ProduitDto produitDto, Integer id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        produit.setLibelle(produitDto.getLibelle());
        produit.setDescription(produitDto.getDescription());
        produit.setCategorie(produitDto.getCategorie());
        produit.setPrix(produitDto.getPrix());
        produit.setQuantite(produitDto.getQuantite());

        Produit saved = produitRepository.save(produit);

        return ProduitMapper.toDTO(saved);
    }

    @Override
    public ProduitDto findById(Integer id) {

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        return ProduitMapper.toDTO(produit);
    }

    @Override
    public void deleteProduit(Integer id) {
        produitRepository.deleteById(id);
    }

    @Override
    public List<ProduitDto> findAllProduits() {

        return produitRepository.findAll()
                .stream()
                .map(ProduitMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProduitDto> getAvailable() {
        return produitRepository.findByDisponibleTrue()
                .stream()
                .map(ProduitMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProduitDto> getByCategorie(String categorie) {
        return produitRepository.findByCategorieContainingIgnoreCase(categorie)
                .stream()
                .map(ProduitMapper::toDTO)
                .toList();    }
}
