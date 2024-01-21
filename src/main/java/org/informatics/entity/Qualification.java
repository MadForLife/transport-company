package org.informatics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @Column(name = "qualification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Qualification signature cannot beblank!")
    @Pattern(regexp = "[A-Z0-9]*", message = "Qualification signature has to be with capital letters only!")
    @Column(name = "qualification_signature", length = 10, nullable = false)
    private String signature;

    @NotBlank(message = "Qualification description cannot be blank!")
    @Pattern(regexp = "^[A-Z].*", message = "Qualification description has to start with a capital letter!")
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

    @Override
    public String toString() {
        return "Qualification{" +
                "id=" + id +
                ", signature='" + signature + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
