package com.examp.models.dtos;


public class ShipBattleDTO {

    private String attacker;

    private String defender;


    public String getAttacker() {
        return attacker;
    }

    public ShipBattleDTO setAttacker(String attacker) {
        this.attacker = attacker;
        return this;
    }

    public String getDefender() {
        return defender;
    }

    public ShipBattleDTO setDefender(String defender) {
        this.defender = defender;
        return this;
    }
}
