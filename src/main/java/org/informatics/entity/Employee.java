package org.informatics.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends Person {

    @Column(name = "employee_salary", precision = 8, scale = 2, nullable = false)
    private BigDecimal employeeSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id", nullable = false)
    private TransportCompany transportCompany;

    @OneToMany(mappedBy = "assignedEmployee")
    private Set<Transport> handledTransports;

    @ManyToMany
    @JoinTable(
            name = "employee_has_qualification",
            joinColumns = @JoinColumn(name = "employee_employee_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_qualification_id")
    )
    private Set<Qualification> qualifications;

    public BigDecimal getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(BigDecimal employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public Employee() {  }

    public Employee(String firstName, String lastName, LocalDate birthDate, BigDecimal employeeSalary, TransportCompany transportCompany) {
        super(firstName, lastName, birthDate);
        this.employeeSalary = employeeSalary;
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeSalary=" + employeeSalary +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
