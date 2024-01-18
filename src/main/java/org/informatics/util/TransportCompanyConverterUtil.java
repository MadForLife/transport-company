package org.informatics.util;

import org.informatics.dto.CreateTransportCompanyDto;
import org.informatics.dto.TransportCompanyDto;
import org.informatics.entity.TransportCompany;

public class TransportCompanyConverterUtil {

    public static TransportCompany convertCreateCompanyToEntity(CreateTransportCompanyDto createTransportCompanyDto) {

        TransportCompany transportCompany = new TransportCompany();

        transportCompany.setName(createTransportCompanyDto.getName());
        transportCompany.setFoundationDate(createTransportCompanyDto.getFoundationDate());

        return transportCompany;
    }

    public static TransportCompany convertToEntity(TransportCompanyDto transportCompanyDto) {

        TransportCompany transportCompany = new TransportCompany();

        transportCompany.setId(transportCompanyDto.getId());
        transportCompany.setName(transportCompanyDto.getName());
        transportCompany.setFoundationDate(transportCompanyDto.getFoundationDate());

        return transportCompany;
    }

}
