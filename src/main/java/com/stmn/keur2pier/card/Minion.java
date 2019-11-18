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

    private Mechanics mechanics;

    public Minion(JSONObject jsonObject) {
        super(jsonObject);
        this.health = ((Long) jsonObject.getOrDefault("health", 0L)).intValue();
        this.attack = ((Long) jsonObject.getOrDefault("attack", 0L)).intValue();
        if(jsonObject.containsKey("mechanics")){
            this.mechanics = new Mechanics((JSONObject) jsonObject.get("mechanics"));
        } else {
            this.mechanics = null;
        }
    }

    @Override
    public void playCard(Player owner){
        owner.getBoard().addMinion(this);
        this.hasAttacked = true;
    }

    public void destroy(Board board){
        board.removeMinion(this);
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
        health -= damage;
        if (health <= 0){
            die();
        }
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void die() {

    }
}
