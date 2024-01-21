package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends Person {

    @NotBlank(message = "Client address cannot be blank!")
    @Size(min = 6, message = "Client address must be at least 6 characters")
    @Column(name = "client_address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id", nullable = false)
    private TransportCompany transportCompany;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Client() { }

    public Client(String firstName, String lastName, LocalDate birthDate, String address, TransportCompany transportCompany) {
        super(firstName, lastName, birthDate);
        this.address = address;
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Client{" +
                "address='" + address + '\'' +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
