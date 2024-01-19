package org.informatics.dao;

import org.informatics.dto.*;
import org.informatics.entity.TransportCompany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransportCompanyDao {
    TransportCompany findById(long id);
    void save(TransportCompany transportCompany);
    void update(TransportCompany transportCompany);
    void delete(TransportCompany transportCompany);

    TransportCompanyDto findByIdDTO(long id);
    List<TransportCompanyDto> findAllDTO();
    void saveDTO(CreateTransportCompanyDto createTransportCompanyDto);
    void updateDTO(TransportCompanyDto transportCompanyDto);
    void deleteDTO(TransportCompanyDto transportCompanyDto);

    List<TransportCompanyDto> sortByNameASC();
    List<TransportCompanyDto> filterByName(String name);
    List<TransportCompanyRevenueDto> sortByRevenueASC();
    List<TransportCompanyRevenueDto> filterByRevenueGreaterOrEqualTo(BigDecimal minimumRevenue);
    List<TransportCompanyRevenueDto> filterByRevenueLessOrEqualTo(BigDecimal minimumRevenue);
    Long totalNumberOfCarriedOutTransports(long transportCompanyId);
    BigDecimal totalRevenueOfCarriedOutTransports(long transportCompanyId);
    List<EmployeeTransportCountDto> totalNumberOfTransportsByDrivers(long transportCompanyId);
    List<TransportCompanyEmployeeIncomeDto> calculateEmployeeIncomeByCompany(long transportCompanyId);
    TransportCompanyPeriodIncomeDTO calculateIncomeBetweenDates(long transportCompanyId, LocalDateTime lowerDateTime, LocalDateTime upperDateTime);

}
