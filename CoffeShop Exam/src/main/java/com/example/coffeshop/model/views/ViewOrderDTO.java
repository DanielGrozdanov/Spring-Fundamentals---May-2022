package com.example.coffeshop.model.views;

import com.example.coffeshop.model.entity.Category;

import java.math.BigDecimal;

public class ViewOrderDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private Category category;

    public ViewOrderDTO() {

    }

    public long getId() {
        return id;
    }

    public ViewOrderDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ViewOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ViewOrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ViewOrderDTO setCategory(Category category) {
        this.category = category;
        return this;
    }
}
