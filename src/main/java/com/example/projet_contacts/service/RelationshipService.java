package com.example.projet_contacts.service;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.entity.Relationship;
import com.example.projet_contacts.entity.enums.Gender;
import com.example.projet_contacts.entity.enums.TypeRelationship;
import com.example.projet_contacts.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RelationshipService {
    @Autowired
    private RelationshipRepository relationshipRepository;
    @Autowired
    private ContactService contactService;

    public List<Relationship> findAllRelationshipsHavingId(Long contactId) {
        return relationshipRepository.findAllByOwnerIdOrTargetId(contactId, contactId);
    }

    public List<Relationship> findAllByTargetId(Long targetId) {
        return relationshipRepository.findAllByTargetId(targetId);
    }

    public void deleteAllRelationshipsHavingId(Long contactId) {
        relationshipRepository.deleteAllByOwnerIdOrTargetId(contactId, contactId);
    }

    public void setRelationship(Long ownerId, Long targetId, TypeRelationship tRelationship) {
        Optional<Contact> optOwner = contactService.findById(ownerId);
        Optional<Contact> optTarget = contactService.findById(targetId);

        if (optOwner.isEmpty() || optTarget.isEmpty())
            return;

        Contact target = optTarget.get();

        Relationship rShip = new Relationship();
        rShip.setOwner(optOwner.get());
        rShip.setTarget(optTarget.get());
        rShip.setTypeRelationship(tRelationship);

        Relationship rShipMirror = new Relationship();
        rShipMirror.setOwner(target);
        rShipMirror.setTarget(optOwner.get());
        TypeRelationship tMirror;
        switch (tRelationship) {
            case MOTHER:
            case FATHER:
            case PARENT:
                switch (target.getGender()) {
                    case FEMALE: tMirror = TypeRelationship.DAUGHTER; break;
                    case MALE: tMirror = TypeRelationship.SON; break;
                    default: tMirror = TypeRelationship.CHILD; break;
                }
                break;
            case DAUGHTER:
            case SON:
            case CHILD:
                switch (target.getGender()) {
                    case FEMALE: tMirror = TypeRelationship.MOTHER; break;
                    case MALE: tMirror = TypeRelationship.FATHER; break;
                    default: tMirror = TypeRelationship.PARENT; break;
                }
                break;
            case WIFE:
            case HUSBAND:
            case SPOUSE:
                switch (target.getGender()) {
                    case FEMALE: tMirror = TypeRelationship.WIFE; break;
                    case MALE: tMirror = TypeRelationship.HUSBAND; break;
                    default: tMirror = TypeRelationship.SPOUSE; break;
                }
                break;
            case SISTER:
            case BROTHER:
            case SIBLING:
                switch (target.getGender()) {
                    case FEMALE: tMirror = TypeRelationship.SISTER; break;
                    case MALE: tMirror = TypeRelationship.BROTHER; break;
                    default: tMirror = TypeRelationship.SIBLING; break;
                }
                break;
            default:
                return;
        }
        rShipMirror.setTypeRelationship(tMirror);

        relationshipRepository.save(rShip);
        relationshipRepository.save(rShipMirror);
    }
}
