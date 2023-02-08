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

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "/register_user";
    }

    @PostMapping("/register")
    public String addUser(User user){
        userService.save(user);
        return "redirect:/list_user";
    }

}
