package com.example.projet_contacts.service;

import com.example.projet_contacts.entity.Contact;
import com.example.projet_contacts.entity.User;
import com.example.projet_contacts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public void save(User user){
        userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public List<User> findAll(){
        List<User> listUser = userRepository.findAll();
        return listUser;
    }

    public void update(User user){
        userRepository.save(user);
    }
}


