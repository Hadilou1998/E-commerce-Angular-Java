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
                    "New Balance 530 white natural indigo.",
                    "src/app/assets/new-balance-530-white-natural-indigo.jpg",
                    new BigDecimal(81.25),
                    27,
                    springMen
                ),
                new Product(
                    null,
                    "Adidas Samba OG",
                    "adidas Samba Og core black cloud white gum.",
                    "src/app/assets/adidas-samba-og-b75807-core-black-cloud-white-gum5.jpg",
                    new BigDecimal(71.99),
                    54,                
                    springWomen
                ),
                new Product(
                    null,
                    "Nike Shox TL",
                    "Nike Shox TL type white.",
                    "src/app/assets/nike-shox-tl.jpg",
                    new BigDecimal(51.00),
                    30,                
                    springMen
                ),
                new Product(
                    null,
                    "Adidas SL 72 OG",
                    "adidas sl 72 og women scarlet cream white gum.",
                    "src/app/assets/adidas-sl-72-og-women-scarlet-cream-white-gum.jpg",
                    new BigDecimal(64.90),
                    30,                
                    springWomen
                ),
                new Product(
                    null,
                    "Nike Air Max LTD",
                    "nike air max ltd 3 black white cool grey.",
                    "src/app/assets/nike-air-max-ltd-3-black-white-cool-grey.jpg",
                    new BigDecimal(55.00),
                    30,                
                    springMen
                ),
                new Product(
                    null,
                    "Chaussures printemps maille",
                    "Chaussures-printemps-maille-respirante-decontractée.",
                    "src/app/assets/Chaussures-printemps-maille-respirante-decontractée.jpg",
                    new BigDecimal(129.99),
                    30,                
                    springWomen
                ),
                new Product(
                    null,
                    "Adidas Ultraboost",
                    "Adidas Ultraboost 2.0 blue black white.",
                    "src/app/assets/adidas-ultraboost-2-0-blue-black-white.jpg",
                    new BigDecimal(199.99),
                    30,                
                    springMen
                ),
                new Product(
                    null,
                    "Nike Air Zoom Pegasus 36",
                    "Nike Air Zoom Pegasus 36 black white.",
                    "src/app/assets/nike-air-zoom-pegasus-36-black-white.jpg",
                    new BigDecimal(299.99),
                    30,                
                    springWomen
                ),
                new Product(
                    null,
                    "Adidas Ultraboost",
                    "Adidas Ultraboost 2.0 blue black white.",
                    "src/app/assets/adidas-ultraboost-2-0-blue-black-white.jpg",
                    new BigDecimal(199.99),
                    30,                
                    springMen
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