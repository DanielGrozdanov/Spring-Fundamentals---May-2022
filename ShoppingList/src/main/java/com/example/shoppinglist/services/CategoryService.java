package com.example.shoppinglist.services;


import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import com.example.shoppinglist.repositories.CategoryRepository;
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

    public void seedCategories(){
        if (categoryRepository.count() == 0){
            Arrays.stream(CategoryEnum.values()).forEach(categoryEnum -> {
                Category category = new Category();
                category.setName(categoryEnum);
                categoryRepository.save(category);
            });
        }
    }

    public Category getCategory(CategoryEnum category) {
       return this.categoryRepository.findByName(category);
    }
}
