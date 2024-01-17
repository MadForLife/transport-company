package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.QualificationDao;
import org.informatics.entity.Qualification;

public class QualificationDaoImpl implements QualificationDao {

    private final SessionFactory sessionFactory;

    public QualificationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Qualification findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Qualification.class, id);
        }
    }

    @Override
    public void save(Qualification qualification) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(qualification);
            transaction.commit();
        }
    }

    @Override
    public void update(Qualification qualification) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(qualification);
            transaction.commit();
        }
    }

    @Override
    public void delete(Qualification qualification) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(qualification);
            transaction.commit();
        }
    }
}
