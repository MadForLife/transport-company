package org.informatics.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "transport_type")
public class TransportType {

    @Id
    @Column(name = "transport_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transport_type", length = 20, nullable = false)
    private String type;

    @Column(name = "transport_type_description", length = 200, nullable = false)
    private String description;

    @OneToMany(mappedBy = "transportType")
    private Set<Transport> transports;

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

    public TransportType() {  }

    public TransportType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransportType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
