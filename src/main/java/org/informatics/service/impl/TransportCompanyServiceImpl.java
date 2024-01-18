package org.informatics.service.impl;

import org.informatics.dao.TransportCompanyDao;
import org.informatics.dto.CreateTransportCompanyDto;
import org.informatics.dto.TransportCompanyDto;
import org.informatics.service.TransportCompanyService;

import java.util.List;

public class TransportCompanyServiceImpl implements TransportCompanyService {

    private TransportCompanyDao transportCompanyDao;

    public TransportCompanyServiceImpl(TransportCompanyDao transportCompanyDao) {
        this.transportCompanyDao = transportCompanyDao;
    }

    @Override
    public TransportCompanyDto findById(long id) {
        return null;
    }

    @Override
    public List<TransportCompanyDto> findAll() {
        return null;
    }

    @Override
    public void save(CreateTransportCompanyDto createTransportCompanyDto) {

    }

    @Override
    public void update(TransportCompanyDto transportCompanyDto) {

    }

    @Override
    public void delete(TransportCompanyDto transportCompanyDto) {

    }
}
