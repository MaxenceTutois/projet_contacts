package com.example.projet_contacts.repository;

import com.example.projet_contacts.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    List<Relationship> findAllByOwnerIdOrTargetId(Long ownerId, Long targetId);

    List<Relationship> findAllByTargetId(Long targetId);

    void deleteAllByOwnerIdOrTargetId(Long ownerId, Long targetId);
}
