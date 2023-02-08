package com.example.projet_contacts.entity;

import jakarta.persistence.*;

@Entity
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Contact contact1;
    @ManyToOne
    private Contact contact2;
    private TypeRelationship typeRelationship;

    public Relationship() {}

    public Relationship(Long id, Contact contact1, Contact contact2, TypeRelationship typeRelationship) {
        this.id = id;
        this.contact1 = contact1;
        this.contact2 = contact2;
        this.typeRelationship = typeRelationship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact1() {
        return contact1;
    }

    public void setContact1(Contact contact1) {
        this.contact1 = contact1;
    }

    public Contact getContact2() {
        return contact2;
    }

    public void setContact2(Contact contact2) {
        this.contact2 = contact2;
    }

    public TypeRelationship getTypeRelationship() {
        return typeRelationship;
    }

    public void setTypeRelationship(TypeRelationship typeRelationship) {
        this.typeRelationship = typeRelationship;
    }
}
