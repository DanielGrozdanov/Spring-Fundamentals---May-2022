package com.example.shoppinglist.model.entity.dtos;


import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddDTO {

    @NotBlank
    @Size(min = 3,max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;

    @Positive
    private BigDecimal price;


    private CategoryEnum category;

    public ProductAddDTO() {

    }

    public String getName() {
        return name;
    }

    public ProductAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductAddDTO setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductAddDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
