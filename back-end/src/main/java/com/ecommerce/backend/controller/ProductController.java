package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository pRepo;

    public ProductController(ProductRepository pRepo) {
        this.pRepo = pRepo;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return pRepo.save(product);
    }

    @GetMapping
    public List<Product> findAll() {
        return pRepo.findAll();
    }
}