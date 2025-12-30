package com.ecommerce.backend.config;

import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        /* =========================
           USERS
        ========================= */
        if (userRepo.count() == 0) {
            userRepo.saveAll(List.of(
                createAdmin(),
                createUser("John", "Doe", "john.doe@shop.com"),
                createUser("Jane", "Smith", "jane.smith@shop.com"),
                createUser("Alice", "Johnson", "alice.johnson@shop.com"),
                createUser("Bob", "Brown", "bob.brown@shop.com"),
                createUser("Charlie", "Williams", "charlie.williams@shop.com"),
                createUser("David", "Thompson", "david.thompson@shop.com"),
                createUser("Eve", "Davis", "eve.davis@shop.com"),
                createUser("Fred", "Miller", "fred.miller@shop.com"),
                createUser("Grace", "Wilson", "grace.wilson@shop.com")
            ));
        }

        /* =========================
           CATEGORIES
        ========================= */
        Category springMen;
        Category springWomen;

        if (categoryRepo.count() == 0) {

            springMen = new Category();
            springMen.setName("Printemps Homme");

            springWomen = new Category();
            springWomen.setName("Printemps Femme");

            categoryRepo.saveAll(List.of(springMen, springWomen));

        } else {
            springMen = categoryRepo.findAll().stream()
                    .filter(c -> c.getName().equals("Printemps Homme"))
                    .findFirst()
                    .orElseThrow();
            springWomen = categoryRepo.findAll().stream()
                    .filter(c -> c.getName().equals("Printemps Femme"))
                    .findFirst()
                    .orElseThrow();
        }

        /* =========================
           PRODUCTS
        ========================= */
        if (productRepo.count() == 0) {

            productRepo.saveAll(List.of(
                new Product(
                    null,
                    "New Balance 530",
                    "New Balance 530 Homme Chaussures de loisirs 9.5 Orange.",
                    "https://www.idealo.fr/prix/200603576/new-balance-530.html",
                    new BigDecimal(64.79),
                    27,
                    springMen
                ),
                new Product(
                    null,
                    "Adidas Samba OG",
                    "adidas Samba Og Femme Black Silver Baskets - 36 2/3 EU.",
                    "https://www.idealo.fr/prix/5824995/adidas-samba-og.html",
                    new BigDecimal(53.45),
                    54,                
                    springWomen
                )
            ));
        }
    }

    /* =========================
        MÉTHODES UTILITAIRES
    ========================= */

    private User createUser(String firstname, String lastname, String email) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRole(Role.USER);
        return user;
    }

    private User createAdmin() {
        User admin = new User();
        admin.setFirstname("Admin");
        admin.setLastname("Admin");
        admin.setEmail("admin@shop.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        return admin;
    }
}