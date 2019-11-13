package com.stmn.keur2pier.card;

import org.json.simple.JSONObject;

public class Weapon extends Card {

    private int attackPoint;
    private int durability;

    public Weapon(JSONObject jsonObject) {
        super(jsonObject);
        this.attackPoint = (int) jsonObject.getOrDefault("attack", 0);
        this.durability = (int) jsonObject.getOrDefault("health", 0);
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
