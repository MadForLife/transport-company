package org.informatics.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransportDestinationDto {
    private Long id;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public TransportDestinationDto(Long id, String destination, LocalDateTime departureDate, LocalDateTime arrivalDate, BigDecimal cost) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TransportDestinationDto{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", cost=" + cost +
                '}';
    }
}
