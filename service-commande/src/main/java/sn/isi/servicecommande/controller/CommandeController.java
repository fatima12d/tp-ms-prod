package sn.isi.servicecommande.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class CommandeController {
    private final CommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeDto> create(@Valid @RequestBody CommandeRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commandeService.createcommande(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<CommandeDetailDto> getDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(commandeService.getCommandeDetails(id));
    }

    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAll() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
