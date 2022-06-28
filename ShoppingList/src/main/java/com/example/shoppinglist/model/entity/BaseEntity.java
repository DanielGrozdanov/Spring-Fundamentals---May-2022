package com.example.shoppinglist.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public BaseEntity setId(long id) {
        this.id = id;
        return this;
    }
}
