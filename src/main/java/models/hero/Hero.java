package models.hero;
import models.IFighter;
import models.card.Weapon;

import java.util.Observable;

public class Hero extends Observable implements IFighter {

    private static final int MAX_HEALTH = 30;
    private static final int START_ARMOR = 0;

    private Weapon weapon;
    private int health;
    private int armor;
    private HeroClass heroClass;
    private boolean hasAttacked;

    public Hero(HeroClass heroClass) {
        this.heroClass = heroClass;
        this.weapon = null;
        this.health = MAX_HEALTH;
        this.armor = START_ARMOR;
        this.hasAttacked = false;
    }

    @Override
    public boolean hasAttacked() {
        return this.hasAttacked;
    }

    @Override
    public void newTurn() {
        hasAttacked = false;
        update();
    }

    @Override
    public void attack(IFighter target) {
        if(weapon != null && !hasAttacked){
            target.takeDamage(weapon.getAttack());
            takeDamage(target.getAttack());
            weapon.loseDurability(1, this);
            hasAttacked = true;
        }
        update();
    }

    @Override
    public void takeDamage(int damage) {
        armor -= damage;
        if(armor < 0) {
            damage = -armor;
            armor = 0;
        }
        health -= damage;
        if(health <= 0){
            die();
        }
        update();
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

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        update();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        update();
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
        update();
    }

    public void update(){
        setChanged();
        notifyObservers(this);
    }
}
