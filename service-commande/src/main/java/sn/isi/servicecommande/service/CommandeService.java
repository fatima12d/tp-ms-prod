
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

        log.info("Début de la création d'une commande pour le client ID : {}",
                request.getClientId());

        ClientDto client = clientFeignClient.getById(request.getClientId());
        log.debug("Client ID {} récupéré avec succès", request.getClientId());

        ProduitDto produit = produitFeignClient.getById(request.getProduitId());
        log.debug("Produit ID {} récupéré avec succès", request.getProduitId());

        if (client == null) {
            log.warn("Création impossible : client ID {} introuvable",
                    request.getClientId());
            throw new RuntimeException("Client introuvable");
        }

        if (produit == null) {
            log.warn("Création impossible : produit ID {} introuvable",
                    request.getProduitId());
            throw new RuntimeException("Produit introuvable");
        }

        if (!produit.isDisponible()) {
            log.warn("Création impossible : produit ID {} indisponible",
                    request.getProduitId());
            throw new RuntimeException("Produit indisponible");
        }

        if (produit.getQuantite() < request.getQuantiteCommande()) {
            log.warn("Stock insuffisant pour le produit ID {}. Stock : {}, quantité demandée : {}",
                    request.getProduitId(),
                    produit.getQuantite(),
                    request.getQuantiteCommande());
            throw new RuntimeException("Stock insuffisant");
        }

        Commande commande = Commande.builder()
                .numCommande("CMD-" + UUID.randomUUID()
                        .toString().substring(0, 8).toUpperCase())
                .clientId(request.getClientId())
                .produitId(request.getProduitId())
                .quantiteCommande(request.getQuantiteCommande())
                .montantTotal(produit.getPrix() * request.getQuantiteCommande())
                .dateCommande(LocalDate.now())
                .statut("EN_COURS")
                .build();

        Commande savedCommande = commandeRepository.save(commande);

        log.info("Commande créée avec succès. ID : {}, numéro : {}",
                savedCommande.getId(), savedCommande.getNumCommande());

        return CommandeMapper.toDTO(savedCommande);
    }

    @Override
    public CommandeDto getCommandeById(Integer id) {

        log.debug("Recherche de la commande ID : {}", id);

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Commande ID {} introuvable", id);
                    return new RuntimeException("Commande introuvable");
                });

        log.debug("Commande ID {} récupérée avec succès", id);

        return CommandeMapper.toDTO(commande);
    }

    @Override
    public CommandeDetailDto getCommandeDetails(Integer id) {

        log.info("Récupération des détails de la commande ID : {}", id);

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Commande ID {} introuvable", id);
                    return new RuntimeException("Commande introuvable");
                });

        log.debug("Récupération du client ID : {}", commande.getClientId());
        ClientDto client = clientFeignClient.getById(commande.getClientId());

        log.debug("Récupération du produit ID : {}", commande.getProduitId());
        ProduitDto produit = produitFeignClient.getById(commande.getProduitId());

        CommandeDetailDto details = CommandeDetailDto.builder()
                .id(commande.getId())
                .numCommande(commande.getNumCommande())
                .quantiteCommande(commande.getQuantiteCommande())
                .montantTotal(commande.getMontantTotal())
                .dateCommande(commande.getDateCommande())
                .statut(commande.getStatut())
                .clientDto(client)
                .produitDto(produit)
                .build();

        log.info("Détails de la commande ID {} récupérés avec succès", id);

        return details;
    }

    @Override
    public List<CommandeDto> getAllCommandes() {

        log.debug("Récupération de la liste des commandes");

        List<CommandeDto> commandes = commandeRepository.findAll()
                .stream()
                .map(CommandeMapper::toDTO)
                .toList();

        log.info("Nombre de commandes récupérées : {}", commandes.size());

        return commandes;
    }

    @Override
    public void deleteCommande(Integer id) {

        log.info("Début de la suppression de la commande ID : {}", id);

        getCommandeById(id);

        commandeRepository.deleteById(id);

        log.info("Commande ID {} supprimée avec succès", id);
    }
}
