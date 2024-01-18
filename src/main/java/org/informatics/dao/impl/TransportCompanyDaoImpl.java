package org.informatics.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.TransportCompanyDao;
import org.informatics.dto.*;
import org.informatics.entity.Employee;
import org.informatics.entity.Transport;
import org.informatics.entity.TransportCompany;
import org.informatics.util.TransportCompanyConverterUtil;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public TransportCompanyDto findByIdDTO(long id) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyDto.class);
            Root<TransportCompany> transportCompanyRoot = criteriaQuery.from(TransportCompany.class);

            Predicate idEquals = criteriaBuilder.equal(transportCompanyRoot.get("id"), id);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportCompanyDto.class,
                            transportCompanyRoot.get("id"),
                            transportCompanyRoot.get("name"),
                            transportCompanyRoot.get("foundationDate")
                    )
            ).where(idEquals);

            TypedQuery<TransportCompanyDto> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        }
    }

    @Override
    public List<TransportCompanyDto> findAllDTO() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyDto.class);
            Root<TransportCompany> transportCompanyRoot = criteriaQuery.from(TransportCompany.class);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportCompanyDto.class,
                            transportCompanyRoot.get("id"),
                            transportCompanyRoot.get("name"),
                            transportCompanyRoot.get("foundationDate")
                    )
            );

            TypedQuery<TransportCompanyDto> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    @Override
    public void saveDTO(CreateTransportCompanyDto createTransportCompanyDto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            TransportCompany transportCompany =
                    TransportCompanyConverterUtil.convertCreateCompanyToEntity(createTransportCompanyDto);

            session.persist(transportCompany);

            transaction.commit();
        }
    }

    @Override
    public void updateDTO(TransportCompanyDto transportCompanyDto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            TransportCompany transportCompany =
                    TransportCompanyConverterUtil.convertToEntity(transportCompanyDto);

            session.merge(transportCompany);

            transaction.commit();
        }
    }

    @Override
    public void deleteDTO(TransportCompanyDto transportCompanyDto) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            TransportCompany transportCompany =
                    TransportCompanyConverterUtil.convertToEntity(transportCompanyDto);

            session.remove(transportCompany);

            transaction.commit();
        }
    }

    @Override
    public List<TransportCompanyDto> sortByNameASC() {
        List<TransportCompanyDto> sortedByName;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyDto.class);
            Root<TransportCompany> transportCompanyRoot = criteriaQuery.from(TransportCompany.class);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportCompanyDto.class,
                            transportCompanyRoot.get("id"),
                            transportCompanyRoot.get("name"),
                            transportCompanyRoot.get("foundationDate")
                    )
            ).orderBy(criteriaBuilder.asc(transportCompanyRoot.get("name")));

            TypedQuery<TransportCompanyDto> query = session.createQuery(criteriaQuery);
            sortedByName = query.getResultList();
        }
        return sortedByName;
    }

    @Override
    public List<TransportCompanyDto> filterByName(String name) {
        List<TransportCompanyDto> filteredByName;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyDto.class);
            Root<TransportCompany> transportCompanyRoot = criteriaQuery.from(TransportCompany.class);

            Predicate companyNameLike = criteriaBuilder
                    .like(transportCompanyRoot.get("name"), "%" + name + "%");

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportCompanyDto.class,
                            transportCompanyRoot.get("id"),
                            transportCompanyRoot.get("name"),
                            transportCompanyRoot.get("foundationDate")
                    )
            ).where(companyNameLike);

            TypedQuery<TransportCompanyDto> query = session.createQuery(criteriaQuery);
            filteredByName = query.getResultList();
        }
        return filteredByName;
    }

    @Override
    public List<TransportCompanyRevenueDto> sortByRevenueASC() {
        List<TransportCompanyRevenueDto> sortedByRevenue;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyRevenueDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyRevenueDto.class);

            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);
            Join<Transport, TransportCompany> transportCompanyJoin = transportRoot.join("transportCompany");

            Expression<BigDecimal> sumTotalCost = criteriaBuilder.sum(transportRoot.get("cost"));

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportCompanyRevenueDto.class,
                            transportCompanyJoin.get("id"),
                            transportCompanyJoin.get("name"),
                            sumTotalCost
                    )
            ).groupBy(transportCompanyJoin.get("id"), transportCompanyJoin.get("name"))
                    .orderBy(criteriaBuilder.asc(sumTotalCost));

            TypedQuery<TransportCompanyRevenueDto> query = session.createQuery(criteriaQuery);
            sortedByRevenue = query.getResultList();
        }
        return sortedByRevenue;
    }

    @Override
    public List<TransportCompanyRevenueDto> filterByRevenueGreaterOrEqualTo(BigDecimal minimumRevenue) {
        List<TransportCompanyRevenueDto> filteredByRevenue;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyRevenueDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyRevenueDto.class);

            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);
            Join<Transport, TransportCompany> transportCompanyJoin = transportRoot.join("transportCompany");

            Expression<BigDecimal> calculateRevenue =
                    criteriaBuilder.sum(transportRoot.get("cost"));

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            TransportCompanyRevenueDto.class,
                            transportCompanyJoin.get("id"),
                            transportCompanyJoin.get("name"),
                            calculateRevenue
                    )
            ).groupBy(transportCompanyJoin.get("id"))
                    .having(criteriaBuilder.greaterThanOrEqualTo(calculateRevenue, minimumRevenue));

            TypedQuery<TransportCompanyRevenueDto> query = session.createQuery(criteriaQuery);
            filteredByRevenue = query.getResultList();
        }
        return filteredByRevenue;
    }

    @Override
    public List<TransportCompanyRevenueDto> filterByRevenueLessOrEqualTo(BigDecimal minimumRevenue) {
        List<TransportCompanyRevenueDto> filteredByRevenue;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyRevenueDto> criteriaQuery = criteriaBuilder.createQuery(TransportCompanyRevenueDto.class);

            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);
            Join<Transport, TransportCompany> transportCompanyJoin = transportRoot.join("transportCompany");

            Expression<BigDecimal> calculateRevenue =
                    criteriaBuilder.sum(transportRoot.get("cost"));

            criteriaQuery.select(
                            criteriaBuilder.construct(
                                    TransportCompanyRevenueDto.class,
                                    transportCompanyJoin.get("id"),
                                    transportCompanyJoin.get("name"),
                                    calculateRevenue
                            )
                    ).groupBy(transportCompanyJoin.get("id"))
                    .having(criteriaBuilder.lessThanOrEqualTo(calculateRevenue, minimumRevenue));

            TypedQuery<TransportCompanyRevenueDto> query = session.createQuery(criteriaQuery);
            filteredByRevenue = query.getResultList();
        }
        return filteredByRevenue;
    }

    @Override
    public Long totalNumberOfCarriedOutTransports(long transportCompanyId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);

            criteriaQuery.select(criteriaBuilder.count(transportRoot))
                    .where(criteriaBuilder.equal(transportRoot.get("transportCompany")
                            .get("id"), transportCompanyId));

            TypedQuery<Long> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        }
    }

    @Override
    public BigDecimal totalRevenueOfCarriedOutTransports(long transportCompanyId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);

            Root<Transport> transportRoot = criteriaQuery.from(Transport.class);
            Expression<BigDecimal> calculateRevenueFromTransports = criteriaBuilder
                    .sum(transportRoot.get("cost"));

            criteriaQuery.select(calculateRevenueFromTransports)
                    .where(criteriaBuilder.equal(transportRoot.get("transportCompany")
                            .get("id"), transportCompanyId));

            TypedQuery<BigDecimal> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        }
    }

    //TODO Refactor | Not working properly
    @Override
    public List<EmployeeTransportCountDto> totalNumberOfTransportsByDrivers(long transportCompanyId) {
        List<EmployeeTransportCountDto> carriedOutTransportsByEmployee;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeTransportCountDto> criteriaQuery = criteriaBuilder.createQuery(EmployeeTransportCountDto.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            Join<Employee, Transport> employeeTransportJoin = employeeRoot.join("handledTransports", JoinType.LEFT);

            Predicate transportCompanyIdEquals = criteriaBuilder
                    .equal(employeeRoot.get("transportCompany").get("id"), transportCompanyId);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            EmployeeTransportCountDto.class,
                            employeeRoot.get("id"),
                            employeeRoot.get("firstName"),
                            employeeRoot.get("lastName"),
                            employeeRoot.get("transportCompany").get("name"),
                            criteriaBuilder.count(employeeTransportJoin.get("id"))
                    )
            ).where(transportCompanyIdEquals)
                    .groupBy(
                            employeeRoot.get("id"),
                            employeeRoot.get("firstName"),
                            employeeRoot.get("lastName"),
                            employeeRoot.get("transportCompany").get("name")
                    );

            TypedQuery<EmployeeTransportCountDto> query = session.createQuery(criteriaQuery);
            carriedOutTransportsByEmployee = query.getResultList();
        }
        return carriedOutTransportsByEmployee;
    }

    @Override
    public List<TransportCompanyEmployeeIncomeDto> calculateEmployeeIncomeByCompany(long transportCompanyId) {
        List<TransportCompanyEmployeeIncomeDto> employeeIncome;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompanyEmployeeIncomeDto> criteriaQuery = criteriaBuilder
                    .createQuery(TransportCompanyEmployeeIncomeDto.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            Join<Employee, Transport> employeeTransportJoin = employeeRoot.join("handledTransports", JoinType.LEFT);

            Expression<BigDecimal> sumIncome = criteriaBuilder.sum(
                    employeeTransportJoin.get("cost"));

            criteriaQuery.select(
                            criteriaBuilder.construct(
                                    TransportCompanyEmployeeIncomeDto.class,
                                    employeeRoot.get("id"),
                                    employeeRoot.get("firstName"),
                                    employeeRoot.get("lastName"),
                                    sumIncome
                            )
                    ).where(criteriaBuilder.equal(employeeRoot.get("transportCompany").get("id"), transportCompanyId))
                    .groupBy(employeeRoot.get("id"));

            TypedQuery<TransportCompanyEmployeeIncomeDto> query = session.createQuery(criteriaQuery);
            employeeIncome = query.getResultList();
        }
        return employeeIncome;
    }
}
