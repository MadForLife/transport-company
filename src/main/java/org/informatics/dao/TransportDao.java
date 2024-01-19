package org.informatics.dao;

import org.informatics.dto.TransportDestinationDto;
import org.informatics.entity.Transport;

import java.util.List;

public interface TransportDao {
    Transport findById(long id);
    void save(Transport transport);
    void update(Transport transport);
    void delete(Transport transport);

    List<TransportDestinationDto> sortByDestinationASC();
    List<TransportDestinationDto> filterByDestinationASC(String destination);
}
