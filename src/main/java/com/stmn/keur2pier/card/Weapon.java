package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Player;
import org.json.simple.JSONObject;

public class Weapon extends Card {

    private int attack;
    private int durability;

    public Weapon(JSONObject jsonObject) {
        super(jsonObject);
        this.attack = (int) jsonObject.getOrDefault("attack", 0);
        this.durability = (int) jsonObject.getOrDefault("health", 0);
    }

    @Override
    public void playCard(Player owner){
        owner.getDeck().getHero().setWeapon(this);
    }

    @Override
    public void destroy(Player owner){
        owner.getDeck().getHero().setWeapon(null);
    }

    public void gainAttack(int value){
        attack += value;
    }

    public void loseAttack(int value){
        durability -= value;
    }

    public void gainDurability(int value){
        durability += value;
    }

    public void loseDurability(int value, Player owner){
        durability -= value;
        if(durability == 0){
            destroy(owner);
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
