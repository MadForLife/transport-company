package org.informatics;

import com.google.protobuf.DescriptorProtos;
import jdk.dynalink.beans.StaticClass;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.hibernate.SessionFactory;
import org.informatics.configuration.SessionFactoryUtil;
import org.informatics.dao.*;
import org.informatics.dao.impl.*;
import org.informatics.dto.*;
import org.informatics.entity.*;
import org.informatics.exception.EmployeeDoesNotMeetQualificationRequirements;
import org.informatics.service.TransportService;
import org.informatics.service.TransportServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        TransportService transportService = new TransportServiceImpl(transportDao);

//        createTransportCompanies(transportCompanyDao);
//        editTransportCompanies(transportCompanyDao);
//        deleteTransportCompany(transportCompanyDao); // TODO On Delete Behaviour
//        createEmployees(employeeDao, transportCompanyDao);
//        editEmployees(employeeDao);
//        deleteEmployees(employeeDao);
//        createClients(clientDao, transportCompanyDao);
//        editClients(clientDao);
//        deleteClients(clientDao);
//        createQualifications(qualificationDao);
//        createVehicleTypes(vehicleTypeDao);
//        createVehicles(vehicleDao, vehicleTypeDao, qualificationDao, transportCompanyDao);
//        editVehicles(vehicleDao);
//        deleteVehicles(vehicleDao);
//        createTransportTypes(transportTypeDao);
//        createTransports(transportDao, employeeDao, vehicleDao, transportTypeDao, transportCompanyDao);
//        addClientsToTransport(transportDao, transportClientDao, clientDao);

//        try {
//            createTransports(transportService, employeeDao, vehicleDao, transportTypeDao, transportCompanyDao);
//        } catch (EmployeeDoesNotMeetQualificationRequirements e) {
//            throw new RuntimeException(e);
//        }

