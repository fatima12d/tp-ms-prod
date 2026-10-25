package sn.isi.serviceclient.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.serviceclient.dto.ClientDto;
import sn.isi.serviceclient.service.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto clientDto) {
        log.info("Demande de création d'un client: {}", clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Integer id, @Valid @RequestBody ClientDto clientDto) {
        log.info("Demande de modification d'un client: {}, data : {}", id, clientDto);
        return ResponseEntity.ok(clientService.updateClient(clientDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        log.info("Demande de suppression d'un client: {}", id);
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Integer id) {
        log.info("Demande de recuperation d'un client: {}", id);
        return ResponseEntity.ok(clientService.findClientById(id));
    }
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAll() {
        log.info("Demande la liste des clients");
        return ResponseEntity.ok(clientService.findAllClients());
    }
}
