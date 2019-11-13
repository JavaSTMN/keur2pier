package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Board;
import com.stmn.keur2pier.IFighter;
import org.json.simple.JSONObject;

public class Minion extends Card implements IFighter {

    private int healthPoint;
    private int attackPoint;
    private boolean hasAttacked;

    public Minion(JSONObject jsonObject) {
        super(jsonObject);
        this.healthPoint = ((Long) jsonObject.getOrDefault("health", 0L)).intValue();
        this.attackPoint = ((Long) jsonObject.getOrDefault("attack", 0L)).intValue();
    }

    public void playMinion(Board board) {
        board.addMinion(this);
        this.hasAttacked = true;
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
            target.takeDamage(attackPoint);
            takeDamage(target.getAttack());
        }
    }

    @Override
    public void takeDamage(int damage) {
        healthPoint -= damage;
        if (healthPoint <= 0){
            die();
        }
    }

    @Override
    public int getAttack() {
        return attackPoint;
    }

    @Override
    public void die() {

    }
}
