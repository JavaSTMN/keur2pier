package com.stmn.keur2pier.hero;

import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.Weapon;
import com.stmn.keur2pier.IFighter;

public class Hero implements IFighter {

    private static final int MAX_HEALTH = 30;
    private static final int START_ARMOR = 0;

    private Weapon weapon;
    private int health;
    private int armor;
    private HeroClass heroClass;
    private boolean hasAttacked;
    private Player owner;

    public Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public Hero(HeroClass heroClass, Player owner) {
        this.weapon = null;
        this.health = MAX_HEALTH;
        this.armor = START_ARMOR;
        this.heroClass = heroClass;
        this.hasAttacked = false;
        this.owner = owner;
    }

    @Override
    public boolean hasAttacked() {
        return this.hasAttacked;
    }

    @Override
    public void newTurn() {
        hasAttacked = false;
    }

    @Override
    public void attack(IFighter target) {
        if(weapon != null && !hasAttacked){
            target.takeDamage(weapon.getAttack());
            takeDamage(target.getAttack());
            weapon.loseDurability(1, owner);
            hasAttacked = true;
        }
    }

    @Override
    public void takeDamage(int damage) {
        armor -= damage;
        if(armor <= 0) {
            damage = armor;
            armor = 0;
        }
        health -= damage;
        if(health <= 0){
            die();
        }
    }

    @Override
    public int getAttack() {
        if(weapon != null){
            return weapon.getAttack();
        }
        return 0;
    }

    @Override
    public void die() {
        owner.lose();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
