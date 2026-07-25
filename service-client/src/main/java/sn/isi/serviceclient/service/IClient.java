package sn.isi.serviceclient.service;

import sn.isi.serviceclient.dto.ClientDto;

import java.util.List;

public interface IClient {
    ClientDto createClient(ClientDto clientDto);
    ClientDto updateClient(ClientDto clientDto, Integer id);
    ClientDto findClientById(Integer id);
    void deleteClient(Integer id);
    List<ClientDto> findAllClients();

}
