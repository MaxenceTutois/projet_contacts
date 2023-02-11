package com.example.projet_contacts.service;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public List<Contact> findAllButId(Long id) {
        return contactRepository.findAllByIdNot(id);
    }

    public List<Contact> searchContact(String search) {
        return contactRepository.findByFirstNameContainingOrLastNameContaining(search, search);
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteById(Long id) {
        // TODO supprimer les relations du contact avant de le supprimer
        contactRepository.deleteById(id);
    }
}
