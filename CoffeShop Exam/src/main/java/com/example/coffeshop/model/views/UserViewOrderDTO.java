package com.example.coffeshop.model.views;


public class UserViewOrderDTO {

    private String username;
    private Integer countOfOrders;

    public UserViewOrderDTO() {

    }

    public String getUsername() {
        return username;
    }

    public UserViewOrderDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getCountOfOrders() {
        return countOfOrders;
    }

    public UserViewOrderDTO setCountOfOrders(Integer countOfOrders) {
        this.countOfOrders = countOfOrders;
        return this;
    }
}

