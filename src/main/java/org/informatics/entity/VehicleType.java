package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.Set;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {

    @Id
    @Column(name = "vehicle_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vehicle type cannot be blank!")
    @Pattern(regexp = "[A-Z0-9]*", message = "Vehicle type has to be all capital letters!")
    @Column(name = "vehicle_type", length = 20, nullable = false)
    private String type;


    @NotBlank(message = "Vehicle description cannot be blank!")
    @Pattern(regexp = "^[A-Z].*", message = "Vehicle description has to start with capital letter!")
    @Column(name = "vehicle_type_description", length = 200, nullable = false)
    private String description;

    @OneToMany(mappedBy = "vehicleType")
    private Set<Vehicle> vehicles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VehicleType() {  }

    public VehicleType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
