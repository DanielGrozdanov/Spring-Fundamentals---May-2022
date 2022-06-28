package com.example.shoppinglist.model.entity.views;


import com.example.shoppinglist.model.entity.Category;

import java.math.BigDecimal;

public class ProductViewModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;

    public ProductViewModel() {

    }

    public Long getId() {
        return id;
    }

    public ProductViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ProductViewModel setCategory(Category category) {
        this.category = category;
        return this;
    }
}

