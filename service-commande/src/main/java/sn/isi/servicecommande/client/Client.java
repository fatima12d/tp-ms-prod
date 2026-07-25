package sn.isi.servicecommande.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sn.isi.servicecommande.dto.ClientDto;

@FeignClient(
        name = "service-client",
        path = "/api/clients"

)
public interface Client {
    @GetMapping("/{id}")
    ClientDto getById(@PathVariable("id") Integer id);

    @PutMapping("/{id}")
    ClientDto update(@PathVariable("id") Integer id, @RequestBody ClientDto clientDto);
}
