package sn.isi.servicecommande.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sn.isi.servicecommande.dto.ProduitDto;

@FeignClient(
        name = "service-produit",
        path = "/api/produits"
)
public interface ProduitClient {
    @GetMapping("{id}")
    ProduitDto getById(@PathVariable("id") Integer id);

}
