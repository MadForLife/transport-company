package org.informatics;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.hibernate.SessionFactory;
import org.informatics.configuration.SessionFactoryUtil;
import org.informatics.dao.*;
import org.informatics.dao.impl.*;
import org.informatics.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();

        ClientDao clientDao = new ClientDaoImpl(sessionFactory);
        EmployeeDao employeeDao = new EmployeeDaoImpl(sessionFactory);
        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        QualificationDao qualificationDao = new QualificationDaoImpl(sessionFactory);
        TransportClientDao transportClientDao = new TransportClientDaoImpl(sessionFactory);
        TransportCompanyDao transportCompanyDao = new TransportCompanyDaoImpl(sessionFactory);
        TransportDao transportDao = new TransportDaoImpl(sessionFactory);
        TransportTypeDao transportTypeDao = new TransportTypeDaoImpl(sessionFactory);
        VehicleDao vehicleDao = new VehicleDaoImpl(sessionFactory);
        VehicleTypeDao vehicleTypeDao = new VehicleTypeDaoImpl(sessionFactory);

        List<TransportCompanyDto> transportCompanyDtoList = transportCompanyDao.sortByNameASC();
        transportCompanyDtoList.forEach(System.out::println);

        List<TransportCompanyDto> filteredByName = transportCompanyDao.filterByName("DHL");
        filteredByName.forEach(System.out::println);

        List<TransportCompanyRevenueDto> sortedByRevenue = transportCompanyDao.sortByRevenueASC();
        sortedByRevenue.forEach(System.out::println);

        List<TransportCompanyRevenueDto> filteredByRevenueGreaterThan = transportCompanyDao.filterByRevenueGreaterOrEqualTo(BigDecimal.valueOf(2099.99));
        filteredByRevenueGreaterThan.forEach(System.out::println);

        List<TransportCompanyRevenueDto> sortedByRevenueLessThan = transportCompanyDao.filterByRevenueLessOrEqualTo(BigDecimal.valueOf(2099.98));
        sortedByRevenueLessThan.forEach(System.out::println);

        Long totalNumberOfCarriedOutTransportsByCompany = transportCompanyDao.totalNumberOfCarriedOutTransports(2);
        System.out.println(totalNumberOfCarriedOutTransportsByCompany);

        BigDecimal totalRevenueOfCarriedOutTransportsByCompany = transportCompanyDao.totalRevenueOfCarriedOutTransports(1);
        System.out.println(totalRevenueOfCarriedOutTransportsByCompany);

        List<EmployeeTransportCountDto> carriedOutTransportsByEmployee = transportCompanyDao.totalNumberOfTransportsByDrivers(2);
        carriedOutTransportsByEmployee.forEach(System.out::println);

        List<TransportCompanyEmployeeIncomeDto> employeeIncome = transportCompanyDao.calculateEmployeeIncomeByCompany(2);
        employeeIncome.forEach(System.out::println);

        List<EmployeeQualificationDto> sortedByQualification = employeeDao.sortByQualificationASC();
        sortedByQualification.forEach(System.out::println);

        List<EmployeeQualificationDto> filteredByQualification = employeeDao.filterByQualificationASC("CDL-A");
        filteredByQualification.forEach(System.out::println);

        List<EmployeeSalaryDto> sortedBySalary = employeeDao.sortBySalaryASC();
        sortedBySalary.forEach(System.out::println);

        List<EmployeeSalaryDto> filteredBySalaryGreaterThan = employeeDao.filterBySalaryGreaterOrEqualTo(BigDecimal.valueOf(45000.01));
        filteredBySalaryGreaterThan.forEach(System.out::println);

        List<EmployeeSalaryDto> filteredBySalaryLessThan = employeeDao.filterBySalaryLessOrEqualTo(BigDecimal.valueOf(49999.00));
        filteredBySalaryLessThan.forEach(System.out::println);

        TransportCompanyPeriodIncomeDTO periodIncomeDTO = transportCompanyDao.calculateIncomeBetweenDates(
                2L,
                LocalDateTime.of(2024, 1, 21, 6, 0, 0),
                LocalDateTime.of(2024, 1, 22, 9, 0, 0));

        System.out.println(periodIncomeDTO);

        List<TransportDestinationDto> sortedByDestination = transportDao.sortByDestinationASC();
        sortedByDestination.forEach(System.out::println);

        List<TransportDestinationDto> filteredByDestination = transportDao.filterByDestinationASC("City B");
        filteredByDestination.forEach(System.out::println);

    }

    private static void createTransportCompanies(TransportCompanyDao transportCompanyDao) {
    }

    private static void updateTransportCompanies(TransportCompanyDao transportCompanyDao) {
    }


}