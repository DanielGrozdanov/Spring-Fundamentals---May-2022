package com.example.coffeshop.model.entity;

import com.example.coffeshop.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BasicEntity{

    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(name = "needed_time",nullable = false)
    private int neededTime;

    public Category() {

    }

    public CategoryEnum getName() {
        return name;
    }

    public Category setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public Category setNeededTime(int neededTime) {
        this.neededTime = neededTime;
        return this;
    }
}
