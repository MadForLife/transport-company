package org.informatics.dao;

import org.informatics.dto.ClientDto;
import org.informatics.dto.CreateClientDto;
import org.informatics.entity.Client;

import java.util.List;

public interface ClientDao {
    Client findById(long id);
    void save(Client client);
    void update(Client client);
    void delete(Client client);

    ClientDto findByIdDTO(long id);
    List<ClientDto> findAllDTO();
}
