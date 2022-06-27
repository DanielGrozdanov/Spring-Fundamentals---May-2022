package com.example.coffeshop.model.entity;


import javax.persistence.*;


@MappedSuperclass
public abstract class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public BasicEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
