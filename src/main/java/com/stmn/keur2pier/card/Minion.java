package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Board;
import com.stmn.keur2pier.IFighter;
import com.stmn.keur2pier.Player;
import org.json.simple.JSONObject;

public class Minion extends Card implements IFighter {

    public int getHealth() {
        return health;
    }

    private int health;
    private int attack;
    private boolean hasAttacked;

    private MinionMechanics minionMechanics;

    public Minion(JSONObject jsonObject) {
        super(jsonObject);
        this.health = ((Long) jsonObject.getOrDefault("health", 0L)).intValue();
        this.attack = ((Long) jsonObject.getOrDefault("attack", 0L)).intValue();
        if(jsonObject.containsKey("mechanics")){
            this.minionMechanics = new MinionMechanics((JSONObject) jsonObject.get("mechanics"));
        } else {
            this.minionMechanics = null;
        }
    }

    @Override
    public void playCard(Player owner){
        owner.getBoard().addMinion(this);
        if(minionMechanics != null){
            this.hasAttacked = !minionMechanics.isCharge();
            minionMechanics.battleCry(owner);
        } else {
            this.hasAttacked = true;
        }
    }

    public void destroy(Player owner){
        if(minionMechanics != null){
            minionMechanics.deathRattle(owner);
        }
        owner.getBoard().removeMinion(this);
        die();
    }

    @Override
    public boolean hasAttacked() {
        return this.hasAttacked;
    }

    public void newTurn(){
        this.hasAttacked = false;
    }

    @Override
    public void attack(IFighter target) {
        if(!hasAttacked) {
            target.takeDamage(attack);
            takeDamage(target.getAttack());
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (minionMechanics != null && minionMechanics.isDivineShield()) {
            minionMechanics.setDivineShield(false);
        } else {
            health -= damage;
            if (health <= 0){
                die();
            }
        }
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void die() {

    }

    public MinionMechanics getMinionMechanics() {
        return minionMechanics;
    }

    public int getHealth() {
        return health;
    }

    public boolean isHasAttacked() {
        return hasAttacked;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
