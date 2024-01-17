package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.TransportDao;
import org.informatics.entity.Transport;

public class TransportDaoImpl implements TransportDao {

    private final SessionFactory sessionFactory;

    public TransportDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Transport findById(long id) {
        try (Session session = sessionFactory.openSession()) {
           return session.find(Transport.class, id);
        }
    }

    @Override
    public void save(Transport transport) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(transport);
            transaction.commit();
        }
    }

    @Override
    public void update(Transport transport) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(transport);
            transaction.commit();
        }
    }

    @Override
    public void delete(Transport transport) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(transport);
            transaction.commit();
        }
    }
}
