package org.informatics.dao;

import org.informatics.entity.TransportClient;
import org.informatics.entity.TransportClientKey;

public interface TransportClientDao {
    TransportClient findById(TransportClientKey id);
    void save(TransportClient transportClient);
    void update(TransportClient transportClient);
    void delete(TransportClient transportClient);
}
