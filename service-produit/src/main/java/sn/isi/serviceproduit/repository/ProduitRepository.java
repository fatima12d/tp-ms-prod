package sn.isi.serviceproduit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.serviceproduit.entity.Produit;

import java.util.List;

@Repository
public interface ProduitRepository  extends JpaRepository<Produit,Integer> {
    List<Produit> findByDisponibleTrue();
    List<Produit> findByCategorieContainingIgnoreCase(String categorie);

}
