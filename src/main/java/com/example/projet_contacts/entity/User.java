package com.example.projet_contacts.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String firstName;

    private String lastName;

    private String email;

    private String img;

    private String motDePasse;

    @OneToMany
    private List<Contact> contacts;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, String img, String motDePasse, List<Contact> contacts) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.img = img;
        this.motDePasse = motDePasse;
        this.contacts = contacts;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
