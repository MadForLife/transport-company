package org.informatics.entity;

import jakarta.persistence.*;
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

    @Column(name = "transport_start_point", length = 100, nullable = false)
    private String startPoint;

    @Column(name = "transport_end_point", length = 100, nullable = false)
    private String endPoint;

    @Column(name = "transport_departure_date", nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "transport_arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @Column(name = "transport_cost", precision = 8, scale = 2, nullable = false)
    private BigDecimal cost;

    @Column(name = "transport_cargo_weight", precision = 8, scale = 2, nullable = false)
    private BigDecimal cargoWeight;

    @ManyToOne
    @JoinColumn(name = "assigned_employee_id", nullable = false)
    private Employee assignedEmployee;

    @ManyToOne
    @JoinColumn(name = "assigned_vehicle_id", nullable = false)
    private Vehicle assignedVehicle;

    @ManyToOne
    @JoinColumn(name = "transport_type_id", nullable = false)
    private TransportType transportType;

    @ManyToOne
    @JoinColumn(name = "transport_company_id", nullable = false)
    private TransportCompany transportCompany;
}
