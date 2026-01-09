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
                    "Skechers Graceful",
                    "skechers graceful get connected black gold.",
                    "src/app/assets/skechers-graceful-get-connected-black-gold.jpg",
                    new BigDecimal(33.36),
                    30,                
                    springWomen
                ),
                new Product(
                    null,
                    "Nike Air Force 1 07",
                    "nike air force 1 07 dark obsidian black.",
                    "src/app/assets/nike-air-force-1-07-dark-obsidian-black-smoke-grey-dark-obsidian.jpg",
                    new BigDecimal(83.30),
                    30,                
                    springMen
                ),
                new Product(
                    null,
                    "Asics GEL NYC",
                    "asics gel nyc 1201a789 graphite grey black.",
                    "src/app/assets/asics-gel-nyc-1201a789-graphite-grey-black.jpg",
                    new BigDecimal(150.00),
                    30,                
                    springWomen
                ),
                new Product(
                    null,
                    "Puma Club II Era",
                    "puma club ii era cool dark gray vapor gray gum.",
                    "src/app/assets/puma-club-ii-era-cool-dark-gray-vapor-gray-gum.jpg",
                    new BigDecimal(38.99),
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