package com.examp.models.dtos;

import org.apache.juli.logging.Log;

public class ShipStatusView {

    private String name;
    private Long health;
    private Long power;

    public ShipStatusView() {

    }

    public String getName() {
        return name;
    }

    public ShipStatusView setName(String name) {
        this.name = name;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public ShipStatusView setHealth(Long health) {
        this.health = health;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public ShipStatusView setPower(Long power) {
        this.power = power;
        return this;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
