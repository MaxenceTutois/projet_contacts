package com.example.projet_contacts.controller;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.service.ContactService;
import com.example.projet_contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/list_contact")
    public String listContact(Model model){
        List<Contact> listContact = contactService.findAll();
        model.addAttribute("contacts", listContact);
        return "/list_contact";
    }
    @GetMapping("/add_contact")
    private String addContact (Model model){
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "/add_contact";
    }

    @PostMapping("/add_contact")
    private String addContact(@ModelAttribute Contact contact){
        contactService.save(contact);
        return "redirect:/list_contact";
    }

    @GetMapping("/contact/{id}")
    private String showContact(Model model, @PathVariable Optional<Long> id) {
        // TODO empêcher d'accéder à un contact d'un autre user via l'url
        // soit en récupérant le contact, et en vérifiant que son user est bien notre user actuel
        // soit par une recherche sur les deux champs id du contact et id du user du contact
        if (id.isEmpty())
            return "redirect:/list_contact";
        Optional<Contact> contact = contactService.findById(id.get());
        if (contact.isEmpty())
            return "redirect:/list_contact";
        model.addAttribute("contact", contact.get());
        return "contact";
    }

    @PostMapping("/contact/{id}")
    private String deleteContact(@PathVariable Long id) {
        // TODO empêcher de supprimer le contact d'un autre user via l'url
        contactService.deleteById(id);
        return "redirect:/list_contact";
    }
}