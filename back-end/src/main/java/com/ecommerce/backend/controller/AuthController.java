package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User foundUser = userRepo.findByEmail(user.getEmail())
               .orElseThrow(() -> new RuntimeException("User not found"));
        if (!foundUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtService.generateToken(foundUser.getEmail());
    }
}