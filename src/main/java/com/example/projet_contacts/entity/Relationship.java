package com.example.projet_contacts.entity;

import com.example.projet_contacts.entity.enums.TypeRelationship;
import jakarta.persistence.*;

@Entity
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Contact owner;
    @ManyToOne
    private Contact target;
    private TypeRelationship typeRelationship;

    public Relationship() {}

    public Relationship(Long id, Contact owner, Contact target, TypeRelationship typeRelationship) {
        this.id = id;
        this.owner = owner;
        this.target = target;
        this.typeRelationship = typeRelationship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getOwner() {
        return owner;
    }

    public void setOwner(Contact owner) {
        this.owner = owner;
    }

    public Contact getTarget() {
        return target;
    }

    public void setTarget(Contact target) {
        this.target = target;
    }

    public TypeRelationship getTypeRelationship() {
        return typeRelationship;
    }

    public void setTypeRelationship(TypeRelationship typeRelationship) {
        this.typeRelationship = typeRelationship;
    }
}
