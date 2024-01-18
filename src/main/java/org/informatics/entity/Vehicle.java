package org.informatics.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_license_plate", length = 10, nullable = false)
    private String licensePlate;

    @Column(name = "vehicle_seating_capacity", nullable = false)
    private Short seatingCapacity;

    @Column(name = "vehicle_cargo_capacity", precision = 8, scale = 2, nullable = false)
    private BigDecimal cargoCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_type_id", nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "required_qualification_id", nullable = false)
    private Qualification requiredQualification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_company_id", nullable = false)
    private TransportCompany transportCompany;

    @OneToMany(mappedBy = "assignedVehicle")
    private Set<Transport> carriedOutTransports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Short getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Short seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public BigDecimal getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(BigDecimal cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Qualification getRequiredQualification() {
        return requiredQualification;
    }

    public void setRequiredQualification(Qualification requiredQualification) {
        this.requiredQualification = requiredQualification;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Vehicle() {  }

    public Vehicle(String licensePlate, Short seatingCapacity, BigDecimal cargoCapacity, VehicleType vehicleType, Qualification requiredQualification, TransportCompany transportCompany) {
        this.licensePlate = licensePlate;
        this.seatingCapacity = seatingCapacity;
        this.cargoCapacity = cargoCapacity;
        this.vehicleType = vehicleType;
        this.requiredQualification = requiredQualification;
        this.transportCompany = transportCompany;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", cargoCapacity=" + cargoCapacity +
                ", vehicleType=" + vehicleType +
                ", requiredQualification=" + requiredQualification +
                ", transportCompany=" + transportCompany +
                '}';
    }
}
