
package sn.isi.servicecommande.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.servicecommande.dto.CommandeDetailDto;
import sn.isi.servicecommande.dto.CommandeDto;
import sn.isi.servicecommande.dto.CommandeRequestDto;
import sn.isi.servicecommande.service.CommandeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commandes")
@Slf4j
public class CommandeController {

    private final CommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeDto> create(
            @Valid @RequestBody CommandeRequestDto request) {

        log.info("Requête reçue : création d'une nouvelle commande");

        CommandeDto commande = commandeService.createcommande(request);

        log.info("Réponse : commande créée avec succès, ID : {}",
                commande.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(commande);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getById(@PathVariable Integer id) {

        log.debug("Requête reçue : recherche de la commande ID : {}", id);

        CommandeDto commande = commandeService.getCommandeById(id);

        log.debug("Réponse : commande ID {} récupérée avec succès", id);

        return ResponseEntity.ok(commande);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<CommandeDetailDto> getDetails(
            @PathVariable Integer id) {

        log.info("Requête reçue : récupération des détails de la commande ID : {}", id);

        CommandeDetailDto details = commandeService.getCommandeDetails(id);

        log.info("Réponse : détails de la commande ID {} récupérés avec succès", id);

        return ResponseEntity.ok(details);
    }

    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAll() {

        log.debug("Requête reçue : récupération de la liste des commandes");

        List<CommandeDto> commandes = commandeService.getAllCommandes();

        log.info("Réponse : {} commande(s) récupérée(s)", commandes.size());

        return ResponseEntity.ok(commandes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        log.info("Requête reçue : suppression de la commande ID : {}", id);

        commandeService.deleteCommande(id);

        log.info("Réponse : commande ID {} supprimée avec succès", id);

        return ResponseEntity.noContent().build();
    }
}
