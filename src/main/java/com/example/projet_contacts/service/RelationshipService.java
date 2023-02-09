package com.example.projet_contacts.service;

import com.example.projet_contacts.entity.Relationship;
import com.example.projet_contacts.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationshipService {
    @Autowired
    private RelationshipRepository relationshipRepository;

    public List<Relationship> findAllRelationshipsHavingId(Long contactId) {
        return relationshipRepository.findAllByOwnerIdOrTargetId(contactId, contactId);
    }

    public void deleteAllRelationshipsHavingId(Long contactId) {
        relationshipRepository.deleteAllByOwnerIdOrTargetId(contactId, contactId);
    }
}
