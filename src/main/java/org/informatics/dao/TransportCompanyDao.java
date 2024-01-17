package org.informatics.dao;

import org.informatics.entity.TransportCompany;

public interface TransportCompanyDao {
    TransportCompany findById(long id);
    void save(TransportCompany transportCompany);
    void update(TransportCompany transportCompany);
    void delete(TransportCompany transportCompany);
}
