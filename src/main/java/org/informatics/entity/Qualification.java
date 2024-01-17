package org.informatics.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @Column(name = "qualification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qualification_signature", length = 10, nullable = false)
    private String signature;

    @Column(name = "qualification_description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "requiredQualification")
    private Set<Vehicle> vehicles;

    @ManyToMany(mappedBy = "qualifications")
    private Set<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Qualification() {  }

    public Qualification(String signature, String description) {
        this.signature = signature;
        this.description = description;
    }
}
