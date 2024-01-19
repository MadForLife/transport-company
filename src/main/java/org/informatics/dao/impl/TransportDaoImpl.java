package org.informatics.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.TransportDao;
import org.informatics.dto.TransportDestinationDto;
import org.informatics.entity.Transport;

import java.util.List;

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

    @Override
    public List<TransportDestinationDto> sortByDestinationASC() {
        List<TransportDestinationDto> sortedByDestination;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportDestinationDto> criteriaQuery = criteriaBuilder.createQuery(TransportDestinationDto.class);
            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportDestinationDto.class,
                            transportRoot.get("id"),
                            transportRoot.get("endPoint"),
                            transportRoot.get("departureDate"),
                            transportRoot.get("arrivalDate"),
                            transportRoot.get("cost")
                    )
            ).orderBy(criteriaBuilder.asc(transportRoot.get("endPoint")));

            TypedQuery<TransportDestinationDto> query = session.createQuery(criteriaQuery);
            sortedByDestination = query.getResultList();
        }
        return sortedByDestination;
    }

    @Override
    public List<TransportDestinationDto> filterByDestinationASC(String destination) {
        List<TransportDestinationDto> filteredByDestination;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportDestinationDto> criteriaQuery = criteriaBuilder.createQuery(TransportDestinationDto.class);
            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);

            Predicate destinationLike = criteriaBuilder
                    .like(transportRoot.get("endPoint"), "%" + destination + "%");

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportDestinationDto.class,
                            transportRoot.get("id"),
                            transportRoot.get("endPoint"),
                            transportRoot.get("departureDate"),
                            transportRoot.get("arrivalDate"),
                            transportRoot.get("cost")
                    )
            ).where(destinationLike)
                    .orderBy(criteriaBuilder.asc(transportRoot.get("endPoint")));

            TypedQuery<TransportDestinationDto> query = session.createQuery(criteriaQuery);
            filteredByDestination = query.getResultList();
        }
        return filteredByDestination;
    }
}
