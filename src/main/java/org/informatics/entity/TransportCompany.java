package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "transport_company")
public class TransportCompany {

    @Id
    @Column(name = "transport_company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Transport Company name cannot be blank!")
    @Pattern(regexp = "^[A-Z].*", message = "Transport Company name has to start with capital letter!")
    @Column(name = "transport_company_name", nullable = false)
    private String name;

    @PastOrPresent(message = "Foundation date cannot be in the future")
    @Column(name = "transport_company_foundation_date", nullable = false)
    private LocalDate foundationDate;

    @OneToMany(mappedBy = "transportCompany")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "transportCompany")
    private Set<Client> clients;

    @OneToMany(mappedBy = "transportCompany")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "transportCompany")
    private Set<Transport> transports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public TransportCompany() {  }

    public TransportCompany(String name, LocalDate foundationDate) {
        this.name = name;
        this.foundationDate = foundationDate;
    }

    @Override
    public String toString() {
        return "TransportCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundationDate=" + foundationDate +
                '}';
    }
}
