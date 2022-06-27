package com.example.coffeshop.services;

import com.example.coffeshop.model.entity.Category;
import com.example.coffeshop.model.entity.enums.CategoryEnum;
import com.example.coffeshop.repositories.CategoryRepository;
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
                switch (categoryEnum){
                    case Drink -> category.setNeededTime(1);
                    case Coffee -> category.setNeededTime(2);
                    case Other -> category.setNeededTime(5);
                    case Cake -> category.setNeededTime(10);
                }
                categoryRepository.save(category);
            });
        }
    }
    public Category getCategory(CategoryEnum categoryEnum){
        return this.categoryRepository.findByName(categoryEnum);
    }
}
