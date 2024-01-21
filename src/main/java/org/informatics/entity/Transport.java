package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.metamodel.mapping.EntityAssociationMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transport")
public class Transport {

    @Id
    @Column(name = "transport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Starting point cannot be blank!")
    @Pattern(regexp = "^[A-Z].*", message = "Starting point has to start with capital letter!")
    @Column(name = "transport_start_point", length = 100, nullable = false)
    private String startPoint;

    @NotBlank(message = "End point cannot be blank!")
    @Pattern(regexp = "^[A-Z].*", message = "Ending point has to start with capital letter!")
    @Column(name = "transport_end_point", length = 100, nullable = false)
    private String endPoint;

    @FutureOrPresent(message = "Departure date cannot be in the past")
    @Column(name = "transport_departure_date", nullable = false)
    private LocalDateTime departureDate;


    @FutureOrPresent(message = "Arrival date cannot be in the past")
    @Column(name = "transport_arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @Positive
    @DecimalMin(value = "0.00", message = "Cost has to be more than or equal to 0.00")
    @Column(name = "transport_cost", precision = 8, scale = 2, nullable = false)
    private BigDecimal cost;

    @Positive
    @DecimalMin(value = "0.00", message = "Cargo weight has to be more than or equal to 0.00")
    @Column(name = "transport_cargo_weight", precision = 8, scale = 2, nullable = false)
    private BigDecimal cargoWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id", nullable = false)
    private Employee assignedEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_vehicle_id", nullable = false)
    private Vehicle assignedVehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_type_id", nullable = false)
    private TransportType transportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id", nullable = false)
    private TransportCompany transportCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
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

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(BigDecimal cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public Vehicle getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(Vehicle assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Transport() {  }

    public Transport(String startPoint, String endPoint, LocalDateTime departureDate, LocalDateTime arrivalDate, BigDecimal cost, BigDecimal cargoWeight, Employee assignedEmployee, Vehicle assignedVehicle, TransportType transportType, TransportCompany transportCompany) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.cost = cost;
        this.cargoWeight = cargoWeight;
        this.assignedEmployee = assignedEmployee;
        this.assignedVehicle = assignedVehicle;
        this.transportType = transportType;
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", cost=" + cost +
                ", cargoWeight=" + cargoWeight +
                ", assignedEmployee=" + assignedEmployee +
                ", assignedVehicle=" + assignedVehicle +
                ", transportType=" + transportType +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
