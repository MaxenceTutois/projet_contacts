package com.example.projet_contacts.repository;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByNameContaining (String firstName);
}
