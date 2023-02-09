package com.example.projet_contacts.entity.enums;

public enum TypeRelationship {
    PARENT("parent", "mother", "father"),
    CHILD("child", "daughter", "son"),
    SIBLING("sibling", "sister", "brother"),
    MARRIED("married to", "wife", "husband");

    private final String sIfUndefined;
    private final String sIfWoman;
    private final String sIfMan;

    private TypeRelationship(String sIfUndefined, String sIfWoman, String sIfMan) {
        this.sIfUndefined = sIfUndefined;
        this.sIfWoman = sIfWoman;
        this.sIfMan = sIfMan;
    }

    public String getValue(Gender gender) {
        switch (gender) {
            case UNDEFINED: return sIfUndefined;
            case FEMALE: return sIfWoman;
            case MALE: return sIfMan;
            default: return null;
        }
    }
}
