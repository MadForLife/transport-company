package org.informatics;

import org.hibernate.SessionFactory;
import org.informatics.configuration.SessionFactoryUtil;
import org.informatics.dao.*;
import org.informatics.dao.impl.*;
import org.informatics.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
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


    }
}