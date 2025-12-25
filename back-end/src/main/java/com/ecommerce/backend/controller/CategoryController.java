package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository cRepo;

    public CategoryController(CategoryRepository cRepo) {
        this.cRepo = cRepo;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return cRepo.save(category);
    }

    @GetMapping
    public List<Category> findAll() {
        return cRepo.findAll();
    }
}