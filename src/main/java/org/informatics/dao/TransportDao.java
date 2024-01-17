package org.informatics.dao;

import org.informatics.entity.Transport;

public interface TransportDao {
    Transport findById(long id);
    void save(Transport transport);
    void update(Transport transport);
    void delete(Transport transport);
}
