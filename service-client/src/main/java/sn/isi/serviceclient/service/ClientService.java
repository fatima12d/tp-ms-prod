//package sn.isi.serviceclient.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import sn.isi.serviceclient.dto.ClientDto;
//import sn.isi.serviceclient.entity.Client;
//import sn.isi.serviceclient.mapper.ClientMapper;
//import sn.isi.serviceclient.repository.ClientRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ClientService implements IClient {
//
//   private final ClientRepository clientRepository;
//
//    @Override
//    public ClientDto createClient(ClientDto clientDto) {
//        log.info("Tentative de création d'un nouveau client");
//
//        Client client = ClientMapper.toEntity(clientDto);
//        Client savedClient = clientRepository.save(client);
//
//        log.info("Client créé avec succès. ID généré : {}", savedClient.getId());
//        return ClientMapper.toDTO(savedClient);
//    }
//
//    @Override
//    public ClientDto updateClient(ClientDto clientDto, Integer id) {
//        log.info("Tentative de mise à jour du client ID : {}", id);
//
//        if(findClientById(id)!=null){
//           clientDto.setId(id);
//        }
//        return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntity(clientDto)));
//    }
//
//    @Override
//    public ClientDto findClientById(Integer id) {
//        log.info("findClientById");
//        Optional<Client> client = Optional.of(clientRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("id n'existe pas")
//        ));
//        return ClientMapper.toDTO(client.get());
//    }
//
//    @Override
//    public void deleteClient(Integer id) {
//        log.info("deleteClient avec id {}", id);
//        findClientById(id);
//        clientRepository.deleteById(id);
//    }
//
//    @Override
//    public List<ClientDto> findAllClients() {
//        log.info("findAllClients");
//        return clientRepository.findAll().stream().map(ClientMapper::toDTO).toList();
//    }
//}

package sn.isi.serviceclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.serviceclient.dto.ClientDto;
import sn.isi.serviceclient.entity.Client;
import sn.isi.serviceclient.mapper.ClientMapper;
import sn.isi.serviceclient.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements IClient {

    private final ClientRepository clientRepository;

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        log.info("Début de la création d'un nouveau client");

        Client client = ClientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);

        log.info("Client créé avec succès. ID : {}", savedClient.getId());

        return ClientMapper.toDTO(savedClient);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Integer id) {
        log.info("Début de la mise à jour du client ID : {}", id);

        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Client ID : {} introuvable", id);
                    return new RuntimeException(
                            "Le client avec l'ID " + id + " n'existe pas");
                });

        clientDto.setId(id);

        Client updatedClient = clientRepository.save(
                ClientMapper.toEntity(clientDto));

        log.info("Client ID : {} mis à jour avec succès", id);

        return ClientMapper.toDTO(updatedClient);
    }

    @Override
    public ClientDto findClientById(Integer id) {
        log.debug("Recherche du client ID : {}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Client ID : {} introuvable", id);
                    return new RuntimeException(
                            "Le client avec l'ID " + id + " n'existe pas");
                });

        log.debug("Client ID : {} trouvé avec succès", id);

        return ClientMapper.toDTO(client);
    }

    @Override
    public void deleteClient(Integer id) {
        log.info("Début de la suppression du client ID : {}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Impossible de supprimer : client ID {} introuvable", id);
                    return new RuntimeException(
                            "Le client avec l'ID " + id + " n'existe pas");
                });

        clientRepository.delete(client);

        log.info("Client ID : {} supprimé avec succès", id);
    }

    @Override
    public List<ClientDto> findAllClients() {
        log.debug("Récupération de la liste des clients");

        List<Client> clients = clientRepository.findAll();

        log.info("Nombre de clients récupérés : {}", clients.size());

        return clients.stream()
                .map(ClientMapper::toDTO)
                .toList();
    }
}
