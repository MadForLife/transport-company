package org.informatics.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransportCompanyPeriodIncomeDTO {
    private Long id;
    private String companyName;
    private LocalDateTime lowerPeriod;
    private LocalDateTime upperPeriod;
    private BigDecimal income;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDateTime getLowerPeriod() {
        return lowerPeriod;
    }

    public void setLowerPeriod(LocalDateTime lowerPeriod) {
        this.lowerPeriod = lowerPeriod;
    }

    public LocalDateTime getUpperPeriod() {
        return upperPeriod;
    }

    public void setUpperPeriod(LocalDateTime upperPeriod) {
        this.upperPeriod = upperPeriod;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public TransportCompanyPeriodIncomeDTO(Long id, String companyName, LocalDateTime lowerPeriod, LocalDateTime upperPeriod, BigDecimal income) {
        this.id = id;
        this.companyName = companyName;
        this.lowerPeriod = lowerPeriod;
        this.upperPeriod = upperPeriod;
        this.income = income;
    }

    @Override
    public String toString() {
        return "TransportCompanyPeriodIncomeDTO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", lowerPeriod=" + lowerPeriod +
                ", upperPeriod=" + upperPeriod +
                ", income=" + income +
                '}';
    }
}
