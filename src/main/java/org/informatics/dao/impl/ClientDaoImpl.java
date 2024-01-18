package org.informatics.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.ClientDao;
import org.informatics.dto.ClientDto;
import org.informatics.entity.Client;

import java.util.List;

public class ClientDaoImpl implements ClientDao {

    private final SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Client.class, id);
        }
    }

    @Override
    public void save(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }
    }

    @Override
    public void update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        }
    }

    @Override
    public void delete(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        }
    }

    @Override
    public ClientDto findByIdDTO(long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ClientDto> criteriaQuery = criteriaBuilder.createQuery(ClientDto.class);
            Root<Client> clientRoot = criteriaQuery.from(Client.class);

            Predicate idEquals = criteriaBuilder.equal(clientRoot.get("id"), id);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            ClientDto.class,
                            clientRoot.get("id"),
                            clientRoot.get("firstName"),
                            clientRoot.get("lastName"),
                            clientRoot.get("birthDate"),
                            clientRoot.get("address"),
                            clientRoot.get("transportCompany")
                    )
            ).where(idEquals);

            TypedQuery<ClientDto> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        }
    }

    @Override
    public List<ClientDto> findAllDTO() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ClientDto> criteriaQuery = criteriaBuilder.createQuery(ClientDto.class);
            Root<Client> clientRoot = criteriaQuery.from(Client.class);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            ClientDto.class,
                            clientRoot.get("id"),
                            clientRoot.get("firstName"),
                            clientRoot.get("lastName"),
                            clientRoot.get("birthDate"),
                            clientRoot.get("address"),
                            clientRoot.get("transportCompany")
                    )
            );

            TypedQuery<ClientDto> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
