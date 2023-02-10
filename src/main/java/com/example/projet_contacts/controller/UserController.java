package com.example.projet_contacts.controller;

import com.example.projet_contacts.entity.User;
import com.example.projet_contacts.repository.UserRepository;
import com.example.projet_contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model){
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
    private String listUser(Model model){
        List<User> listUser = userService.findAll();
        model.addAttribute("users", listUser);
        return "/list_user";
    }

    @GetMapping("/add_user")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "/register_user";
    }

    @PostMapping("/add_user")
    public String addUser(User user){
        userService.save(user);
        return "redirect:/list_contact";
    }

}
