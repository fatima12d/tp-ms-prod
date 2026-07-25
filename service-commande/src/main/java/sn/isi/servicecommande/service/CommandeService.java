package sn.isi.servicecommande.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.servicecommande.client.Client;
import sn.isi.servicecommande.client.ProduitClient;
import sn.isi.servicecommande.dto.*;
import sn.isi.servicecommande.entity.Commande;
import sn.isi.servicecommande.mapper.CommandeMapper;
import sn.isi.servicecommande.repository.CommandeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeService implements ICommande {

    private final CommandeRepository commandeRepository;
    private final Client clientFeignClient;
    private final ProduitClient produitFeignClient;

    @Override
    public CommandeDto createcommande(CommandeRequestDto request) {

        log.info("Création d'une commande");

        ClientDto client = clientFeignClient.getById(request.getClientId());

        ProduitDto produit = produitFeignClient.getById(request.getProduitId());

        if (client == null) {
            throw new RuntimeException("Client introuvable");
        }

        if (produit == null) {
            throw new RuntimeException("Produit introuvable");
        }

        if (!produit.isDisponible()) {
            throw new RuntimeException("Produit indisponible");
        }

        if (produit.getQuantite() < request.getQuantiteCommande()) {
            throw new RuntimeException("Stock insuffisant");
        }

        Commande commande = Commande.builder()
                .numCommande("CMD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .clientId(request.getClientId())
                .produitId(request.getProduitId())
                .quantiteCommande(request.getQuantiteCommande())
                .montantTotal(produit.getPrix() * request.getQuantiteCommande())
                .dateCommande(LocalDate.now())
                .statut("EN_COURS")
                .build();

        return CommandeMapper.toDTO(commandeRepository.save(commande));
    }

    @Override
    public CommandeDto getCommandeById(Integer id) {

        Optional<Commande> commande = Optional.of(
                commandeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Commande introuvable"))
        );

        return CommandeMapper.toDTO(commande.get());
    }

    @Override
    public CommandeDetailDto getCommandeDetails(Integer id) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        ClientDto client = clientFeignClient.getById(commande.getClientId());

        ProduitDto produit = produitFeignClient.getById(commande.getProduitId());

        return CommandeDetailDto.builder()
                .id(commande.getId())
                .numCommande(commande.getNumCommande())
                .quantiteCommande(commande.getQuantiteCommande())
                .montantTotal(commande.getMontantTotal())
                .dateCommande(commande.getDateCommande())
                .statut(commande.getStatut())
                .clientDto(client)
                .produitDto(produit)
                .build();
    }

    @Override
    public List<CommandeDto> getAllCommandes() {

        return commandeRepository.findAll()
                .stream()
                .map(CommandeMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteCommande(Integer id) {

        getCommandeById(id);

        commandeRepository.deleteById(id);
    }


}