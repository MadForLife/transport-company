package org.informatics.dto;

public class EmployeeTransportCountDto {
    private Long id;
    private String firstName;
    private String lastname;
    private String transportCompany;
    private Long totalTransports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(String transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Long getTotalTransports() {
        return totalTransports;
    }

    public void setTotalTransports(Long totalTransports) {
        this.totalTransports = totalTransports;
    }

    public EmployeeTransportCountDto(Long id, String firstName, String lastname, String transportCompany, Long totalTransports) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.transportCompany = transportCompany;
        this.totalTransports = totalTransports;
    }

    @Override
    public String toString() {
        return "EmployeeTransportCountDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", transportCompany='" + transportCompany + '\'' +
                ", totalTransports=" + totalTransports +
                '}';
    }
}
