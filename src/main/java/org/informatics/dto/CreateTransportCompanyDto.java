package org.informatics.dto;

import java.time.LocalDate;

public class CreateTransportCompanyDto {
    private String name;
    private LocalDate foundationDate;

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

    public CreateTransportCompanyDto(String name, LocalDate foundationDate) {
        this.name = name;
        this.foundationDate = foundationDate;
    }

    @Override
    public String toString() {
        return "CreateTransportCompanyDto{" +
                "name='" + name + '\'' +
                ", foundationDate=" + foundationDate +
                '}';
    }
}
