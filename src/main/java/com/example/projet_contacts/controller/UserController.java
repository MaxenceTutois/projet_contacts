package com.example.projet_contacts.controller;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.entity.Relationship;
import com.example.projet_contacts.entity.User;
import com.example.projet_contacts.repository.UserRepository;
import com.example.projet_contacts.service.RelationshipService;
import com.example.projet_contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RelationshipService relationshipService;

    @GetMapping("/home")
    public String home(Model model) {
        return "/home";
    }

    @PostMapping("/home")
    public String login(@RequestParam String email, @RequestParam String password){
        Optional<User> optUser = userService.findByEmailAndPassword(email, password);
        if (optUser.isPresent()) {
            return "redirect:/list_contact";
        }
        return "/home";
    }

    @GetMapping("/list_user")
    private String listUser(Model model) {
        List<User> listUser = userService.findAll();
        model.addAttribute("users", listUser);
        return "/list_user";
    }

    @GetMapping("/add_user")
    public String addUser(Model model, @RequestParam Optional<Long> id) {
        User user;
        if (id.isPresent()) {
            Optional<User> optionalContact = userService.findById(id.get());
            user = optionalContact.isPresent() ? optionalContact.get() : new User();

        } else {
            user = new User();
        }
        model.addAttribute("user", user);
        return "register_user";
    }

    @PostMapping("/add_user")
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/list_contact";
    }

    @PostMapping("/user/{id}")
    private String deleteUser(@PathVariable Long id) {
        // TODO empêcher de supprimer le contact d'un autre user via l'url
        userService.deleteById(id);
        return "redirect:/list_user";
    }

}
