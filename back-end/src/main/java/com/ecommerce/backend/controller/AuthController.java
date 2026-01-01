package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passEncoder;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setRole(Role.USER);
        user.setPassword(passEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User request) {

        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        
        String token =jwtService.generateToken(user.getEmail());

        return Map.of(
            "token", token,
            "role", user.getRole().name()
        );
    }
}