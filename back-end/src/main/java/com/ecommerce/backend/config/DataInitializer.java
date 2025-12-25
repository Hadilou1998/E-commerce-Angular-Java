package com.ecommerce.backend.config;

import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers(UserRepository userRepo) {
        return args -> {
            if (userRepo.findByEmail("john.doe@example.com").isEmpty()) {
                User user = new User();
                user.setFirstname("John");
                user.setLastname("Doe");
                user.setEmail("john.doe@example.com");
                user.setPassword(passwordEncoder.encode("password123"));
                user.setRole(Role.USER);
                userRepo.save(user);
            }
        };
    }
}