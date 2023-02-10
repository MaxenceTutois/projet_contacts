package com.example.projet_contacts.controller;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.entity.Relationship;
import com.example.projet_contacts.entity.User;
import com.example.projet_contacts.entity.enums.TypeRelationship;
import com.example.projet_contacts.service.ContactService;
import com.example.projet_contacts.service.RelationshipService;
import com.example.projet_contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private RelationshipService relationshipService;

    @GetMapping("/list_contact")
    public String listContact(Model model, @RequestParam Optional<String> search) {

        List<Contact> contacts;
        User users = new User();
        if (search.isEmpty() || search.get().length() == 0)
            contacts = contactService.findAll();
        else
            contacts = contactService.searchContact(search.get());
        model.addAttribute("contacts", contacts);
        model.addAttribute("user", users);
        return "/list_contact";
    }

    @GetMapping("/add_contact")
    private String addContact(Model model, @RequestParam Optional<Long> id) {
        Contact contact;
        if (id.isPresent()) {
            Optional<Contact> optionalContact = contactService.findById(id.get());
            contact = optionalContact.isPresent() ? optionalContact.get() : new Contact();

        } else {
            contact = new Contact();
        }
        model.addAttribute("contact", contact);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "register_contact";
    }

    @PostMapping("/add_contact")
    private String addContact(@ModelAttribute Contact contact, @RequestParam Optional<Long> userId) {
        if (userId.isPresent()) {
            Optional<User> user = userService.findById(userId.get());
            if (user.isPresent()) {
                contact.setUser(user.get());
            }
        }
        contactService.save(contact);
        return "redirect:/list_contact";
    }

    //
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
        List<Relationship> relationships = relationshipService.findAllByTargetId(contact.get().getId());
        model.addAttribute("contact", contact.get());
        model.addAttribute("relationships", relationships);
        return "contact";
    }

    @PostMapping("/contact/{id}")
    private String deleteContact(@PathVariable Long id) {
        // TODO empêcher de supprimer le contact d'un autre user via l'url
        relationshipService.deleteAllRelationshipsForContact(id);
        contactService.deleteById(id);
        return "redirect:/list_contact";
    }

    @PostMapping("/contact/{id}/{delOwner}/{delTarget}")
    private String deleteRelationShip(Model model, @PathVariable Long id, @PathVariable Long delOwner, @PathVariable Long delTarget) {
        if (contactService.findById(delOwner).isPresent() && contactService.findById(delTarget).isPresent())
            relationshipService.deleteBothSidesOfRelationship(delOwner, delTarget);

        model.addAttribute("id", id);
        return "redirect:/contact/{id}";
    }

    @GetMapping("/relation_contact/{id}")
    private String showContactsForRelationship(Model model, @PathVariable Long id) {
        Optional<Contact> contact = contactService.findById(id);
        if (contact.isEmpty())
            return "redirect:/list_contact";

        model.addAttribute("contact", contact.get());
        model.addAttribute("contacts", contactService.findAll());

        return "relation_contact";
    }

    @GetMapping("/relation_pick")
    private String showTypesForRelationship(Model model, @RequestParam Long ownerId, @RequestParam Long targetId) {
        //TODO empêcher contacts d'autres users
        Optional<Contact> optOwner = contactService.findById(ownerId);
        Optional<Contact> optTarget = contactService.findById(targetId);
        if (optOwner.isEmpty() || optTarget.isEmpty())
            return "redirect:/list_contact";

        model.addAttribute("owner", optOwner.get());
        model.addAttribute("target", optTarget.get());

        return "/relation_pick";
    }

    @PostMapping("/relation_pick")
    private String selectTypeOfRelationship(Model model, @RequestParam Long ownerId, @RequestParam Long targetId, @RequestParam TypeRelationship tRelationship) {
        relationshipService.setRelationship(ownerId, targetId, tRelationship);

        // Ne fonctionne pas ici mais fonctionne dans deleteRelationship, pour une raison inconnue
        /*model.addAttribute("id", ownerId);
        return "redirect:/contact/{id}";*/

        return "redirect:/list_contact";
    }
}