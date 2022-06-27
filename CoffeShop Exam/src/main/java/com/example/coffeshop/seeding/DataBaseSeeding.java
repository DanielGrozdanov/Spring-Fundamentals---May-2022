package com.example.coffeshop.seeding;

import com.example.coffeshop.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseSeeding implements CommandLineRunner {

    private final CategoryService categoryService;

    public DataBaseSeeding(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.seedCategories();
    }
}
