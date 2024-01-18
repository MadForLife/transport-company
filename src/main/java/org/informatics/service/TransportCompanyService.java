package org.informatics.service;

import org.informatics.dto.CreateTransportCompanyDto;
import org.informatics.dto.TransportCompanyDto;

import java.util.List;

public interface TransportCompanyService {
    TransportCompanyDto findById(long id);
    List<TransportCompanyDto> findAll();
    void save(CreateTransportCompanyDto createTransportCompanyDto);
    void update(TransportCompanyDto TransportCompanyDto);
    void delete(TransportCompanyDto transportCompanyDto);
}
