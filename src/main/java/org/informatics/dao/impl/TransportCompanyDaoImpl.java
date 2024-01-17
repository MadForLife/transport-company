package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.TransportCompanyDao;
import org.informatics.entity.TransportCompany;

public class TransportCompanyDaoImpl implements TransportCompanyDao {

    private final SessionFactory sessionFactory;

    public TransportCompanyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TransportCompany findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(TransportCompany.class, id);
        }
    }

    @Override
    public void save(TransportCompany transportCompany) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(transportCompany);
            transaction.commit();
        }
    }

    @Override
    public void update(TransportCompany transportCompany) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(transportCompany);
            transaction.commit();
        }
    }

    @Override
    public void delete(TransportCompany transportCompany) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(transportCompany);
            transaction.commit();
        }
    }
}
