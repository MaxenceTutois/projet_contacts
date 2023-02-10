package com.example.projet_contacts.repository;

import com.example.projet_contacts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmailAndMotDePasse(String email, String pwd);
}
