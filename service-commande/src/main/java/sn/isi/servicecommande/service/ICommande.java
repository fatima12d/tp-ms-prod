package sn.isi.servicecommande.service;

import sn.isi.servicecommande.dto.CommandeDetailDto;
import sn.isi.servicecommande.dto.CommandeDto;
import sn.isi.servicecommande.dto.CommandeRequestDto;

import java.util.List;

public interface ICommande {
    CommandeDto createcommande(CommandeRequestDto request);
    CommandeDto getCommandeById(Integer id);
    CommandeDetailDto getCommandeDetails(Integer id);
    List<CommandeDto> getAllCommandes();
    void deleteCommande(Integer id);
}
