package com.example.projet_contacts.controller;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.repository.ContactRepository;
import com.example.projet_contacts.service.ContactService;
import com.example.projet_contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/list_contact")
    public String listContact(Model model, @RequestParam Optional<String> search) {

        List<Contact> contacts;
        if (search.isEmpty() || search.get().length() == 0)
            contacts = contactService.findAll();
        else
            contacts = contactService.searchContact(search.get());
        model.addAttribute("contacts", contacts);
        return "/list_contact";
    }

    @GetMapping("/add_contact")
    private String addContact(Model model,@RequestParam Optional<Long> id) {
        Contact contact;
        if (id.isPresent()) {
            Optional<Contact> optionalContact = contactService.findById(id.get());
            contact = optionalContact.isPresent() ? optionalContact.get() : new Contact();

        } else {
            contact = new Contact();
        }
        model.addAttribute("contact", contact);
        return "/add_contact";
    }

    @PostMapping("/add_contact")
    private String addContact(@ModelAttribute Contact contact) {
        contactService.save(contact);
        return "redirect:/list_contact";
    }


}