package sn.isi.serviceclient.mapper;

import sn.isi.serviceclient.dto.ClientDto;
import sn.isi.serviceclient.entity.Client;

public class ClientMapper {
    public static ClientDto toDTO(Client c) {
        return ClientDto.builder()
                .id(c.getId())
                .nom(c.getNom())
                .prenom(c.getPrenom())
                .email(c.getEmail())
                .adresse(c.getAdresse())
                .telephone(c.getTelephone())
                .build();
    }

    public static Client toEntity(ClientDto d) {
        return Client.builder()
                .id(d.getId())
                .nom(d.getNom())
                .prenom(d.getPrenom())
                .email(d.getEmail())
                .adresse(d.getAdresse())
                .telephone(d.getTelephone())
                .build();
    }
}
