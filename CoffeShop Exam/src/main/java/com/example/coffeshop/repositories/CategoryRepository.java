package com.example.coffeshop.repositories;

import com.example.coffeshop.model.entity.Category;
import com.example.coffeshop.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(CategoryEnum categoryEnum);

}
