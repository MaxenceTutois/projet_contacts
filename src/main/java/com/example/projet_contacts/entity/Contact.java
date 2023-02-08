package com.example.projet_contacts.entity;

import com.example.projet_contacts.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String picture;
    private Gender gender;
    @ManyToMany
    private List<Relationship> relationships;

    public Contact() {}

    public Contact(Long id, String firstName, String lastName, LocalDate birthDate, String picture, Gender gender, List<Relationship> relationships) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.picture = picture;
        this.gender = gender;
        this.relationships = relationships;
    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Relationship> getRelationShips() {
        return relationships;
    }

    public void setRelationShips(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
