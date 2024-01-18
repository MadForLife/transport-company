package org.informatics.dto;

import java.time.LocalDate;

public class ClientDto {

    private long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String address;
    private TransportCompanyDto transportCompanyDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TransportCompanyDto getTransportCompanyDto() {
        return transportCompanyDto;
    }

    public void setTransportCompanyDto(TransportCompanyDto transportCompanyDto) {
        this.transportCompanyDto = transportCompanyDto;
    }

    public ClientDto(long id, String firstname, String lastname, LocalDate birthDate, String address, TransportCompanyDto transportCompanyDto) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.address = address;
        this.transportCompanyDto = transportCompanyDto;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", transportCompanyDto=" + transportCompanyDto +
                '}';
    }
}
