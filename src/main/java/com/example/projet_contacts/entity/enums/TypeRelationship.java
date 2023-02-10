package com.example.projet_contacts.entity.enums;

public enum TypeRelationship {

    MOTHER("mother"),
    FATHER("father"),
    PARENT("parent"),
    DAUGHTER("daughter"),
    SON("son"),
    CHILD("child"),
    WIFE("wife"),
    HUSBAND("husband"),
    SPOUSE("spouse"),
    SISTER("sister"),
    BROTHER("brother"),
    SIBLING("sibling");

    private final String value;

    private TypeRelationship(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
