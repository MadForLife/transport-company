package org.informatics.dao.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.informatics.dao.EmployeeDao;
import org.informatics.dto.EmployeeQualificationDto;
import org.informatics.dto.EmployeeSalaryDto;
import org.informatics.entity.Employee;
import org.informatics.entity.Qualification;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<EmployeeQualificationDto> sortByQualificationASC() {
        List<EmployeeQualificationDto> sortedByQualification;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeQualificationDto> criteriaQuery = criteriaBuilder.createQuery(EmployeeQualificationDto.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            Join<Employee, Qualification> employeeQualificationJoin = employeeRoot.join("qualifications", JoinType.LEFT);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            EmployeeQualificationDto.class,
                            employeeRoot.get("id"),
                            employeeRoot.get("firstName"),
                            employeeRoot.get("lastName"),
                            employeeQualificationJoin
                    )
            ).orderBy(criteriaBuilder.asc(employeeQualificationJoin.get("signature")));

            TypedQuery<EmployeeQualificationDto> query = session.createQuery(criteriaQuery);
            sortedByQualification = query.getResultList();
        }
        return sortedByQualification;
    }

    @Override
    public List<EmployeeQualificationDto> filterByQualificationASC(String qualificationSignature) {
        List<EmployeeQualificationDto> filteredByQualification;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeQualificationDto> criteriaQuery = criteriaBuilder.createQuery(EmployeeQualificationDto.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            Join<Employee, Qualification> employeeQualificationJoin = employeeRoot.join("qualifications", JoinType.LEFT);

            Predicate signatureLike = criteriaBuilder
                    .like(employeeQualificationJoin.get("signature"), "%" + qualificationSignature + "%");

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            EmployeeQualificationDto.class,
                            employeeRoot.get("id"),
                            employeeRoot.get("firstName"),
                            employeeRoot.get("lastName"),
                            employeeQualificationJoin
                    )
            ).where(criteriaBuilder.and(signatureLike))
                    .orderBy(criteriaBuilder.asc(employeeQualificationJoin.get("signature")));

            TypedQuery<EmployeeQualificationDto> query = session.createQuery(criteriaQuery);
            filteredByQualification = query.getResultList();
        }
        return filteredByQualification;
    }

    @Override
    public List<EmployeeSalaryDto> sortBySalaryASC() {
        List<EmployeeSalaryDto> sortedBySalary;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<EmployeeSalaryDto> criteriaQuery = criteriaBuilder.createQuery(EmployeeSalaryDto.class);
            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            EmployeeSalaryDto.class,
                            employeeRoot.get("id"),
                            employeeRoot.get("firstName"),
                            employeeRoot.get("lastName"),
                            employeeRoot.get("employeeSalary")
                    )
            ).orderBy(criteriaBuilder.asc(employeeRoot.get("employeeSalary")));

            TypedQuery<EmployeeSalaryDto> query = session.createQuery(criteriaQuery);
            sortedBySalary = query.getResultList();
        }
        return sortedBySalary;
    }

    @Override
    public List<EmployeeSalaryDto> filterBySalaryGreaterOrEqualTo(BigDecimal minimumSalary) {
        List<EmployeeSalaryDto> filteredBySalary;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<EmployeeSalaryDto> criteriaQuery = criteriaBuilder.createQuery(EmployeeSalaryDto.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            Predicate salaryLessOrEqualTo = criteriaBuilder
                    .greaterThanOrEqualTo(employeeRoot.get("employeeSalary"), minimumSalary);

            criteriaQuery.select(
                    criteriaBuilder.construct(
                            EmployeeSalaryDto.class,
                            employeeRoot.get("id"),
                            employeeRoot.get("firstName"),
                            employeeRoot.get("lastName"),
                            employeeRoot.get("employeeSalary")
                    )
            ).where(criteriaBuilder.and(salaryLessOrEqualTo))
                    .orderBy(criteriaBuilder.asc(employeeRoot.get("employeeSalary")));

            TypedQuery<EmployeeSalaryDto> query = session.createQuery(criteriaQuery);
            filteredBySalary = query.getResultList();
        }
        return filteredBySalary;
    }

    @Override
    public List<EmployeeSalaryDto> filterBySalaryLessOrEqualTo(BigDecimal minimumSalary) {
        List<EmployeeSalaryDto> filteredBySalary;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<EmployeeSalaryDto> criteriaQuery = criteriaBuilder.createQuery(EmployeeSalaryDto.class);

            Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
            Predicate salaryLessOrEqualTo = criteriaBuilder
                    .lessThanOrEqualTo(employeeRoot.get("employeeSalary"), minimumSalary);

            criteriaQuery.select(
                            criteriaBuilder.construct(
                                    EmployeeSalaryDto.class,
                                    employeeRoot.get("id"),
                                    employeeRoot.get("firstName"),
                                    employeeRoot.get("lastName"),
                                    employeeRoot.get("employeeSalary")
                            )
                    ).where(criteriaBuilder.and(salaryLessOrEqualTo))
                    .orderBy(criteriaBuilder.asc(employeeRoot.get("employeeSalary")));

            TypedQuery<EmployeeSalaryDto> query = session.createQuery(criteriaQuery);
            filteredBySalary = query.getResultList();
        }
        return filteredBySalary;
    }
}
