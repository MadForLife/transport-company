package org.informatics.dto;

import org.informatics.entity.Qualification;

public class EmployeeQualificationDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Qualification qualification;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public EmployeeQualificationDto(Long id, String firstName, String lastName, Qualification qualification) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "EmployeeQualificationDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", qualification=" + qualification +
                '}';
    }
}
