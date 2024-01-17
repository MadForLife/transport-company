package org.informatics.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.TransportClientDao;
import org.informatics.entity.Client;
import org.informatics.entity.Transport;
import org.informatics.entity.TransportClient;
import org.informatics.entity.TransportClientKey;

public class TransportClientDaoImpl implements TransportClientDao {
    //TODO ! Refactor
    private final SessionFactory sessionFactory;

    public TransportClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TransportClient findById(TransportClientKey id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(TransportClient.class, id);
        }
    }

    @Override
    public void save(TransportClient transportClient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Transport transport = session.find(Transport.class, transportClient.getTransport().getId());
            Client client = session.find(Client.class, transportClient.getClient().getId());

            transportClient.setTransport(transport);
            transportClient.setClient(client);

            session.persist(transportClient);

            transaction.commit();
        }
    }

    @Override
    public void update(TransportClient transportClient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(transportClient);
            transaction.commit();
        }
    }

    @Override
    public void delete(TransportClient transportClient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(transportClient);
            transaction.commit();
        }
    }
}
