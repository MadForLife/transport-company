package org.informatics;

import org.hibernate.SessionFactory;
import org.informatics.configuration.SessionFactoryUtil;
import org.informatics.dao.*;
import org.informatics.dao.impl.*;
import org.informatics.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    }

    private static void createTransportCompanies(TransportCompanyDao transportCompanyDao) {
    }

    private static void updateTransportCompanies(TransportCompanyDao transportCompanyDao) {
    }


}