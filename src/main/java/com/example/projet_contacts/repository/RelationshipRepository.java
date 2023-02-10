package com.example.projet_contacts.repository;

import com.example.projet_contacts.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    List<Relationship> findAllByTargetId(Long targetId);
    List<Relationship> findAllByOwnerIdOrTargetId(Long ownerId, Long targetId);
    Optional<Relationship> findFirstByOwnerIdAndTargetId(Long ownerId, Long targetId);
}
