package com.examp.services;

import com.examp.models.Category;
import com.examp.models.ShipType;
import com.examp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void seedCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(ShipType.values())
                    .forEach(categoryEnum -> {
                        Category category = new Category();
                        category.setName(categoryEnum);
                        categoryRepository.save(category);
                    });
        }
    }

    public Category getCategory(ShipType shipType) {
        return categoryRepository.findByName(shipType);
    }
}
