package sn.isi.serviceproduit.service;

import sn.isi.serviceproduit.dto.ProduitDto;

import java.util.List;

public interface IProduit {
    ProduitDto createProduit(ProduitDto produitDto);
    ProduitDto updateProduit(ProduitDto produitDto, Integer id);
    ProduitDto findById(Integer id);
    void deleteProduit(Integer id);
    List<ProduitDto> findAllProduits();
    List<ProduitDto> getAvailable();
    List<ProduitDto> getByCategorie(String categorie);




}
