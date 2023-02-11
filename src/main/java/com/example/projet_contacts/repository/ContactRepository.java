package com.example.projet_contacts.repository;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    List<Contact> findAllByIdNot(Long id);
}
