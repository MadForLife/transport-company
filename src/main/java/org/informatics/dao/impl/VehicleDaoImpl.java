package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.VehicleDao;
import org.informatics.entity.Vehicle;

public class VehicleDaoImpl implements VehicleDao {

    private final SessionFactory sessionFactory;

    public VehicleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Vehicle findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Vehicle.class, id);
        }
    }

    @Override
    public void save(Vehicle vehicle) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(vehicle);
            transaction.commit();
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(vehicle);
            transaction.commit();
        }
    }

    @Override
    public void delete(Vehicle vehicle) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(vehicle);
            transaction.commit();
        }
    }
}
