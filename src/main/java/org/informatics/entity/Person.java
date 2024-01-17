package org.informatics.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_first_name", nullable = false)
    private String firstName;

    @Column(name = "person_last_name", nullable = false)
    private String lastName;

    @Column(name = "person_birth_date", nullable = false)
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Person() {  }

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
