package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.VehicleTypeDao;
import org.informatics.entity.VehicleType;

public class VehicleTypeDaoImpl implements VehicleTypeDao {

    private final SessionFactory sessionFactory;

    public VehicleTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public VehicleType findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(VehicleType.class, id);
        }
    }

    @Override
    public void save(VehicleType vehicleType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(vehicleType);
            transaction.commit();;
        }
    }

    @Override
    public void update(VehicleType vehicleType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(vehicleType);
            transaction.commit();;
        }
    }

    @Override
    public void delete(VehicleType vehicleType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(vehicleType);
            transaction.commit();;
        }
    }
}
