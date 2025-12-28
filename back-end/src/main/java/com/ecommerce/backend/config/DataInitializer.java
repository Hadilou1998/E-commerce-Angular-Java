package com.ecommerce.backend.config;

import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

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

            User.admin = new User();
            admin.setFirstname("Admin");
            admin.setLastname("Admin");
            admin.setEmail("admin@shop.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            User.user = new User();
            user.setFirstname("John");
            user.setLastname("Doe");
            user.setEmail("john.doe@shop.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole(Role.USER);

            User.user = new User();
            user.setFirstname("Jane");
            user.setLastname("Smith");
            user.setEmail("jane.smith@shop.com");
            user.setPassword(passwordEncoder.encode("user123"));

            User.user = new User();
            user.setFirstname("Alice");
            user.setLastname("Johnson");
            user.setEmail("alice.johnson@shop.com");
            user.setPassword(passwordEncoder.encode("user123"));

            userRepo.saveAll(List.of(admin, user));
        }

        /* =========================
           CATEGORIES
        ========================= */
        if (categoryRepo.count() == 0) {

            Category springMen = new Category(
                null,
                "Printemps Homme",
                "Vêtements homme pour la saison du printemps."
            );

            Category springWomen = new Category(
                null,
                "Printemps Femme",
                "Vêtements femme pour la saison du printemps."
            );

            categoryRepo.saveAll(List.of(springMen, springWomen));
        }

        /* =========================
           PRODUCTS
        ========================= */
        if (productRepo.count() == 0) {

            new Product(
                null,
                "New Balance 530",
                "New Balance 530 Homme Chaussures de loisirs 8.5 Orange.",
                new BigDecimal(64.79),
                27,
                "https://www.idealo.fr/prix/200603576/new-balance-530.html",
                springMen,
            ),

            new Product(
                null,
                "Adidas Samba OG",
                "adidas Samba Og Femme Black Silver Baskets - 36 2/3 EU.",
                new BigDecimal(53.45),
                54,
                "https://www.idealo.fr/prix/5824995/adidas-samba-og.html",
                springWomen,
            ),
        }
    }
}