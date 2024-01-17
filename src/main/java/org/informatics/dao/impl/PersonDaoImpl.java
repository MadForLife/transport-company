package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.PersonDao;
import org.informatics.entity.Person;

public class PersonDaoImpl implements PersonDao {

    private final SessionFactory sessionFactory;

    public PersonDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Person.class, id);
        }
    }

    @Override
    public void save(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(person);
            transaction.commit();
        }
    }

    @Override
    public void update(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(person);
            transaction.commit();
        }
    }

    @Override
    public void delete(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(person);
            transaction.commit();
        }
    }
}
