package com.example.projet_contacts.controller;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.service.ContactService;
import com.example.projet_contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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



}