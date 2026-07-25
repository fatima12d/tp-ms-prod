package sn.isi.serviceclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.isi.serviceclient.dto.ClientDto;
import sn.isi.serviceclient.entity.Client;
import sn.isi.serviceclient.mapper.ClientMapper;
import sn.isi.serviceclient.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements IClient {

   private final ClientRepository clientRepository;

    @Override
    public ClientDto createClient(ClientDto clientDto) {
       log.info("createClient");

        return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntity(clientDto)));
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Integer id) {
        log.info("updateClient");
        if(findClientById(id)!=null){
           clientDto.setId(id);
        }
        return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntity(clientDto)));
    }

    @Override
    public ClientDto findClientById(Integer id) {
        Optional<Client> client = Optional.of(clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("id n'existe pas")
        ));
        return ClientMapper.toDTO(client.get());
    }

    @Override
    public void deleteClient(Integer id) {
        findClientById(id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDto> findAllClients() {
        return clientRepository.findAll().stream().map(ClientMapper::toDTO).toList();
    }
}
