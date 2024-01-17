package org.informatics.dao;

import org.informatics.entity.Client;

public interface ClientDao {
    Client findById(long id);
    void save(Client client);
    void update(Client client);
    void delete(Client client);
}
