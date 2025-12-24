package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Créer un utilisateur
    @PostMapping
    public User create(@RequestBody User user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    // Liste des utilisateurs
    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Détail utilisateur
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}