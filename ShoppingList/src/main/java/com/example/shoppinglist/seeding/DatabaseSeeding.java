package com.example.shoppinglist.seeding;

import com.example.shoppinglist.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeding implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public DatabaseSeeding(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
            categoryService.seedCategories();
    }
}
