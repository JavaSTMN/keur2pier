package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.hero.Hero;
import org.json.simple.JSONObject;

public class Weapon extends Card {

    private int attack;
    private int durability;

    public Weapon(JSONObject jsonObject) {
        super(jsonObject);
        this.attack = ((Long) jsonObject.getOrDefault("attack", 0L)).intValue();
        this.durability = ((Long) jsonObject.getOrDefault("durability", 0L)).intValue();
    }

    @Override
    public void playCard(Player owner){
        owner.getHero().setWeapon(this);
    }

    public void destroy(Hero hero){
        hero.setWeapon(null);
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

    public void loseDurability(int value, Hero hero){
        durability -= value;
        if(durability == 0){
            destroy(hero);
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
