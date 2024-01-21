package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends Person {

    @Positive
    @DecimalMin(value = "1000.00", message = "Salary has to be more than or equal to 1000.00!")
    @DecimalMax(value = "5000.00", message = "Salary has to be less than or equal to 5000.00!")
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
