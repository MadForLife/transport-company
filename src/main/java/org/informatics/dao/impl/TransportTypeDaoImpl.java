package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.TransportTypeDao;
import org.informatics.entity.TransportType;

public class TransportTypeDaoImpl implements TransportTypeDao {

    private final SessionFactory sessionFactory;

    public TransportTypeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TransportType findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(TransportType.class, id);
        }
    }

    @Override
    public void save(TransportType transportType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(transportType);
            transaction.commit();
        }
    }

    @Override
    public void update(TransportType transportType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(transportType);
            transaction.commit();
        }
    }

    @Override
    public void delete(TransportType transportType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(transportType);
            transaction.commit();
        }
    }
}
