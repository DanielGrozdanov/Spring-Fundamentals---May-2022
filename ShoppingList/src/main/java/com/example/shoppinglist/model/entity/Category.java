package com.example.shoppinglist.model.entity;


import com.example.shoppinglist.model.entity.enums.CategoryEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> product;


    public Category() {

    }

    public Set<Product> getProduct() {
        return product;
    }

    public Category setProduct(Set<Product> product) {
        this.product = product;
        return this;
    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
