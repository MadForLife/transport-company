package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.EmployeeDao;
import org.informatics.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Employee findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Employee.class, id);
        }
    }

    @Override
    public void save(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(employee);
            transaction.commit();
        }
    }
}
