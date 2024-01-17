package org.informatics.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.informatics.entity.*;

public class SessionFactoryUtil {

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Qualification.class);
            configuration.addAnnotatedClass(Transport.class);
            configuration.addAnnotatedClass(TransportClient.class);
            configuration.addAnnotatedClass(TransportCompany.class);
            configuration.addAnnotatedClass(TransportType.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(VehicleType.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