//        addQualificationsToEmployees(employeeDao, qualificationDao);


        sortCompaniesByName(transportCompanyDao);
        sortCompaniesByRevenue(transportCompanyDao);
        sortEmployeesByQualification(employeeDao);
        sortEmployeesBySalary(employeeDao);
        sortTransportByDestination(transportDao);
        filterTransportsByDestination(transportDao, "Burgas");
        totalNumberOfCarriedOutTransports(transportCompanyDao, 1);
        totalRevenueFromCarriedOutTransports(transportCompanyDao, 1);
        driverTransportCount(transportCompanyDao, 1);
        driverIncome(transportCompanyDao, 1);
    }



    // =============================================  Transport Company =============================================

    private static void createTransportCompanies(TransportCompanyDao transportCompanyDao) {

        TransportCompany transportCompany1 = new TransportCompany("Fast Track Logistics", LocalDate.of(1998, 7, 15));
        TransportCompany transportCompany2 = new TransportCompany("Global Movers Inc", LocalDate.of(2002, 9, 9));
        TransportCompany transportCompany3 = new TransportCompany("Swift Wheels Transport", LocalDate.of(2005, 3, 22));
        TransportCompany transportCompany4 = new TransportCompany("Horizon Freight", LocalDate.of(2010, 7, 30));
        TransportCompany transportCompany5 = new TransportCompany("Eco Transit Solutions", LocalDate.of(2015, 4, 18));

        transportCompanyDao.save(transportCompany1);
        transportCompanyDao.save(transportCompany2);
        transportCompanyDao.save(transportCompany3);
        transportCompanyDao.save(transportCompany4);
        transportCompanyDao.save(transportCompany5);
    }

    private static void editTransportCompanies(TransportCompanyDao transportCompanyDao) {

        TransportCompany transportCompany1 = transportCompanyDao.findById(1L);
        TransportCompany transportCompany2 = transportCompanyDao.findById(2L);

        transportCompany1.setName("DHL");
        transportCompany2.setName("FedEx");

        transportCompanyDao.update(transportCompany1);
        transportCompanyDao.update(transportCompany2);
    }

    private static void deleteTransportCompany(TransportCompanyDao transportCompanyDao) {

        TransportCompany transportCompany1 = transportCompanyDao.findById(10L);
        TransportCompany transportCompany2 = transportCompanyDao.findById(11L);

        transportCompanyDao.delete(transportCompany1);
        transportCompanyDao.delete(transportCompany2);
    }

    // =============================================  Employees  =============================================

    private static void createEmployees(EmployeeDao employeeDao, TransportCompanyDao transportCompanyDao) {

        TransportCompany transportCompany = transportCompanyDao.findById(1L);

        Employee employee1 = new Employee(
                "John",
                "Doe",
                LocalDate.of(1985, 4, 23),
                BigDecimal.valueOf(3200.00),
                transportCompany
        );

        Employee employee2 = new Employee(
                "Alice",
                "Smith",
                LocalDate.of(1990, 7, 16),
                BigDecimal.valueOf(2800.00),
                transportCompany
        );

        Employee employee3 = new Employee(
                "Michael",
                "Brown",
                LocalDate.of(1978, 11, 2),
                BigDecimal.valueOf(4500.00),
                transportCompany
        );

        Employee employee4 = new Employee(
                "Linda",
                "Johnson",
                LocalDate.of(1983, 2, 19),
                BigDecimal.valueOf(4000.00),
                transportCompany
        );

        Employee employee5 = new Employee(
                "Robert",
                "Wilson",
                LocalDate.of(1992, 9, 5),
                BigDecimal.valueOf(3600.00),
                transportCompany
        );

        employeeDao.save(employee1);
        employeeDao.save(employee2);
        employeeDao.save(employee3);
        employeeDao.save(employee4);
        employeeDao.save(employee5);
    }

    private static void editEmployees(EmployeeDao employeeDao) {

        Employee employee1 = employeeDao.findById(1);
        Employee employee2 = employeeDao.findById(2);

        employee1.setEmployeeSalary(BigDecimal.valueOf(4000.00));
        employee2.setEmployeeSalary(BigDecimal.valueOf(4000.00));

        employeeDao.update(employee1);
        employeeDao.update(employee2);
    }

    private static void deleteEmployees(EmployeeDao employeeDao) {

        Employee employee1 = employeeDao.findById(1);
        Employee employee2 = employeeDao.findById(2);

        employeeDao.delete(employee1);
        employeeDao.delete(employee2);
    }

    // =============================================  Clients  =============================================

    private static void createClients(ClientDao clientDao, TransportCompanyDao transportCompanyDao) {

        TransportCompany transportCompany = transportCompanyDao.findById(1L);

        Client client1 = new Client(
                "Emily",
                "Turner",
                LocalDate.of(1991, 3, 12),
                "123 Maple Street, Springfield",
                transportCompany
        );

        Client client2 = new Client(
                "David",
                "Green",
                LocalDate.of(1984, 8, 25),
                "456 Oak Avenue, Riverdale",
                transportCompany
        );

        Client client3 = new Client(
                "Sarah",
                "Johnson",
                LocalDate.of(1975, 2, 17),
                "789 Pine Road, Lakeside",
                transportCompany
        );

        Client client4 = new Client(
                "Daniel",
                "Martinez",
                LocalDate.of(1988, 11, 30),
                "159 Elm Lane, Brookville",
                transportCompany
        );

        Client client5 = new Client(
                "Laura",
                "White",
                LocalDate.of(1993, 5, 21),
                "246 Cedar Path, Hillcrest",
                transportCompany
        );

        clientDao.save(client1);
        clientDao.save(client2);
        clientDao.save(client3);
        clientDao.save(client4);
        clientDao.save(client5);
    }

    private static void editClients(ClientDao clientDao) {

        Client client1 = clientDao.findById(6);
        Client client2 = clientDao.findById(7);

        client1.setAddress("Netherlands ABC");
        client2.setAddress("Netherlands XYZ");

        clientDao.update(client1);
        clientDao.update(client2);
    }

    private static void deleteClients(ClientDao clientDao) {

        Client client1 = clientDao.findById(6);
        Client client2 = clientDao.findById(7);

        clientDao.delete(client1);
        clientDao.delete(client2);
    }

    // =============================================  Qualifications  =============================================

    private static void createQualifications(QualificationDao qualificationDao) {

        Qualification qualification1 = new Qualification(
                "QLFY123",
                "Professional Driver Certification"
        );

        Qualification qualification2 = new Qualification(
                "LOGST789",
                "Logistic Management Expertise"
        );

        Qualification qualification3 = new Qualification(
                "SAFE101",
                "Safety and Compliance Training"
        );

        Qualification qualification4 = new Qualification(
                "HVYLD456",
                "Heavy Load Handling"
        );

        Qualification qualification5 = new Qualification(
                "TECH234",
                "Technical Vehicle Maintenance"
        );

        qualificationDao.save(qualification1);
        qualificationDao.save(qualification2);
        qualificationDao.save(qualification3);
        qualificationDao.save(qualification4);
        qualificationDao.save(qualification5);
    }

    // =============================================  Vehicle Type  =============================================

    private static void createVehicleTypes(VehicleTypeDao vehicleTypeDao) {

        VehicleType vehicleType1 = new VehicleType(
                "TRUCK",
                "Standard cargo truck for general transport"
        );

        VehicleType vehicleType2 = new VehicleType(
                "VAN",
                "Small van for quick and local deliveries"
        );

        VehicleType vehicleType3 = new VehicleType(
                "TANKER",
                "Tanker vehicle for liquid goods transport"
        );

        VehicleType vehicleType4 = new VehicleType(
                "FLATBED",
                "Flatbed truck for oversized loads"
        );

        VehicleType vehicleType5 = new VehicleType(
                "REFRIG",
                "Refrigerated truck for for perishable goods"
        );

        VehicleType vehicleType6 = new VehicleType(
                "BUS",
                "Comfortable bus for long-distance passenger transport"
        );

        vehicleTypeDao.save(vehicleType1);
        vehicleTypeDao.save(vehicleType2);
        vehicleTypeDao.save(vehicleType3);
        vehicleTypeDao.save(vehicleType4);
        vehicleTypeDao.save(vehicleType5);
        vehicleTypeDao.save(vehicleType6);
    }

    // =============================================  Vehicles  =============================================

    private static void createVehicles(VehicleDao vehicleDao, VehicleTypeDao vehicleTypeDao, QualificationDao qualificationDao, TransportCompanyDao transportCompanyDao) {

        VehicleType truck = vehicleTypeDao.findById(1);
        VehicleType van = vehicleTypeDao.findById(2);
        VehicleType flatBed = vehicleTypeDao.findById(4);
        VehicleType bus = vehicleTypeDao.findById(6);
        VehicleType refrigeratedTruck = vehicleTypeDao.findById(5);

        Qualification professionalDriver = qualificationDao.findById(1);
        Qualification safetyAndComplianceTraining = qualificationDao.findById(3);
        Qualification technicalVehicleMaintenance = qualificationDao.findById(5);
        Qualification logisticsManagementExpertise = qualificationDao.findById(2);
        Qualification heavyLoadHandling = qualificationDao.findById(4);

        TransportCompany transportCompany = transportCompanyDao.findById(1);

        Vehicle vehicle1 = new Vehicle(
                "AB1234CD",
                (short) 4,
                BigDecimal.valueOf(500.00),
                truck,
                professionalDriver,
                transportCompany
        );

        Vehicle vehicle2 = new Vehicle(
                "EF5678GH",
                (short) 2,
                BigDecimal.valueOf(200.00),
                van,
                safetyAndComplianceTraining,
                transportCompany
        );

        Vehicle vehicle3 = new Vehicle(
                "IJ9012KL",
                (short) 3,
                BigDecimal.valueOf(1500.00),
                flatBed,
                technicalVehicleMaintenance,
                transportCompany
        );

        Vehicle vehicle4 = new Vehicle(
                "MN3456OP",
                (short) 36,
                BigDecimal.valueOf(100.00),
                bus,
                logisticsManagementExpertise,
                transportCompany
        );

        Vehicle vehicle5 = new Vehicle(
                "QR7890ST",
                (short) 2,
                BigDecimal.valueOf(800.00),
                refrigeratedTruck,
                heavyLoadHandling,
                transportCompany
        );

        vehicleDao.save(vehicle1);
        vehicleDao.save(vehicle2);
        vehicleDao.save(vehicle3);
        vehicleDao.save(vehicle4);
        vehicleDao.save(vehicle5);
    }

    private static void editVehicles(VehicleDao vehicleDao) {

        Vehicle vehicle1 = vehicleDao.findById(1);
        Vehicle vehicle2 = vehicleDao.findById(2);

        vehicle1.setSeatingCapacity((short)5);
        vehicle2.setSeatingCapacity((short)3);

        vehicleDao.update(vehicle1);
        vehicleDao.update(vehicle2);
    }

    private static void deleteVehicles(VehicleDao vehicleDao) {

        Vehicle vehicle1 = vehicleDao.findById(1);
        Vehicle vehicle2 = vehicleDao.findById(2);

        vehicleDao.delete(vehicle1);
        vehicleDao.delete(vehicle2);
    }

    // =============================================  Transport Type  =============================================

    private static void createTransportTypes(TransportTypeDao transportTypeDao) {

        TransportType transportType1 = new TransportType(
                "CARGO",
                "Large-scale cargo transport for goods and materials"
        );

        TransportType transportType2 = new TransportType(
                "PASSENGER",
                "Passenger transport services for both short and long distances"
        );

        TransportType transportType3 = new TransportType(
                "EXPRESS",
                "High-speed transport services for urgent deliveries"
        );

        TransportType transportType4 = new TransportType(
                "LOGISTICS",
                "Comprehensive logistics and supply chain management services"
        );

        TransportType transportType5 = new TransportType(
                "SPECIALIZED",
                "Specialized transport for sensitive or oversized goods"
        );

        transportTypeDao.save(transportType1);
        transportTypeDao.save(transportType2);
        transportTypeDao.save(transportType3);
        transportTypeDao.save(transportType4);
        transportTypeDao.save(transportType5);
    }

    // =============================================  Transport  =============================================

    private static void createTransports(
            TransportDao transportDao, EmployeeDao employeeDao,
            VehicleDao vehicleDao, TransportTypeDao transportTypeDao,
            TransportCompanyDao transportCompanyDao) {

        Employee employee1 = employeeDao.findById(4);
        Employee employee2 = employeeDao.findById(5);

        Vehicle bus = vehicleDao.findById(4);
        Vehicle refrigeratedTruck = vehicleDao.findById(5);
        Vehicle tanker = vehicleDao.findById(3);

        TransportType cargo = transportTypeDao.findById(1);
        TransportType passenger = transportTypeDao.findById(2);
        TransportType specialized = transportTypeDao.findById(5);

        TransportCompany transportCompany = transportCompanyDao.findById(1);
        TransportCompany transportCompany1 = transportCompanyDao.findById(2);

        Transport transport1 = new Transport(
                "Sofia",
                "Burgas",
                LocalDateTime.of(2024, 2, 15, 9, 00),
                LocalDateTime.of(2024, 02, 16, 16, 00),
                BigDecimal.valueOf(1200.00),
                BigDecimal.valueOf(5000.00),
                employee1,
                refrigeratedTruck,
                cargo,
                transportCompany
        );

        // TODO Add Passengers
        Transport transport2 = new Transport(
                "Sofia",
                "Plovdiv",
                LocalDateTime.of(2024, 3, 10, 9, 30),
                LocalDateTime.of(2024, 03, 10, 18, 45),
                BigDecimal.valueOf(800),
                BigDecimal.valueOf(2500),
                employee2,
                bus,
                passenger,
                transportCompany1
        );

        Transport transport3 = new Transport(
                "Stara Zagora",
                "Sliven",
                LocalDateTime.of(2024, 4, 5, 7, 00),
                LocalDateTime.of(2024, 4, 5, 10, 00),
                BigDecimal.valueOf(1500.00),
                BigDecimal.valueOf(3000.00),
                employee2,
                tanker,
                specialized,
                transportCompany
        );

        Transport transport4 = new Transport(
                "Varna",
                "Burgas",
                LocalDateTime.of(2024, 2, 15, 9, 00),
                LocalDateTime.of(2024, 02, 16, 16, 00),
                BigDecimal.valueOf(1200.00),
                BigDecimal.valueOf(5000.00),
                employee1,
                refrigeratedTruck,
                cargo,
                transportCompany1
        );

        Transport transport5 = new Transport(
                "Sofia",
                "Sliven",
                LocalDateTime.of(2024, 6, 11, 10, 15),
                LocalDateTime.of(2024, 6, 11, 20, 00),
                BigDecimal.valueOf(1100.00),
                BigDecimal.valueOf(4000.00),
                employee1,
                refrigeratedTruck,
                cargo,
                transportCompany1
        );

        transportDao.save(transport1);
        transportDao.save(transport2);
        transportDao.save(transport3);
        transportDao.save(transport4);
        transportDao.save(transport5);
    }

    private static void addClientsToTransport(TransportDao transportDao, TransportClientDao transportClientDao, ClientDao clientDao) {

        Transport transport = transportDao.findById(2);

        Client client1 = clientDao.findById(8);
        Client client2 = clientDao.findById(9);
        Client client3 = clientDao.findById(10);

        TransportClientKey transportClientKey1 = new TransportClientKey(transport.getId(), client1.getId());
        TransportClientKey transportClientKey2 = new TransportClientKey(transport.getId(), client2.getId());
        TransportClientKey transportClientKey3 = new TransportClientKey(transport.getId(), client3.getId());

        TransportClient transportClient1 = new TransportClient(
                transportClientKey1,
                transport,
                client1,
                false
        );

        TransportClient transportClient2 = new TransportClient(
                transportClientKey2,
                transport,
                client2,
                true
        );

        TransportClient transportClient3 = new TransportClient(
                transportClientKey3,
                transport,
                client3,
                true
        );

        transportClientDao.save(transportClient1);
        transportClientDao.save(transportClient2);
        transportClientDao.save(transportClient3);
    }

    // =============================================  Advanced Queries  =============================================

    private static void addQualificationsToEmployees(EmployeeDao employeeDao, QualificationDao qualificationDao) {

        Qualification qualification1 = qualificationDao.findById(1);
        Qualification qualification2 = qualificationDao.findById(2);
        Qualification qualification3 = qualificationDao.findById(3);
        Qualification qualification4 = qualificationDao.findById(4);

        Employee employee1 = employeeDao.findById(3);
        Employee employee2 = employeeDao.findById(4);
        Employee employee3 = employeeDao.findById(5);

        Set<Qualification> qualificationSet1 = new HashSet<>();
        qualificationSet1.add(qualification1);
        qualificationSet1.add(qualification2);

        Set<Qualification> qualificationSet2 = new HashSet<>();
        qualificationSet1.add(qualification3);
        qualificationSet1.add(qualification4);

        employee1.setQualifications(qualificationSet1);
        employee2.setQualifications(qualificationSet2);
        employee3.setQualifications(qualificationSet1);

        employeeDao.update(employee1);
        employeeDao.update(employee2);
        employeeDao.update(employee3);
    }

    private static void sortCompaniesByName(TransportCompanyDao transportCompanyDao) {

        List<TransportCompanyDto> sortedByName = transportCompanyDao.sortByNameASC();
        sortedByName.forEach(System.out::println);
    }

    private static void sortCompaniesByRevenue(TransportCompanyDao transportCompanyDao) {

        List<TransportCompanyRevenueDto> sortedByRevenue = transportCompanyDao.sortByRevenueASC();
        sortedByRevenue.forEach(System.out::println);
    }

    private static void sortEmployeesByQualification(EmployeeDao employeeDao) {

        List<EmployeeQualificationDto> sortedByQualification = employeeDao.sortByQualificationASC();
        sortedByQualification.forEach(System.out::println);
    }

    private static void sortEmployeesBySalary(EmployeeDao employeeDao) {

        List<EmployeeSalaryDto> sortedBySalary = employeeDao.sortBySalaryASC();
        sortedBySalary.forEach(System.out::println);
    }

    private static void sortTransportByDestination(TransportDao transportDao) {

        List<TransportDestinationDto> sortedByDestination = transportDao.sortByDestinationASC();
        sortedByDestination.forEach(System.out::println);
    }

    private static void filterTransportsByDestination(TransportDao transportDao, String destination) {

        List<TransportDestinationDto> filteredByDestination = transportDao.filterByDestinationASC(destination);
        filteredByDestination.forEach(System.out::println);
    }

    private static void totalNumberOfCarriedOutTransports(TransportCompanyDao transportCompanyDao, long id) {
        System.out.println("Total transports: " + transportCompanyDao.totalNumberOfCarriedOutTransports(id));
    }

    private static void totalRevenueFromCarriedOutTransports(TransportCompanyDao transportCompanyDao, long id) {
        System.out.println("Total revenue from transports: " + transportCompanyDao.totalRevenueOfCarriedOutTransports(id));
    }

    private static void driverTransportCount(TransportCompanyDao transportCompanyDao, long id) {

        List<EmployeeTransportCountDto> transportCountDtos = transportCompanyDao.totalNumberOfTransportsByDrivers(id);
        transportCountDtos.forEach(System.out::println);
    }

    private static void driverIncome(TransportCompanyDao transportCompanyDao, long id) {

        List<TransportCompanyEmployeeIncomeDto> transportCompanyEmployeeIncomeDtos = transportCompanyDao.calculateEmployeeIncomeByCompany(id);
        transportCompanyEmployeeIncomeDtos.forEach(System.out::println);
    }

}