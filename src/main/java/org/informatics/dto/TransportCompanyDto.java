package org.informatics.dto;

import java.time.LocalDate;

public class TransportCompanyDto {
    private Long id;
    private String name;
    private LocalDate foundationDate;

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

    public TransportCompanyDto(Long id, String name, LocalDate foundationDate) {
        this.id = id;
        this.name = name;
        this.foundationDate = foundationDate;
    }

    @Override
    public String toString() {
        return "TransportCompanyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundationDate=" + foundationDate +
                '}';
    }
}
