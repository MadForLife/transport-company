package org.informatics.dto;

import java.math.BigDecimal;

public class TransportCompanyEmployeeIncomeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal totalIncome;

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

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public TransportCompanyEmployeeIncomeDto(Long id, String firstName, String lastName, BigDecimal totalIncome) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "TransportCompanyEmployeeIncomeDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalIncome=" + totalIncome +
                '}';
    }
}
