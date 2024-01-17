package org.informatics.dao;

import org.informatics.entity.TransportType;

public interface TransportTypeDao {
    TransportType findById(long id);
    void save(TransportType transportType);
    void update(TransportType transportType);
    void delete(TransportType transportType);
}
